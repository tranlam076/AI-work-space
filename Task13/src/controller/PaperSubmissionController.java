package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import library.PropertiesLibrary;
import model.bean.Author;
import model.bean.Param;
import model.bean.Submission;
import model.dao.AuthorDAO;
import model.dao.SubmissionDAO;

/**
 * Servlet implementation class PaperSubmissionController
 */
public class PaperSubmissionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaperSubmissionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Enumeration<String> paramNames = request.getParameterNames();
			ArrayList<Author> listAuthors = new ArrayList<>();
			ArrayList<Param> listParams = new ArrayList<>();
//			check if parameters are full fill
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues[0] == null) {
					if (!paramName.equals("webpage") && !paramName.contains("firstName")) {
						request.setAttribute("message", paramName + " is the required field");
						getServletContext().getRequestDispatcher("/submission?msg=error").forward(request, response);
					}
				}
				listParams.add(new Param(paramName, paramValues[0]));
			}

			int authorCounting = Integer.parseInt(getValues(listParams, "authorCouting"));
			String idField = getValues(listParams, "field");
			String title = getValues(listParams, "title");
			String description = getValues(listParams, "description");
			String keywords = getValues(listParams, "keywords");
			for (int i = 1; i <= authorCounting; i++) {
				String firstName = getValues(listParams, "firstName" + i);
				if (firstName == null) {
					firstName = "";
				}
				String lastName = getValues(listParams, "lastName" + i);
				String email = getValues(listParams, "email" + i);
				String country = getValues(listParams, "country" + i);
				String organization = getValues(listParams, "organization" + i);
				String webpage = getValues(listParams, "webpage" + i);
				if (webpage == null) {
					webpage = "";
				}
				String corresponding = getValues(listParams, "corresponding" + i);
				boolean isCorresponding = false;
				if (corresponding != null && corresponding.equals("on")) {
					isCorresponding = true;
				}
				listAuthors.add(new Author("", "", firstName + " " + lastName, email, country, organization,
						webpage, isCorresponding));
			}

//			file saving
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			response.setContentType("text/html");

			if (!isMultipart) {
				request.setAttribute("message", "form encrypt must be multipart");
				getServletContext().getRequestDispatcher(request.getContextPath() + "/submission?msg=error")
						.forward(request, response);
			}
			PropertiesLibrary propertiesLibrary = new PropertiesLibrary();
			String savePath = propertiesLibrary.readProp().getProperty("filePath");
			Part filePart = request.getPart("file");
			String fileName = filePart.getSubmittedFileName();
			int t = fileName.lastIndexOf("\\");
			fileName = fileName.substring(t + 1);
			if (fileName == null || fileName.equals("")) {
				request.setAttribute("message", "file name null");
				getServletContext().getRequestDispatcher("/submission?msg=error").forward(request, response);
				return;
			}
			String fileFormat = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			if (!fileFormat.toLowerCase().equals(".pdf")) {
				request.setAttribute("message", "file format wrong");
				getServletContext().getRequestDispatcher(request.getContextPath() + "/submission?msg=error")
						.forward(request, response);
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
			Submission submission = new Submission("", idField, title, description, keywords, fileName);
//saving data into database

			AuthorDAO authorDAO = new AuthorDAO();
			SubmissionDAO submissionDAO = new SubmissionDAO();

			System.out.println("before create submission");
			String rs = "";
			try {
				rs = submissionDAO.addItem(submission);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("idsubmission: " + rs);
			System.out.println("create submission success");
			for (Author author : listAuthors) {
				System.out.println("into create author");
				try {
					int result = authorDAO.addItem(author);
					if(result != 1) {
						submissionDAO.delItem(author.getIdSubmission());
						request.setAttribute("message", "error when creating author");
						response.sendRedirect(request.getContextPath() + "/submission?msg=error&message=error when creating author");			
						return;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}		
			}

			System.out.println("done success");
			response.sendRedirect(request.getContextPath() + "/submission?msg=success");
			return;
		} catch (Exception e) {
			request.setAttribute("message", "" + e.getMessage());
			getServletContext()
					.getRequestDispatcher(
							request.getContextPath() + "/submission?msg=error&message=" + e.getLocalizedMessage())
					.forward(request, response);
		}
	}

	private String getValues(ArrayList<Param> listParams, String name) {
		for (Param param : listParams) {
			if (param.getName().equals(name)) {
				return param.getValue();
			}
		}
		return "";
	}
}
