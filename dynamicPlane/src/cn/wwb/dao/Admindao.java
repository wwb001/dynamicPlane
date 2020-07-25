package cn.wwb.dao;

import java.sql.SQLException;

import cn.wwb.vo.Admin;

public interface Admindao {
	/**
	 * 插入管理员用户
	 */
	public boolean insertAdmin(Admin admin) throws SQLException;
	/**
	 * 查找管理员用户是否存在1
	 */
	public int selectAdmin(String name, String password) throws SQLException;
	/**
	 * 查找管理员头像
	 */
	public String selectpicture(String name) throws SQLException;
	/**
	 * 查找管理员是否存在2
	 */
	public int selectadmin(String name, String tel) throws SQLException;
}
