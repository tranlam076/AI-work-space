package library;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
public class PropertiesLibrary {
	
	public Properties readProp(){
		Properties prop = new Properties();
		String path = this.getClass().getResource("").getPath();
		path = path.replaceAll("/WEB-INF/classes/library/", "/resources/config.properties");		
//		path = path.replaceAll("/classes/library/", "/config.properties");		
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
		PropertiesLibrary prop = new PropertiesLibrary();
		System.out.println("database: "+prop.readProp().getProperty("db"));

	}
}