package BAITH1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class bai2_client 
{
        private static Scanner sc;

		public static void main(String args[])
        {
        	sc = new Scanner(System.in);
        	try
        	{
        		Socket soc=new Socket("localhost",5000);
        		DataInputStream in=new DataInputStream(soc.getInputStream());
        		DataOutputStream out=new DataOutputStream(soc.getOutputStream());
        		System.out.println("Nhap vao bieu thuc can tinh:");
        		out.writeUTF(sc.nextLine());
        		String nhan=in.readUTF();
        		System.out.println(nhan);
        		soc.close();
        	}catch(Exception e)
        	{
        		
        	}
        }
}
