package TFTPclient;

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
import java.util.Random;

/**
 *
 * 135124
 *
 *
 * This is the client side, it sends read/write requests to the server it
 * connects to the server and performs the action with the server It can send
 * data packets and acks
 *
 */
public class TFTPUDPClient {

	private int TFTP_DEFAULT_PORT = 9000;
	private static final byte OP_RRQ = 1;
	private static final byte OP_WRQ = 2;
	private static final byte OP_DATAPACKET = 3;
	private static final byte OP_ACK = 4;
	private static final byte OP_ERROR = 5;
	private static String fileName;
	private DatagramSocket socket;
	private static final int DATALENGTH = 512;
	Random rand = new Random();
	private FileInputStream fileInputStream;

	/**
	 * The main method checks the amount of arguments and creates a new client
	 *
	 * @param args the command line arguments
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {

		TFTPUDPClient client = new TFTPUDPClient();
		fileName = "client.csv";
//		client.sendReadRequest();
        client.sendWriteRequest();

	}

	/**
	 * This sends a new write request to the server from the client It gets the
	 * address and ports numbers to create a new DatagramSocket object
	 *
	 *
	 * @throws SocketException
	 * @throws IOException
	 */
	public void sendWriteRequest() throws SocketException, IOException {
		InetAddress address = InetAddress.getByName("localhost");
		socket = new DatagramSocket(rand.nextInt(4000) + 2000);
		byte[] ackArray = new byte[4];
		byte[] requestByteArray = createRequest(OP_WRQ, fileName, "octet");
		DatagramPacket packet = new DatagramPacket(requestByteArray, requestByteArray.length, address,
				TFTP_DEFAULT_PORT);
		socket.send(packet);
		DatagramPacket ack = new DatagramPacket(ackArray, 4);
		socket.receive(ack);
		TFTP_DEFAULT_PORT = ack.getPort();
		receivedFirstAck(ack);
		socket.close();
	}

	/**
	 * This sends a new read request to the server from the client It gets the
	 * address and port numbers to create a new Datagram Socket object and then
	 * calls receiveFile()
	 *
	 * @throws UnknownHostException
	 * @throws SocketException
	 * @throws IOException
	 */
	public void sendReadRequest() throws UnknownHostException, SocketException, IOException {
		InetAddress address = InetAddress.getByName("localhost");
		socket = new DatagramSocket(rand.nextInt(4000) + 2000);
		byte[] requestByteArray = createRequest(OP_RRQ, fileName, "octet");
		DatagramPacket packet = new DatagramPacket(requestByteArray, requestByteArray.length, address,
				TFTP_DEFAULT_PORT);
		socket.send(packet);
		receiveFile();
		socket.close();
	}

	/**
	 * This creates the request into bytes to be sent off, it uses the opcode the
	 * filename with a string and the mode.
	 *
	 * @param opCode   the opCode of the request
	 * @param filename the filename of the file
	 * @param mode     the mode of the packet - octet
	 * @return
	 */
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

	/**
	 * This will receive the file from the server and is called when the receive
	 * request is sent
	 *
	 * @throws UnknownHostException
	 * @throws SocketException
	 * @throws IOException
	 */
	public void receiveFile() throws UnknownHostException, SocketException, IOException {
		InetAddress address = InetAddress.getByName("localhost");
		boolean endOfFile = true;
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		// this waits till the end of file, we know when its the end of the file
		// as the packet.length < 516 bytes
		// this also send all of the acks
		while (endOfFile) {
			byte[] readByteArray = new byte[516];
			DatagramPacket packet = new DatagramPacket(readByteArray, 516);
			socket.receive(packet);
			TFTP_DEFAULT_PORT = packet.getPort();

			byte[] packetInput = new byte[packet.getData().length];
			packetInput = packet.getData();
			if (packetInput[1] == OP_ERROR) {
				error(packetInput);
			} else {
				if (packetInput[1] == OP_DATAPACKET && packet.getLength() == 516) {
					DatagramPacket ack = new DatagramPacket(createAck(packetInput[2], packetInput[3]), 4, address,
							TFTP_DEFAULT_PORT);
					file.write(packetInput, 4, packetInput.length - 4);
					socket.send(ack);
				} else if (packetInput[1] == OP_DATAPACKET && packet.getLength() < 516) {
					DatagramPacket ack = new DatagramPacket(createAck(packetInput[2], packetInput[3]), 4, address,
							TFTP_DEFAULT_PORT);

					int j = 0;
					for (int i = 4; i < packetInput.length; i++) {
						if (packetInput[i] == (byte) 0) {
							j++;
						}
					}
					file.write(packetInput, 4, (packetInput.length - 4) - j);
					socket.send(ack);
					writeFile(file);
					endOfFile = false;
				}
			}
		}

	}

