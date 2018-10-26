package fileSplit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Splitme {
	public static void main(String[] args) {
		Splitme sp = new Splitme();
//		sp.cutFile("C:\\Users\\tranl\\Desktop\\server\\test1.rar", 7);
		sp.joinFile("C:\\Users\\tranl\\Desktop\\server\\test1.rar", "C:\\Users\\tranl\\Desktop\\client\\testt.rar");
	}

	private InputStream infile;

	public void cutFile(String FilePath, int leng) {
		File filename = new File(FilePath);
		long splitFileSize = 0, bytefileSize = 0;
		if (filename.exists()) {
			try {
				bytefileSize = leng;
				splitFileSize = bytefileSize * 1024 * 1024;
				Splitme spObj = new Splitme();
				spObj.split(FilePath, (long) splitFileSize);
				spObj = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("File Not Found....");
		}
	}

	public void joinFile(String FilePath, String fileTo) {
		File filename = new File(FilePath + "1.sp");
		if (filename.exists()) {
			filename = new File(fileTo);
			if (filename.exists()) {
				System.out.println("\"" + FilePath + "\" File Already Exist....");
			} else {
				Splitme spObj = new Splitme();
				spObj.join(FilePath, fileTo);
				spObj = null;
			}
		} else {
			System.out.println("filename not found");
		}
	}

	public void join(String fileFrom, String fileTo) {
		int data = 0;
		try {
//			file write to
			File fileName = new File(fileTo);
			OutputStream outfile = new BufferedOutputStream(new FileOutputStream(fileName));
// 			read file to write
			fileName = new File(fileFrom);
			if (fileName.exists()) {
				InputStream infile = new BufferedInputStream(new FileInputStream(fileName));
				data = infile.read();
				System.out.println(data);
				while (data != -1) {
					outfile.write(data);
					data = infile.read();
				}
				infile.close();
				try {
					if (fileName.delete()) {
						System.out.println(fileName.getName() + " is deleted!");
					} else {
						System.out.println("Delete operation is failed.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Error join file");
			}
			outfile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void split(String FilePath, long splitlen) {
		long leng = 0;
		int count = 1, data;
		try {
			File filename = new File(FilePath);
			infile = new BufferedInputStream(new FileInputStream(filename));
			data = infile.read();
			while (data != -1) {
				filename = new File(FilePath + count + ".sp");
				OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
				while (data != -1 && leng < splitlen) {
					outfile.write(data);
					leng++;
					data = infile.read();
				}
				leng = 0;
				outfile.close();
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
