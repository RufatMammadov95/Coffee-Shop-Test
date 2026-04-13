package com.example.coffee_shop.db;

import java.sql.*;

public class DatabaseUtil {

	private static final String URL = "jdbc:sqlite:coffee_chat.db";

	static {
		try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {

			String chatTableSql = """
					    CREATE TABLE IF NOT EXISTS chat_history (
					        id INTEGER PRIMARY KEY AUTOINCREMENT,
					        sender TEXT NOT NULL,
					        message TEXT NOT NULL,
					        timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
					    );
					""";

			String imageTableSql = """
					    CREATE TABLE IF NOT EXISTS uploaded_images (
					        id INTEGER PRIMARY KEY AUTOINCREMENT,
					        image_path TEXT NOT NULL,
					        timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
					    );
					""";

			stmt.execute(chatTableSql);
			stmt.execute(imageTableSql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SAVE CHAT MESSAGE
	public static void saveMessage(String sender, String message) {
		String sql = "INSERT INTO chat_history(sender, message) VALUES(?, ?)";

		try (Connection conn = DriverManager.getConnection(URL); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, sender);
			ps.setString(2, message);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SAVE IMAGE PATH
	public static void saveImage(String path) {
		String sql = "INSERT INTO uploaded_images(image_path) VALUES(?)";

		try (Connection conn = DriverManager.getConnection(URL); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, path);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GET CHAT HISTORY
	public static String getChatHistory() {
		StringBuilder history = new StringBuilder();

		String sql = "SELECT * FROM chat_history ORDER BY id";

		try (Connection conn = DriverManager.getConnection(URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				history.append(rs.getString("timestamp")).append(" | ").append(rs.getString("sender")).append(": ")
						.append(rs.getString("message")).append("\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return history.toString();
	}

	// OPTIONAL CONSOLE PRINT
	public static void printChatHistory() {
		System.out.print(getChatHistory());
	}

	public static String getImages() {
		StringBuilder sb = new StringBuilder();

		String sql = "SELECT * FROM uploaded_images ORDER BY id";

		try (Connection conn = DriverManager.getConnection(URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				sb.append(rs.getString("image_path")).append("\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}