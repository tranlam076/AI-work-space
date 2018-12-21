 package clientTFTP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {

	private int TFTP_PORT = 69;
	private static final byte OP_RRQ = 1;
	private static final byte OP_WRQ = 2;
	private static final byte OP_DATAPACKET = 3;
	private static final byte OP_ACK = 4;
	private static final byte OP_ERROR = 5;
	private static String fileName;
	private String fileDir = "C:\\TFTPClient\\";
	private DatagramSocket socket;
	private static final int DATA_LENGTH = 65464;
	private static final int PACKET_SIZE = DATA_LENGTH + 4;
	static String addressByName = "";
	
	Random rand = new Random();
	private InputStream fileInputStream;

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("Wrong args input!\n"
					+ "arg[0]: Type of request (RRQ or WRQ) \n"
					+ "arg[1]: IP adress (IP of server)\n"
					+ "arg[1]: file name (name of the file)\n"
					+ "");
			return;
		}
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		
		String req = args[0];
		
		addressByName = args[1];
		Client client = new Client();
		File directory = new File(client.fileDir);
	    if (! directory.exists()){
	    	directory.mkdirs();
	    }
		fileName = args[2];
		if (fileName.contains("\\") || fileName.contains("/")) {
			int index = fileName.lastIndexOf("/");
			if (index == -1) {
				index = fileName.lastIndexOf("\\");
			}
			if (index < 0) {
				System.out.println("Wrong file path!");
				return;
			}
			client.fileDir = fileName.substring(0, index);
			fileName = fileName.substring(index + 1, fileName.length());
		}
		
		if (req.equals("RRQ")) {
			client.sendReadRequest();
		}
		if (req.equals("WRQ")) {
			client.sendWriteRequest();
		}
	}

	public void sendWriteRequest() throws SocketException, IOException {
		File file = new File(fileDir + fileName);	
		if (!file.exists()) {
			System.out.println("File not found!");
			return;
		}
		InetAddress address = InetAddress.getByName(addressByName);
		socket = new DatagramSocket(rand.nextInt(4000) + 4000);
		byte[] ackArray = new byte[4];
		byte[] requestByteArray = createRequest(OP_WRQ, fileName, "octet");
		DatagramPacket packet = new DatagramPacket(requestByteArray, requestByteArray.length, address,
				TFTP_PORT);
		socket.send(packet);
		DatagramPacket ack = new DatagramPacket(ackArray, 4);
		socket.receive(ack);
		TFTP_PORT = ack.getPort();
		receivedFirstAck(ack);
		socket.close();
	}

	public void sendReadRequest() throws UnknownHostException, SocketException, IOException {
		InetAddress address = InetAddress.getByName(addressByName);
		socket = new DatagramSocket(rand.nextInt(4000) + 6000);
		byte[] requestByteArray = createRequest(OP_RRQ, fileName, "octet");
		DatagramPacket packet = new DatagramPacket(requestByteArray, requestByteArray.length, address,
				TFTP_PORT);
		socket.send(packet);
		receiveFile();
		socket.close();
	}

	public byte[] createRequest(byte opCode, String filename, String mode) {
		byte zeroByte = 0;
		int rrqByteLength = 2 + filename.length() + 1 + mode.length() + 1;
		byte[] rrqByteArray = new byte[rrqByteLength];
		int position = 0;
		rrqByteArray[position] = zeroByte;
		position++;
		rrqByteArray[position] = opCode;
		position++;
		for (int i = 0; i < filename.length(); i++) {
			rrqByteArray[position] = (byte) filename.charAt(i);
			position++;
		}
		rrqByteArray[position] = zeroByte;
		position++;
		for (int i = 0; i < mode.length(); i++) {
			rrqByteArray[position] = (byte) mode.charAt(i);
			position++;
		}
		rrqByteArray[position] = zeroByte;
		return rrqByteArray;
	}

	
	
	

	public boolean receivedFirstAck(DatagramPacket ack) throws IOException {
		byte[] firstAck = ack.getData();
		if (firstAck[0] == 0 && (int) firstAck[1] == OP_ACK && firstAck[2] == 0 && firstAck[3] == 0) {
			readFileName(ack);
			return true;
		} else {
			return false;
		}
	}
	

	public void readFileName(DatagramPacket packet) throws FileNotFoundException, IOException {
		File file = new File(fileDir + fileName);		
		try {
			fileInputStream = new BufferedInputStream(new FileInputStream(file));
			sendFile(fileInputStream, packet);
		} catch (Exception e1) {
			return;
		}
	}

	public void sendFile(InputStream fileInputStream, DatagramPacket packet) throws IOException {
		System.out.println("Uploading...");
		byte[] buffer = new byte[DATA_LENGTH];
		int read = 0;
		int percent = 0;
		int firstBlockNumber = 0;
		int secondBlockNumber = 0;
		long curTime = System.currentTimeMillis();
		do {
			read = fileInputStream.read(buffer);
			byte[] dataStream;
				dataStream = new byte[read];
			for (int i = 0; i < read; i++) {
				dataStream[i] = buffer[i];
			}
			secondBlockNumber++;
			if (secondBlockNumber == 256) {
				firstBlockNumber++;
				secondBlockNumber = 0;
			}
			DatagramPacket dataPacket = createPacket(packet, dataStream, firstBlockNumber, secondBlockNumber);
			socket.send(dataPacket);
			packet = receivedAck(packet);
			byte[] packetInput = new byte[packet.getData().length];
			packetInput = packet.getData();
			if (packetInput[1] == OP_ERROR) {
				error(packetInput);
				return;
			}		
			if (System.currentTimeMillis() - curTime > 300) {
				curTime = System.currentTimeMillis();
				if (percent % 30 == 0) {
					System.out.println();
				}
				System.out.print("#");
				percent ++;
			}
		} while (isReceivedAck(packet, firstBlockNumber, secondBlockNumber) && read == DATA_LENGTH);
		System.out.println("\nUpload file done!");
		fileInputStream.close();
	}

	public DatagramPacket createPacket(DatagramPacket packet, byte[] theFile, int firstBlockNumber,
			int secondBlockNumber) {
		int position = 0;
		byte[] dataPacket = new byte[theFile.length + 4];
		dataPacket[position] = (byte) 0;
		position++;
		dataPacket[position] = OP_DATAPACKET;
		position++;
		dataPacket[position] = (byte) firstBlockNumber;
		position++;
		dataPacket[position] = (byte) secondBlockNumber;
		position++;

		for (int i = 0; i < theFile.length; i++) {
			dataPacket[position] = theFile[i];
			position++;
		}
        packet.setData(dataPacket);
        return packet;
	}
	
	public boolean isReceivedAck(DatagramPacket packet, int firstBlockNumber, int secondBlockNumber) throws IOException {
		byte[] inDataStream = packet.getData();
		if ((int) inDataStream[0] == 0 && (int) inDataStream[1] == OP_ACK && (byte) (inDataStream[2] & 0xff) == (byte) firstBlockNumber
				&& (byte) (inDataStream[3] & 0xff) == (byte) secondBlockNumber) {
			return true;
		} else {
			return false;
		}
	}

	public DatagramPacket receivedAck(DatagramPacket packet) throws SocketException, IOException {
		byte[] byteAck = new byte[4];
		DatagramPacket ack = new DatagramPacket(byteAck, 4, packet.getAddress(), packet.getPort());
		boolean notReceived = true;
		while (notReceived) {
			socket.setSoTimeout(10000);
			socket.receive(ack);
			notReceived = false;
		}
		return ack;
	}
	
	public byte[] createAck(byte first, byte second) {
		byte[] ack = new byte[4];
		int position = 0;
		ack[position] = 0;
		position++;
		ack[position] = OP_ACK;
		position++;
		ack[position] = first;
		position++;
		ack[position] = second;
		return ack;

	}
	
	public void receiveFile() throws UnknownHostException, SocketException, IOException {
		InetAddress address = InetAddress.getByName(addressByName);
		boolean endOfFile = true;
		OutputStream file = new BufferedOutputStream(new FileOutputStream(fileDir + fileName));
		long curTime = System.currentTimeMillis();
		while (endOfFile) {
			byte[] readByteArray = new byte[PACKET_SIZE];
			DatagramPacket packet = new DatagramPacket(readByteArray, readByteArray.length);
			socket.receive(packet);
			TFTP_PORT = packet.getPort();

			byte[] packetInput = new byte[packet.getData().length];
			packetInput = packet.getData();
			if (packetInput[1] == OP_ERROR) {
				error(packetInput);
				return;
			} else {
				if (System.currentTimeMillis() - curTime > 500) {
					curTime = System.currentTimeMillis();
					System.out.println("#");
				}
				if (packetInput[1] == OP_DATAPACKET && packet.getLength() == PACKET_SIZE) {
					DatagramPacket ack = new DatagramPacket(createAck(packetInput[2], packetInput[3]), 4, address,
							TFTP_PORT);
					file.write(packetInput, 4, packetInput.length - 4);
					socket.send(ack);
					
				} else if (packetInput[1] == OP_DATAPACKET && packet.getLength() < PACKET_SIZE) {
					DatagramPacket ack = new DatagramPacket(createAck(packetInput[2], packetInput[3]), 4, address,
							TFTP_PORT);
					int j = 0;
					for (int i = 4; i < packetInput.length; i++) {
						if (packetInput[i] == (byte) 0) {
							j++;
						}
					}
					file.write(packetInput, 4, (packetInput.length - 4) - j);
					socket.send(ack);
					file.close();
					endOfFile = false;
					System.out.println("\nDownload finish success!");
				}
			}
		}
	}
	
	public void error(byte[] byteArray) {
		String errorCode = new String(byteArray, 3, 1);
		String errorText = new String(byteArray, 4, byteArray.length - 4);
		System.out.println("Error: " + errorCode + " " + errorText);
	}
}
