package data.service;

import java.sql.Connection;

import data.SqliteConnection;
public class BaseService {

	protected SqliteConnection sqlite;
	
	public BaseService() {
		sqlite = SqliteConnection.getInstance();
	}
}
