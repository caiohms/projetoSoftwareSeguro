package database;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DatabaseConSingleton {

	private static final String DB_ADDR = "localhost";
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

	public static Statement runSelect(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException ex) {
			// handle any errors
			log.error("SQLException: " + ex.getMessage());
			log.error("SQLState: " + ex.getSQLState());
			log.error("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				stmt = null;
			}
		}
		return stmt;
	}

}
