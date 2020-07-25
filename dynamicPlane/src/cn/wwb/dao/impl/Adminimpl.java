package cn.wwb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.wwb.dao.Admindao;
import cn.wwb.vo.Admin;

public class Adminimpl implements Admindao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public Adminimpl( Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertAdmin(Admin admin) throws SQLException {
		String name = admin.getName();
		String pwd = admin.getPwd();
		String tel = admin.getTel();
		String path = admin.getPath();
		String sql = "insert into admin(name, password, telephone, path) values(?, HEX(AES_ENCRYPT(?, 'abc')), ?, ?)";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, name);
		this.pstmt.setString(2, pwd);
		this.pstmt.setString(3, tel);
		this.pstmt.setString(4, path);
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public int selectAdmin(String name, String password) throws SQLException {
		String sql = "select * from admin where name = ? and AES_DECRYPT(UNHEX(password),'abc')=?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, name);
		this.pstmt.setString(2, password);
		ResultSet rs = this.pstmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;
		}
		return count;
	}

	@Override
	public String selectpicture(String name) throws SQLException {
		String sql = "select path from admin where name = ?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, name);
		ResultSet rs = this.pstmt.executeQuery();
		String path = null;
		while (rs.next()) {
			path = rs.getString(1);
		}
		return path;
	}

	@Override
	public int selectadmin(String name, String tel) throws SQLException {
		String sql = "select * from admin where name = ? or telephone =?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, name);
		this.pstmt.setString(2, tel);
		ResultSet rs = this.pstmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;
		}
		return count;
	}


}
