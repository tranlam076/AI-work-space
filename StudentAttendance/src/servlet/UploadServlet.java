package servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import data.service.ImageService;
import model.Image;
import util.Constants;
import util.DetectorUtils;
import util.FileHandle;
import util.StringUtils;

import static util.Constants.IMAGE_DATA_PATH;


@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;

	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		try {
			String folderPath = getServletContext().getRealPath("/images/");
			File directory = new File(folderPath);
		    if (! directory.exists()){
		    	directory.mkdirs();
		    }
			System.out.println(folderPath);
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> fileItemList = sfu.parseRequest(new ServletRequestContext(request));
			
			String type = null;
			String courseId = null;
			FileItem fileItemSaved = null;
			for (FileItem fileItem : fileItemList) {
				System.out.println(fileItem.getFieldName());
				if (fileItem.isFormField()) {
					if ("type".equals(fileItem.getFieldName())) {
						type = fileItem.getString();
					} else if ("id".equals(fileItem.getFieldName())) {
						courseId = fileItem.getString();
					}
				} else {
					fileItemSaved = fileItem;
				}
			}
			
			if (type == null || fileItemSaved == null) {
				PrintWriter writer = response.getWriter();
				writer.append("{\"status\":\"fail\"}");
				writer.flush();
				writer.close();
				return;
			}
			
			if ("course_image".equals(type)) {
				ImageService imageService = new ImageService();
				String fileExt = StringUtils.getFileExtension(fileItemSaved.getName());
				String fileId = Constants.id_prefix.IMAGE + StringUtils.random(6);
				String fileName = fileId + fileExt;
				String filePath = folderPath + fileName;
				File file = new File(filePath);
				while (file.exists()) {
					fileId = Constants.id_prefix.IMAGE + StringUtils.random(6);
					fileName = fileId + fileExt;
					filePath = folderPath + fileName;
					file = new File(filePath);
				}
				System.out.println(filePath);
				fileItemSaved.write(file);
				
				imageService.add(new Image(fileId, courseId, "/images/", fileName, fileExt, "uploading", new Date().getTime()));
				String imageData = DetectorUtils.detect(filePath);
				System.out.println(filePath);
				System.out.println(imageData);		
				//save face mark
				filePath = IMAGE_DATA_PATH + "\\" + fileId + ".json";
				FileHandle.getInstance().writeFile(filePath, imageData);
				
				String result = String.format(
						"{"
						+ "`status`:`success`,"
						+ "`url`:`%s`,"
						+ "`id`:`%s`,"
						+ "`data`:`%s`"
						+ "}", 
						"/images/" + fileName, fileId, StringUtils.encodeUrl(imageData))
						.replace("`", "\"");
				PrintWriter writer = response.getWriter();
				//writer.append("{\"status\":\"success\", \"url\":\"" + "/images/" + fileName + "\", \"id\": \""+ fileId + "\"}");
				writer.append(result);
				writer.flush();
				writer.close();
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
