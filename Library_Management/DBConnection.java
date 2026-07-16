package com.Library_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class DBConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/Librarydb_dao";
	private static final String user = "Your Username";
	private static final String password = "your Password";
	static Scanner sc = new Scanner(System.in);
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return c;
		};
	
}
