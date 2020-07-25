package cn.wwb.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/project?useSSL=true&?useUnicode=true&characterEncoding=UTF-8";
	private static final String DBUSER= "root";
	private static final String DBPASS = "123456";
	private Connection conn;
	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER);
			try {
				this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return this.conn;
	}
	public void close() {
		if(this.conn!=null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		System.out.println(new DatabaseConnection().getConn());
	}
}
