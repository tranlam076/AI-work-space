package model.DAO;

import java.util.ArrayList;

import model.bean.User;

public class UserDAO {
	public ArrayList<User> getListUsers () {
		ArrayList<User> listUsers = new ArrayList<> ();
		listUsers.add(new User("chi","123","this is info of chi pheo"));
		listUsers.add(new User("kien","123","this is info of ba kien"));
		return listUsers;
	}
	public User checkLogin (String id, String password) {
		ArrayList<User> listUsers = getListUsers();
		for (User check : listUsers) {
			if (check.getId().equals(id)&&check.getPassword().equals(password)) {
				return check;
			}
		}
		return null;
	}
}
