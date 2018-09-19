package tranlam.models;

public class DatabaseManagement {
	public boolean checkUser(String username, String password) throws Exception {
		if (username != null && password != null && username.equals("tranlam") && password.equals("123")) {
			return true;
		} else  {
			return false;
		}
	}
}
