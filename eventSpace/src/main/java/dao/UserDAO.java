package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost/reservation_table";
	private final String DB_USER = "root";
	private final String DB_PASS = "1224";

	public boolean signUp(String name, String pass, String mail, int admin) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO user (name, pass, mail, admin) VALUES (?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, pass);
			pStmt.setString(3, mail);
			pStmt.setInt(4, admin);

			int result = pStmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean login(String name, String pass) {

		int count = 0;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT COUNT((name=? and pass=?) OR NULL) AS count FROM user";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, pass);

			ResultSet result = pStmt.executeQuery();
			result.next();
			count = Integer.parseInt(result.getString("count"));

			if (count == 0) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean loginAdmin(String name, String pass, boolean admin) {

		int count = 0;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM user WHERE name=? AND pass=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, name);
			pStmt.setString(2, pass);

			ResultSet result = pStmt.executeQuery();
			result.next();
			count = Integer.parseInt(result.getString("admin"));

			if (count == 0) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int userIdGet(String name, String pass) {

		int count = 0;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM user WHERE name = ? AND pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, pass);
			ResultSet result = pStmt.executeQuery();
			result.next();
			count = Integer.parseInt(result.getString("id"));

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

}
