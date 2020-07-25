package cn.wwb.service;

import java.sql.SQLException;

import cn.wwb.vo.Admin;

public interface AdminService {
	public boolean registerAdmin(Admin admin) throws SQLException; 
	public int loginAdmin(String name, String password) throws SQLException; 
	public String selectPath(String name) throws SQLException;
	public int selectAdmin(String name, String tel) throws SQLException;
}
