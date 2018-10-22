package library;

import java.sql.SQLException;

import model.bean.Submission;
import model.dao.SubmissionDAO;

public class test {
	public static void main(String[] args) throws SQLException {
		SubmissionDAO submissionDAO = new SubmissionDAO();
		Submission sm = new Submission("","22291d81-c88d-11e8-a0ee-0026b9f396c1","title","discription","keywords","fileNameUpload");
		int rs = submissionDAO.addItem(sm);
		System.out.println("check: ");
		System.out.println(rs);
	}
}
