
package serverTFTP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MainServer {
    public static void main(String args[]) throws SocketException, IOException{  
         System.out.println("started server");
         int socketNo = 69;
         
         while(true){
             DatagramSocket socket = new DatagramSocket(69);
             byte[] recBuf = new byte[516];
             DatagramPacket packet = new DatagramPacket(recBuf, recBuf.length);
             socket.receive(packet);
             System.out.println("Received first Req Packet");
             socketNo += 1000;
             ServerThread server = new ServerThread(socketNo, packet);
             System.out.println("new Server Thread");
             server.start();
             socket.close();
         }
     }  
}
