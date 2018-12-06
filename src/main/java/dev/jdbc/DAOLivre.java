package dev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOLivre {

	public static void getAllBooks() {

		Connection co = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			co = ConnectionLivre.getConnection();
			st = co.createStatement();

			rs = st.executeQuery("SELECT * FROM livre");

			ResultSetMetaData rsmd = rs.getMetaData();

			System.out.println("\r\n====");

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.println("\t" + rsmd.getColumnName(i).toUpperCase() + "\t");
			}

			System.out.println();

			while (rs.next()) {
				System.out.println("\t" + rs.getInt("id") + "\t\t" 
						+ rs.getString("titre") + "\t\t"
						+ rs.getString("genre") + "\t\t" 
						+ rs.getString("categorie") + "\t\t" 
						+ rs.getInt("id_auteur") + "\t\t" 
						+ rs.getInt("id_editeur") + "\t\t" 
						+ rs.getInt("id_genre") + "\t\t"
						+ rs.getInt("id_categorie") + "\r\n");
			}

			System.out.println("\r\n====");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				rs.close();
				st.close();
				co.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteBook(int id) throws Exception {
		Connection co = null;
		PreparedStatement pstatement = null;

		try {
			co = ConnectionLivre.getConnection();

			pstatement = co.prepareStatement("DELETE FROM livre WHERE id=?");
			pstatement.setInt(1, id);

			int result = pstatement.executeUpdate();
			if (result == 0) {
				System.out.println("No book to delete");
			}

		} catch (Exception e) {
			throw new Exception("Cannot perform delete");
		} finally {
			pstatement.close();
			co.close();
		}

	}

	public static void insertBook(String titre, int auteur, int editeur) throws Exception{
		
		Connection co = null;
		PreparedStatement pstatement = null;
		
		try {
			co = ConnectionLivre.getConnection();
			
			pstatement = co.prepareStatement("INSERT INTO livre (titre, id_auteur, id_editeur) values (?,?,?) ");
			pstatement.setString(1, titre);
			pstatement.setInt(2, auteur);
			pstatement.setInt(3, editeur);
			
			int result = pstatement.executeUpdate();
			if(result == 0) {
				System.out.println("Cannot register book");
			}
			
		}catch(Exception e) {
			throw new Exception("Unable to add book");
		}finally {
			
			pstatement.close();
			co.close();
			
		}
		
	}
	
	public static void updateBook(int id, String name) throws Exception{
	
		Connection co = null;
		PreparedStatement pStatement = null;
		
		try {
			
			co = ConnectionLivre.getConnection();
			
			pStatement = co.prepareStatement("UPDATE livre SET titre =? WHERE id =?");
			pStatement.setInt(2, id);
			pStatement.setString(1, name);
			int result = pStatement.executeUpdate();
			
			
		}catch(Exception e) {
			throw new Exception("Update failed");
		}finally {
			pStatement.close();
			co.close();
		}
		
		
	}
	
}
