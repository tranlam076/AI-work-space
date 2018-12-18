package BAITH1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class bai1_server
{
   public static void main(String args[])
   {
	   ServerSocket ser;
	   try
	   {
		ser=new ServerSocket(9200);
		Socket soc=ser.accept();
		DataInputStream in=new DataInputStream(soc.getInputStream());
		DataOutputStream out=new DataOutputStream(soc.getOutputStream());
		String nhan=in.readUTF();
		String doihoa=nhan.toUpperCase();
		out.writeUTF(doihoa);
		String doithuong=nhan.toLowerCase();
		out.writeUTF(doithuong);
		StringTokenizer b=new StringTokenizer(nhan, " ");
		String goi="So tu trong chuoi gui la:"+b.countTokens();
		out.writeUTF(goi);
	   }catch(Exception e)
	   {	   }
   }

}
