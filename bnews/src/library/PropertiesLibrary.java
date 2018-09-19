package library;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class PropertiesLibrary {
	public Properties readProp(){
		Properties prop = new Properties();
		String path = this.getClass().getResource("").getPath();
		path = path.replaceAll("/classes/library/", "/config.properties");
		System.out.println(path);
		
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new  FileInputStream(file);
			prop.load(fis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return prop;
	}
	public static void main(String[] args) {
		PropertiesLibrary prop = new PropertiesLibrary();
		prop.readProp();
	}

}
