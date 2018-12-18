package data.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.Mark;
import util.Constants;

public class ImageCropService {
	
	StudentImageService studentImageService = new StudentImageService();
	
	public HashMap<String, String> cropImages (String url, Mark [] listMark, String imgName, String ext) {
		HashMap<String, String> map = new HashMap<>();
		try {
		    ext = ext.substring(1, ext.length());
			BufferedImage originalImgage = ImageIO.read(new File(url));
			int width = originalImgage.getWidth();
			int height = originalImgage.getHeight();
			for (int i = 0; i < listMark.length; i++) {
				BufferedImage SubImgage = originalImgage.getSubimage(
						(int) (double) (listMark[i].getLocation().getLeft()*width/100),
						(int) (double) (listMark[i].getLocation().getTop()*height/100),
						(int) (double) (listMark[i].getLocation().getWidth()*width/100),
						(int) (double) (listMark[i].getLocation().getHeight()*height/100));
				String newUrl = Constants.FACE_DATA_SET + listMark[i].getStudentCode() + "\\" + imgName;
				File directory = new File(Constants.FACE_DATA_SET + listMark[i].getStudentCode() + "\\");
			    if (! directory.exists()){
			    	directory.mkdirs();
			    }
			    if (!new File(newUrl).exists()) {
			    	studentImageService.add(listMark[i].getStudentCode(), newUrl);
					File outputfile = new File(newUrl);
					ImageIO.write(SubImgage, ext, outputfile);
					map.put(listMark[i].getStudentCode(), newUrl);
			    }
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
