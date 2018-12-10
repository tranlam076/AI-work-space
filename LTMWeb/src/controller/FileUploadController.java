package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/FileUploadController")
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public FileUploadController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("file up loading");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");

		if (!isMultipart) {
			response.sendRedirect(request.getContextPath() + "/AdminController?msg=error&message=form encrypt must be multipart");
		}
		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		int t = fileName.lastIndexOf("\\");
		fileName = fileName.substring(t + 1);
		if (fileName == null || fileName.equals("")) {
			response.sendRedirect(request.getContextPath() + "/AdminController?msg=error&message=file name null");
			return;
		}
		String fileFormat = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		if (!fileFormat.toLowerCase().equals(".txt")) {
			response.sendRedirect(request.getContextPath() + "/AdminController?msg=error&message=file format wrong");
			return;
		}
		InputStream is = filePart.getInputStream();
		OutputStream os = new FileOutputStream("C:\\Users\\tranl\\Desktop\\users.txt");

		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0)
			os.write(buffer, 0, length);

		is.close();
		os.close();
		
		response.sendRedirect(request.getContextPath() + "/AdminController?msg=success&message=upload success");
		return;
		
	}

}
