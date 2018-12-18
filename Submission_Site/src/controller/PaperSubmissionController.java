package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import library.PropertiesLibrary;
import model.DAO.AuthorDAO;
import model.DAO.FieldDAO;
import model.DAO.SubmissionDAO;
import model.bean.Author;
import model.bean.Field;
import model.bean.Param;
import model.bean.Submission;

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
		try {
			FieldDAO field = new FieldDAO();
			ArrayList<Field> listFields = field.getItems();
			request.setAttribute("listFields", listFields);

			request.setAttribute("listFields", listFields);
//			String message =(String) request.getAttribute("messsage");			
//			String msg =(String) request.getParameter("msg");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/submissions/paper-submission.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
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
						response.sendRedirect(request.getContextPath() + "/submissions?msg=error&message=" +paramName+ " is the required field");
					}
				}
				listParams.add(new Param(paramName, paramValues[0]));
			}

			int authorCounting = Integer.parseInt(getValues(listParams, "authorCounting"));
			String idField = getValues(listParams, "field");
			String title = getValues(listParams, "title");
			String description = getValues(listParams, "description");
			String keywords = getValues(listParams, "keywords");

//			file saving
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			response.setContentType("text/html");

			if (!isMultipart) {
				response.sendRedirect(request.getContextPath() + "/submissions?msg=error&message=form encrypt must be multipart");
			}
			PropertiesLibrary propertiesLibrary = new PropertiesLibrary();
			String savePath = propertiesLibrary.readProp().getProperty("filePath");
			Part filePart = request.getPart("file");
			String fileName = filePart.getSubmittedFileName();
			int t = fileName.lastIndexOf("\\");
			fileName = fileName.substring(t + 1);
			if (fileName == null || fileName.equals("")) {
				response.sendRedirect(request.getContextPath() + "/submissions?msg=error&message=file name null");
				return;
			}
			String fileFormat = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			if (!fileFormat.toLowerCase().equals(".pdf")) {
				response.sendRedirect(request.getContextPath() + "/submissions?msg=error&message=file format wrong");
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

//
			UUID uuid = UUID.randomUUID();
			String idSubmission = uuid.toString();

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
				listAuthors.add(new Author("", idSubmission, firstName + " " + lastName, email, country, organization,
						webpage, isCorresponding));
			}

			Submission submission = new Submission(idSubmission, idField, title, description, keywords, fileName);

//saving data into database
			AuthorDAO authorDAO = new AuthorDAO();
			SubmissionDAO submissionDAO = new SubmissionDAO();
			int resultSub = submissionDAO.addItem(submission);
			if (resultSub == 1) {
				for (Author author : listAuthors) {
					try {
						int resultAut = authorDAO.addItem(author);
						if (resultAut != 1) {
							submissionDAO.delItem(author.getIdSubmission());
							try {
								File file = new File(savePath + File.separator + fileName);
								if (file.delete()) {
									System.out.println(file.getName() + " is deleted!");
								} else {
									System.out.println("Delete operation is failed.");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							response.sendRedirect(request.getContextPath() + "/submissions?msg=error&message=error when creating author");
							return;
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				try {
					File file = new File(savePath + File.separator + fileName);
					if (file.delete()) {
						System.out.println(file.getName() + " is deleted!");
					} else {
						System.out.println("Delete operation is failed.");
					}
					response.sendRedirect(request.getContextPath() + "/submissions?msg=error&message=error when creating submission");
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect(request.getContextPath() + "/submissions?msg=success");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/submissions?msg=error&message=" + e.getMessage());
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
