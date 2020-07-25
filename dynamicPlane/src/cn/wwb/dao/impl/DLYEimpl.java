package cn.wwb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.wwb.dao.DLYEdao;
import cn.wwb.vo.DLYE;

public class DLYEimpl implements DLYEdao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public DLYEimpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertDLYE(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into DLYE(FLID, FATT, FFID, ABST, IAST, FETT) values (?,?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("FATT"));
			this.pstmt.setString(3, map.get("FFID"));
			this.pstmt.setString(4, map.get("ABST"));
			this.pstmt.setString(5, map.get("IAST"));	
			this.pstmt.setString(6, map.get("FETT"));
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public List<DLYE> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<DLYE> all = new ArrayList<DLYE>();
		String sql = "select distinct FLID, FATT, FFID, ABST, IAST, FETT from dlye where FETT <=? and FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, dateString);
		this.pstmt.setString(2, "%" + FLID + "%");
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		this.pstmt.setInt(4, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new DLYE();
			vo.setFLID(rs.getString(1));
			vo.setFATT(rs.getString(2));
			vo.setFFID(rs.getString(3));
			vo.setABST(rs.getString(4));
			vo.setIAST(rs.getString(5));
			vo.setFETT(rs.getString(6));
			all.add(vo);
		}
		return all;
	}

	@Override
	public int getCount(String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String sql = "SELECT COUNT(distinct FLID, FATT, FFID, ABST, IAST, FETT) FROM dlye where FLID like ? and FETT <= ?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, "%" + FLID + "%");
		this.pstmt.setString(2, dateString);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public List<String> getyanwuCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("延误航班");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dlye where FETT >= ? and FETT <= ? and FETT <= ?";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}

}
