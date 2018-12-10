package model.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.bean.User;

public class UserDAO {
	public ArrayList<User> getListUsers () {
		ArrayList<User> listUsers = new ArrayList<> ();
		FileInputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\tranl\\Desktop\\users.txt");
			Scanner input = new Scanner(f);
			while ( input.hasNextLine() )  {
				String [] users = input.nextLine().split(",");
				listUsers.add(new User(users[0],users[1],users[2],users[3]));
			}
	        input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		listUsers.add(new User("chi","123","this is info of chi pheo", "normal"));
//		listUsers.add(new User("kien","123","this is info of ba kien", "normal"));
//		listUsers.add(new User("kien2","123","this is info of ba kien2", "normal"));
//		listUsers.add(new User("admin","123","this is info of admin", "admin"));
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
	
	public static void main(String[] args) {
		new UserDAO().getListUsers();
	}
}
