package cn.wwb.service.impl;

import java.sql.SQLException;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;
import cn.wwb.service.AdminService;
import cn.wwb.vo.Admin;

public class AdminServiceimpl implements AdminService{

	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean registerAdmin(Admin admin) throws SQLException{
		return DAOFactory.Admindaoinstance(dbc.getConn()).insertAdmin(admin);
	}

	@Override
	public int loginAdmin(String name, String password) throws SQLException{
		return DAOFactory.Admindaoinstance(dbc.getConn()).selectAdmin(name, password);
	}

	@Override
	public String selectPath(String name) throws SQLException {
		return DAOFactory.Admindaoinstance(dbc.getConn()).selectpicture(name);
	}

	@Override
	public int selectAdmin(String name, String tel) throws SQLException {
		return DAOFactory.Admindaoinstance(dbc.getConn()).selectadmin(name, tel);
	}

}
