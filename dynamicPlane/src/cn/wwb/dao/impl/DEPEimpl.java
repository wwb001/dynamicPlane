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

import cn.wwb.dao.DEPEdao;
import cn.wwb.vo.DEPE;

public class DEPEimpl implements DEPEdao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public DEPEimpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertDEPE(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into DEPE(FLID, FFID, FATT, STAT, ISTA, FRTT) values (?,?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("FFID"));
			this.pstmt.setString(3, map.get("FATT"));
			this.pstmt.setString(4, map.get("STAT"));
			this.pstmt.setString(5, map.get("ISTA"));	
			this.pstmt.setString(6, map.get("FRTT"));
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public List<DEPE> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<DEPE> all = new ArrayList<DEPE>();
		String sql = "select distinct FLID, FFID, FATT, STAT, ISTA, FRTT from depe where FRTT <=? and FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, dateString);
		this.pstmt.setString(2, "%" + FLID + "%");
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		this.pstmt.setInt(4, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new DEPE();
			vo.setFLID(rs.getString(1));
			vo.setFFID(rs.getString(2));
			vo.setFATT(rs.getString(3));
			vo.setSTAT(rs.getString(4));
			vo.setISTA(rs.getString(5));
			vo.setFRTT(rs.getString(6));
			all.add(vo);
		}
		return all;
	}

	@Override
	public int getCount(String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String sql = "SELECT COUNT(distinct FLID, FFID, FATT, STAT, ISTA, FRTT) FROM depe where FLID like ? and FRTT <= ?";
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
	public List<String> getyiligangCount(List<String> dateList) throws SQLException {
		List<String> countList = new ArrayList<String>();
		countList.add("已离港");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		for (String string : dateList) {
			String sql = "SELECT COUNT(distinct FLID) FROM depe where FRTT like ? and FRTT <= ?";
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
	public List<String> getligangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("离港航班");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM depe where FRTT >= ? and FRTT <= ? and FRTT <= ?";
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
