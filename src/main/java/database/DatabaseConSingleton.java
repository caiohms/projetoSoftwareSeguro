package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConSingleton {

	private static final String DB_ADDR = "192.168.137.252";
	private static final String DB_PORT = "55002";
	private static final String DB_NAME = "lino";
	private static final String DB_USER = "lino";
	private static final String DB_PASS = "lino";

	private static DatabaseConSingleton dbSingleton;
	Connection conn;

	public DatabaseConSingleton() {
		try {
			String conString = "jdbc:mysql://" + DB_ADDR + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS;
			conn = DriverManager.getConnection(conString);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public static DatabaseConSingleton getInstance() {
		if (dbSingleton == null) {
			dbSingleton = new DatabaseConSingleton();
		}
		return dbSingleton;
	}


}
