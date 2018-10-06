package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadController
 */
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadFiles";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");

		if (!isMultipart) {
			out.write("error");
			return;
		}

		String savePath = "C:\\Users\\tranl\\eclipse-workspace\\Task13\\" + SAVE_DIR;
		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		int t = fileName.lastIndexOf("\\");
		fileName = fileName.substring(t + 1);
		if (fileName.equals("")) {
			out.write("error");
			request.setAttribute("message", "Upload fault!");
			getServletContext().getRequestDispatcher("/admin/show-file-manager").forward(request, response);
			return;
		}

		InputStream is = filePart.getInputStream();
		OutputStream os = new FileOutputStream(savePath + File.separator + fileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0)
			os.write(buffer, 0, length);
		is.close();
		os.close();
		request.setAttribute("message", "Upload has been done successfully!");
		getServletContext().getRequestDispatcher("/admin/show-file-manager").forward(request, response);
	}
}
