package com.amdocs.myproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER_NAME="sayan";
	private static final String PASSWORD="pass1";

	private static Connection connection;	
	
	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("Connection Success");
		} catch (SQLException e) {
			System.err.println("Error : Connection Not Established\n" + e );
		}
		return connection;
	}

}
