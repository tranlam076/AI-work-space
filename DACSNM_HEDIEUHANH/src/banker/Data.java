package banker;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
public class Data {
	
	public Properties readData(){
		Properties prop = new Properties();
		String path = this.getClass().getResource("").getPath();
		path += "/input.properties";
//		System.out.println(path);
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new  FileInputStream(file);
			prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return prop;
	}
	public static void main(String[] args) {
		Data prop = new Data();
		System.out.println(prop.readData().getProperty("num-process"));
	}
}