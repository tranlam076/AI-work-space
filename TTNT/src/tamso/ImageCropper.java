package tamso;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCropper {

	public static void main(String[] args) {
		try {
			BufferedImage originalImgage = ImageIO.read(new File("C:\\Users\\tranl\\Desktop\\SA_DATA\\Images\\test.jpg"));
			
			System.out.println("Original Image Dimension: "+originalImgage.getWidth()+"x"+originalImgage.getHeight());

			BufferedImage SubImgage = originalImgage.getSubimage(300, 150, 200, 200);
			System.out.println("Cropped Image Dimension: "+SubImgage.getWidth()+"x"+SubImgage.getHeight());

			File outputfile = new File("C:\\Users\\tranl\\Desktop\\SA_DATA\\Images\\croppedImage.jpg");
			ImageIO.write(SubImgage, "jpg", outputfile);

			System.out.println("Image cropped successfully: "+outputfile.getPath());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
