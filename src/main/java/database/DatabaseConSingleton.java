package database;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseConSingleton {

	private static final String DB_ADDR = "192.168.100.5";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "lino";
	private static final String DB_USER = "lino";
	private static final String DB_PASS = "lino";

	private static Connection conn;

	static {
		try {
			String conString = "jdbc:mysql://" + DB_ADDR + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS;
			conn = DriverManager.getConnection(conString);
			log.info("Database connected.");
		} catch (SQLException ex) {
			log.error("SQLException: " + ex.getMessage());
			log.error("SQLState: " + ex.getSQLState());
			log.error("VendorError: " + ex.getErrorCode());
		}
	}

	private DatabaseConSingleton() {
	}

	public static Connection getConn() {
		return conn;
	}

}
