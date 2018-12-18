package BAITH1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class bai1_client
{
      private static Scanner sc;
	private static Socket soc;

	public static void main(String args[])
      {
    	  sc = new Scanner(System.in);
    	  try
    	  {
    		  soc = new Socket("localhost",9200);
    		  DataInputStream in=new DataInputStream(soc.getInputStream());
    		  DataOutputStream out=new DataOutputStream(soc.getOutputStream());
    		  System.out.print("Client gui chuoi:");
    		  out.writeUTF(sc.nextLine());
    		  String nhan="";
    		  while(nhan!=null)
    		  { 
    		  nhan=in.readUTF();
    		  System.out.println("Server goi lai chuoi:"+nhan);}
    	  }catch(Exception e)
    	  {
    	  }
      }
}