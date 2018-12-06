package dev.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionLivre {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		
		try {
			conn = ConnectionLivre.getConnection();
			
			System.out.println("Connected");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			conn.close();
			System.out.println("Disconnected");
		}
		

	}

	
	public static Connection getConnection() throws Exception{
		
		DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://localhost:3333/formation";
		String user = "root";
		String passw = "root";
		
		return DriverManager.getConnection(url, user, passw);
		
	}
	
	
}
