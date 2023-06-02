package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reserve;

public class ReserveDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/reservation_table";
	private final String DB_USER = "root";
	private final String DB_PASS = "1224";

	public List<Reserve> findAll() {

		List<Reserve> reserveList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM reserve ORDER BY date ASC, time_start ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int uId = rs.getInt("USERID");
				int id = rs.getInt("ID");

				String userName = rs.getString("NAME");
				String date = rs.getString("DATE");
				String timeStart = rs.getString("TIME_START");
				String timeEnd = rs.getString("TIME_END");
				String purpose = rs.getString("PURPOSE");
				String aaa = timeStart.substring(0, 5);
				String bbb = timeEnd.substring(0, 5);
				System.out.println(aaa); //デバッグ用
				Reserve reserve = new Reserve(uId, id, userName, date, aaa, bbb, purpose);
				reserveList.add(reserve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reserveList;
	}

	public boolean create(Reserve reserve) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO reserve (name, date, time_start, time_end, purpose, userid) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, reserve.getUserName());
			pStmt.setString(2, reserve.getDate());
			pStmt.setString(3, reserve.getTimeStart());
			pStmt.setString(4, reserve.getTimeEnd());
			pStmt.setString(5, reserve.getPurpose());
			pStmt.setInt(6, reserve.getUserId());

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

	public boolean update(int id, String date, String timeStart, String timeEnd, String purpose) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "UPDATE reserve SET date=?, time_start=?, time_end=?, purpose=? WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, date);
			pStmt.setString(2, timeStart);
			pStmt.setString(3, timeEnd);
			pStmt.setString(4, purpose);
			pStmt.setInt(5, id);

			int result = pStmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("JDBCデータベース接続エラー");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(String delId) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM reserve WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, delId);
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

	//	public boolean ngword(String purpose) {
	//		int count = 0;
	//		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	//			String sql = "SELECT COUNT(word) as count FROM ngword WHERE purpose LIKE ?";
	//			PreparedStatement pStmt = conn.prepareStatement(sql);
	//			pStmt.setString(1, "%" + purpose + "%");
	//			ResultSet rs = pStmt.executeQuery();
	//			rs.next();
	//			count = rs.getInt("count");
	//			System.out.println("ngword_count : " + count); //デバッグ用
	//
	//			if (count != 0) {
	//				return false;
	//			}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//			return false;
	//		}
	//		return true;
	//	}

	public boolean timeCheckDoubling(String date, String timeStart, String timeEnd) {

		int count = 0;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT COUNT(*) as count FROM reserve WHERE date=? and (? between time_start and time_end or ? between time_start and time_end)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, date);
			pStmt.setString(2, timeStart);
			pStmt.setString(3, timeEnd);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			count = rs.getInt("count");

			if (count != 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int timePassedDelete() {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM reserve WHERE date < DATE_FORMAT(NOW(), '%Y-%m-%d') OR (date = DATE_FORMAT(NOW(), '%Y-%m-%d') AND CURTIME() > time_end)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			result = pStmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
}
