package br.disklanche.sc.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection con;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/DiscLancheDB", "root", "");
			con.setAutoCommit(false);
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	 public static void closeConnection(){
	        try {
	            con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
}
