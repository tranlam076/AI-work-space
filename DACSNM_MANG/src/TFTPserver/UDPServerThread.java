
package TFTPserver;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class UDPServerThread extends Thread {

	protected DatagramSocket socket = null;
	private static final byte OP_RRQ = 1;
	private static final byte OP_WRQ = 2;
	private static final byte OP_DATAPACKET = 3;
	private static final byte OP_ACK = 4;
	private static final byte OP_ERROR = 5;
	private static final int DATALENGTH = 512;
	private static final int PACKET_SIZE = 516;
	private int defaultPort;
	private InetAddress address;
	private DatagramPacket firstPacket;
	private String fileName;
	private FileInputStream fileInputStream;

	public UDPServerThread(int socketNo, DatagramPacket packet) throws SocketException, FileNotFoundException {
		this.firstPacket = packet;
		socket = new DatagramSocket(socketNo);
	}
	public UDPServerThread(String name) throws SocketException, FileNotFoundException {
		super(name);
	}

	@Override
	public void run() {
		try {
			System.out.println("recieved Packet");
			socket.setSoTimeout(10000);
			firstReq(firstPacket);
		} catch (IOException e) {
			System.err.println(e);
		}
		System.out.println("done");
		socket.close();
	}

	public void firstReq(DatagramPacket packet) throws IOException {
		byte[] opcode = new byte[2];
		byte[] inDataStream = packet.getData();
		for (int i = 0; i < 2; i++) {
			opcode[i] = inDataStream[i];
		}
		if (opcode[0] == 0 && opcode[1] == OP_RRQ) {
			System.out.println("read request");
			address = packet.getAddress();
			defaultPort = packet.getPort();
			readFileName(packet);

		} else if (opcode[0] == 0 && opcode[1] == OP_WRQ) {
			System.out.println("write request");
			address = packet.getAddress();
			readFileName(packet);
			System.out.println("Send Ack for write Request");
			defaultPort = packet.getPort();
			DatagramPacket ack = new DatagramPacket(createAck((byte) 0, (byte) 0), 4, packet.getAddress(),
					packet.getPort());
			socket.send(ack);
			System.out.println("begin receive file");
			receiveFile();
		}
	}

	public void readFileName(DatagramPacket packet) throws FileNotFoundException, IOException {
		byte[] inDataStream = packet.getData();
		int i = 2;
		while (inDataStream[i] != 0) {
			i++;
		}
		ByteBuffer fileNameBytes = ByteBuffer.allocate(i - 2);
		i = 2;
		while (inDataStream[i] != 0) {
			fileNameBytes.put(inDataStream[i]);
			i++;
		}
		fileName = new String(fileNameBytes.array());
		System.out.println(fileName);

		File file = new File("C:\\Users\\tranl\\Desktop\\server\\" + fileName);
		if (inDataStream[1] != OP_WRQ) {
			if (!file.exists()) {
				System.out.println("ERROR CODE 1 - FILE NOT FOUND");
				createError(1, "File not found");
			} else {
				System.out.println(file.length());
//				do some thing to split file 
				
				
				
				byte[] fileByte = new byte[(int) file.length()];
				try {
					fileInputStream = new FileInputStream(file);
					fileInputStream.read(fileByte);
					sendFile(fileByte, packet);
				} catch (FileNotFoundException e) {
					System.out.println("File Not Found.");
					e.printStackTrace();
				} catch (IOException e1) {
					System.out.println("Error Reading The File.");
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void createError(int errorCode, String errMessage) throws IOException {
		byte[] error = new byte[512];
		int position = 0;
		error[position] = 0;
		position++;
		error[position] = OP_ERROR;
		position++;
		error[position] = 0;
		position++;
		error[position] = (byte) errorCode;
		position++;
		for (int i = 0; i < errMessage.length(); i++) {
			error[position] = (byte) errMessage.charAt(i);
			position++;
		}
		error[position] = 0;
		DatagramPacket errorPacket = new DatagramPacket(error, error.length, address, defaultPort);
		socket.send(errorPacket);
	}

	public void sendFile(byte[] fileByte, DatagramPacket packet) throws IOException {
		ByteBuffer theFileBuffer = ByteBuffer.wrap(fileByte);
		int byteLength = theFileBuffer.remaining();
		int amountOfPackets = byteLength / DATALENGTH;
		int j = 0;
		int k = -1;
		int dataOffset = 0;
		int firstBlockNumber = -1;
		int secondBlockNumber = 0;
		do {
			byte[] dst;
			if (fileByte.length - (dataOffset) >= 512) {

				dst = new byte[DATALENGTH];
			} else {
				dst = new byte[fileByte.length - (dataOffset)];
			}
			for (int i = dataOffset; i < 512 + dataOffset && i < fileByte.length; i++) {
				dst[j] = fileByte[i];
				j++;
			}
			j = 0;
			dataOffset += 512;
			secondBlockNumber++;
			if (secondBlockNumber == 128) {
				firstBlockNumber++;
				secondBlockNumber = 0;
			}

			DatagramPacket dataPacket = createPacket(packet, dst, firstBlockNumber, secondBlockNumber);
			socket.send(dataPacket);
			packet = receivedAck(packet);

			k++;

		} while (isReceivedAck(packet, firstBlockNumber, secondBlockNumber) && k < amountOfPackets);
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
		address = packet.getAddress();
		int port = packet.getPort();
		packet.setData(dataPacket);
		packet.setAddress(address);
		packet.setPort(port);
		return packet;
	}

	public boolean isReceivedAck(DatagramPacket packet, int firstBlockNumber, int secondBlockNumber)
			throws IOException {

		byte[] inDataStream = packet.getData();
		if ((int) inDataStream[0] == 0 && (int) inDataStream[1] == OP_ACK && (int) inDataStream[2] == firstBlockNumber
				&& (int) inDataStream[3] == secondBlockNumber) {
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
		InetAddress address = InetAddress.getByName("localhost");
		boolean endOfFile = true;
//		ByteArrayOutputStream file = new ByteArrayOutputStream();
		OutputStream file = new BufferedOutputStream(new FileOutputStream("C:\\Users\\tranl\\Desktop\\server\\" + fileName));
		while (endOfFile) {
			byte[] readByteArray = new byte[516];
			DatagramPacket packet = new DatagramPacket(readByteArray, readByteArray.length, address, defaultPort);
			socket.receive(packet);

			byte[] packetInput = new byte[packet.getData().length];
			packetInput = packet.getData();

			if (packetInput[1] == OP_ERROR) {
				error(packetInput);
			} else {
				System.out.println("ending file");

				if (packetInput[1] == OP_DATAPACKET && packet.getLength() == PACKET_SIZE) {
					DatagramPacket ack = new DatagramPacket(createAck(packetInput[2], packetInput[3]), 4, address,
							defaultPort);
					file.write(packetInput, 4, packetInput.length - 4);
					socket.send(ack);

				} else if (packetInput[1] == OP_DATAPACKET && packet.getLength() < PACKET_SIZE) {
					DatagramPacket ack = new DatagramPacket(createAck(packetInput[2], packetInput[3]), 4, address,
							defaultPort);

					int j = 0;
					for (int i = 4; i < packetInput.length; i++) {
						if (packetInput[i] == (byte) 0) {
							j++;
						}
					}
					file.write(packetInput, 4, (packetInput.length - 4) - j);
					socket.send(ack);
					file.close();
//					writeFile(file);
					endOfFile = false;
				}
			}
		}

	}

	public void writeFile(ByteArrayOutputStream file) throws FileNotFoundException, IOException {
		try (OutputStream outputStream = new FileOutputStream("C:\\Users\\tranl\\Desktop\\server\\" + fileName)) {
			file.writeTo(outputStream);
		}
	}

	public void error(byte[] byteArray) {
		String errorCode = new String(byteArray, 3, 1);
		String errorText = new String(byteArray, 4, byteArray.length - 4);
		System.err.println("Error: " + errorCode + " " + errorText);
	}
}
