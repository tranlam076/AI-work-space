
package TFTPserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TFTPUDPServer {
    public static void main(String args[]) throws SocketException, IOException{  
         System.out.println("started the main server");
         int socketNo = 9000;
         
         while(true){
             DatagramSocket socket = new DatagramSocket(9000);
             byte[] recBuf = new byte[516];
             DatagramPacket packet = new DatagramPacket(recBuf, recBuf.length);
             socket.receive(packet);
             System.out.println("Received first Req Packet");
             socketNo++;
             UDPServerThread server = new UDPServerThread(socketNo, packet);
             System.out.println("new Server Thread");
             server.start();
             socket.close();
         }
     }  
}
