import controller.MainController;

import java.sql.*;
import model.Usuario;


/*
* import controller.AutenticacaoController;

public class Main {
    public static void main(String[] args) {
        AutenticacaoController acontroller = new AutenticacaoController();
    }
}
* */

public class Main {
	Usuario usuario;

	public static void main(String[] args) {

		MainController menu = new MainController();
	}

	private static void runSql(Connection conn) {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT foo FROM bar");

			// or alternatively, if you don't know ahead of time that
			// the query will be a SELECT...

			if (stmt.execute("SELECT foo FROM bar")) {
				rs = stmt.getResultSet();
			}

			// Now do something with the ResultSet ....
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
	}
}