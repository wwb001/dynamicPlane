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

import cn.wwb.dao.ARREdao;
import cn.wwb.vo.ARRE;

public class ARREimpl implements ARREdao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public ARREimpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertARRE(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into ARRE(FLID) values (?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("FATT"));
			this.pstmt.setString(3, map.get("FFID"));
			this.pstmt.setString(4, map.get("ISTA"));
			this.pstmt.setString(5, map.get("FRLT"));	
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;	
	}

	@Override
	public List<ARRE> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<ARRE> all = new ArrayList<ARRE>();
		String sql = "select distinct FLID,FATT, FFID, ISTA, FRLT from arre where FRLT <=? and FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, dateString);
		this.pstmt.setString(2, "%" + FLID + "%");
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		this.pstmt.setInt(4, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new ARRE();
			vo.setFLID(rs.getString(1));
			vo.setFATT(rs.getString(2));
			vo.setFFID(rs.getString(3));
			vo.setISTA(rs.getString(4));
			vo.setFRLT(rs.getString(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public int getCount(String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String sql = "SELECT COUNT(distinct FLID,FATT, FFID, ISTA, FRLT) FROM arre where FLID like ? and FRLT <= ?";
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
	public List<String> getyidaogangCount(List<String> dateList) throws SQLException {
		List<String> countList = new ArrayList<String>();
		countList.add("已到港");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		for (String string : dateList) {
			String sql = "SELECT COUNT(distinct FLID,FATT, FFID, ISTA, FRLT) FROM arre where FRLT like ? and FRLT <= ?";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, "%" + string + "%");
			this.pstmt.setString(2, dateString);
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}

	@Override
	public List<String> getdaogangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("到港航班");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM arre where FRLT >= ? and FRLT <= ? and FRLT <= ?";
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