	/**
	 * This writes the file using the filename given and the data that has been sent
	 * from the server
	 *
	 * @param file this is the file from the server
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeFile(ByteArrayOutputStream file) throws FileNotFoundException, IOException {
		try (OutputStream outputStream = new FileOutputStream("C:\\Users\\tranl\\Desktop\\client\\" + fileName)) {
			file.writeTo(outputStream);
			System.out.println(fileName);
			System.out.println("wrote the file");
		}
	}

	/**
	 * this creates an ack
	 *
	 * @param first  first block number
	 * @param second block number
	 * @return byte[]
	 */
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

	/**
	 * this creates an error message
	 *
	 * @param byteArray the error array
	 */
	public void error(byte[] byteArray) {
		String errorCode = new String(byteArray, 3, 1);
		String errorText = new String(byteArray, 4, byteArray.length - 4);
		System.err.println("Error: " + errorCode + " " + errorText);
	}

	/**
	 * this is called when the write request is called and the first ack from the
	 * server
	 *
	 * @param ack data from the server
	 * @return boolean
	 * @throws IOException
	 */
	public boolean receivedFirstAck(DatagramPacket ack) throws IOException {
		byte[] firstAck = ack.getData();
		if (firstAck[0] == 0 && (int) firstAck[1] == OP_ACK && firstAck[2] == 0 && firstAck[3] == 0) {
			readFileName(ack);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * this sends a file to the server when the write request has been called it
	 * creates the file and splits it into packets and then sends the packets to the
	 * server in a do while loop, incrementing the block numbers
	 *
	 * @param fileByte the file but in bytes
	 * @param packet   the packet to send
	 * @throws IOException
	 */
	public void sendFile(byte[] fileByte, DatagramPacket packet) throws IOException {
		ByteBuffer theFileBuffer = ByteBuffer.wrap(fileByte);
		int byteLength = theFileBuffer.remaining();
		int amountOfPackets = byteLength / DATALENGTH;

		int j = 0;
		int k = -1;
		int dataOffset = 0;
		int firstBlockNumber = 0;
		int secondBlockNumber = 0;
		do {
			byte[] dataStream;
			if (fileByte.length - (dataOffset) >= DATALENGTH) {
				dataStream = new byte[DATALENGTH];
			} else {
				dataStream = new byte[fileByte.length - (dataOffset)];
			}
			for (int i = dataOffset; i < DATALENGTH + dataOffset && i < fileByte.length; i++) {
				dataStream[j] = fileByte[i];
				j++;
			}
			j = 0;
			dataOffset += DATALENGTH;
			secondBlockNumber++;
			if (secondBlockNumber == 128) {
				firstBlockNumber++;
				secondBlockNumber = 0;
			}
			DatagramPacket dataPacket = createPacket(packet, dataStream, firstBlockNumber, secondBlockNumber);
			socket.send(dataPacket);
			packet = receivedAck(packet);
			k++;
		} while (receiveAck(packet, firstBlockNumber, secondBlockNumber) && k < amountOfPackets);
		System.out.println("send file success");
	}

	/**
	 * this is called in sendFile to receive the ack from the server and checks if
	 * it is correct
	 *
	 * @param packet            packet received
	 * @param firstBlockNumber  first block number
	 * @param secondBlockNumber second block number
	 * @return boolean true if it correct
	 * @throws IOException
	 */
	public boolean receiveAck(DatagramPacket packet, int firstBlockNumber, int secondBlockNumber) throws IOException {
		byte[] inDataStream = packet.getData();
		if ((int) inDataStream[0] == 0 && (int) inDataStream[1] == OP_ACK && (int) inDataStream[2] == firstBlockNumber
				&& (int) inDataStream[3] == secondBlockNumber) {
			return true;
			// this will send the requested file
		} else {
			return false;
		}
	}

	/**
	 * This creates a packet to be send, we make sure we sent the data, the address
	 * and the port, we need to put the data opcode first, the blockNo and then the
	 * data
	 *
	 * @param packet            the packet
	 * @param theFile           the file to be sent in a byte array
	 * @param firstBlockNumber  the first block number
	 * @param secondBlockNumber the second block number
	 * @return the datagram packet created
	 */
	public DatagramPacket createPacket(DatagramPacket packet, byte[] theFile, int firstBlockNumber,
			int secondBlockNumber) {
		// Create the data packet to be sent
		// make sure we sent the data, the addess and the port
		// We need to put the data opcode first, Block# and then the data
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

	/**
	 * this will receive the next ack
	 *
	 * @param packet the packet to
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
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

	/**
	 * this reads the file name and then retrieves the data from the file and then
	 * calls sendFile
	 *
	 * @param packet the packet to that has been received
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readFileName(DatagramPacket packet) throws FileNotFoundException, IOException {

		File file = new File("C:\\Users\\tranl\\Desktop\\client\\" + fileName);
		byte[] fileByte = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileByte);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Error Reading The File.");
			e1.printStackTrace();
		}
		sendFile(fileByte, packet);
	}
}
