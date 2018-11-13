package model.BO;

import model.DAO.UserDAO;
import model.bean.User;

public class UserBO {
	
	public User getUser(String id, String pass) {
		User user = new UserDAO().checkLogin(id, pass);
		if (user != null) {
			return user;
		}
		return null;
	}
}
