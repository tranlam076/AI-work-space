package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.CheckLoginLibrary;
import library.FilenameLibrary;
import model.bean.Category;
import model.bean.News;
import model.dao.CatDAO;
import model.dao.NewsDAO;

/**
 * Servlet implementation class AddCatAdminController
 */
@MultipartConfig
public class AddNewsAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!CheckLoginLibrary.checkLogin(request, response)){
			return;
		}
		
		NewsDAO newsDAO = new NewsDAO();
		String name = new String(request.getParameter("tentin").getBytes("ISO-8859-1"),"UTF-8");
		int idCat = Integer.parseInt(request.getParameter("danhmuc"));
		String preview_text = new String(request.getParameter("mota").getBytes("ISO-8859-1"),"UTF-8");
		String detail_text = new String(request.getParameter("chitiet").getBytes("ISO-8859-1"),"UTF-8");
		String picture = null;
		
		response.setContentType("text/html;charset=UTF-8");
		final String path = request.getServletContext().getRealPath("files");
		File dirUrl = new File(path);
		if (!dirUrl.exists()) {
			dirUrl.mkdir();
		}
		final Part filePart = request.getPart("hinhanh");
		String fileName = FilenameLibrary.getFileName(filePart);
		
		if(!"".equals(fileName)){
			Date thoiGian= new Date();
	        SimpleDateFormat time = new SimpleDateFormat("dd-MM-yyyy_HHmmssSS");
	        String gettime = time.format(thoiGian.getTime()); 
	              
			String extension = "";
			int i = fileName.lastIndexOf('.');
			if (i > 0) {
			    extension = fileName.substring(i+1); 
			}
			fileName=gettime+"."+extension;
			OutputStream out = null;
			InputStream filecontent = null;
			picture = fileName;
			try {
				out = new FileOutputStream(new File(path + File.separator
						+ fileName));
				filecontent = filePart.getInputStream();
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			} catch (FileNotFoundException fne) {
				System.err.println("Có lỗi trong quá trình xử lí!");
				fne.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
			}
		}
		
		News objNews = new News(0, name, preview_text, detail_text, idCat, picture, null);
		if(newsDAO.addItem(objNews)>0){
			response.sendRedirect(request.getContextPath()+"/admin/indexNews?msg=1");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/indexNews?msg=0");
			return;
		}
	}
	
}
