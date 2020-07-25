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

import cn.wwb.dao.CKLSdao;
import cn.wwb.vo.CKLS;

public class CKLSimpl implements CKLSdao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public CKLSimpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertCKLS(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into CKLS(FLID, FFID, FATT, FCES, FCEE, CODE, CKAT, BTSC) values (?,?,?,?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("FFID"));
			this.pstmt.setString(3, map.get("FATT"));
			this.pstmt.setString(4, map.get("FCES"));
			this.pstmt.setString(5, map.get("FCEE"));	
			this.pstmt.setString(6, map.get("CODE"));
			this.pstmt.setString(7, map.get("CKAT"));
			this.pstmt.setString(8, map.get("BTSC"));
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public List<CKLS> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<CKLS> all = new ArrayList<CKLS>();
		String sql = "select distinct FLID, FFID, FATT, FCES, FCEE, CODE, CKAT, BTSC from ckls where FCEE <=? and FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, dateString);
		this.pstmt.setString(2, "%" + FLID + "%");
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		this.pstmt.setInt(4, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new CKLS();
			vo.setFLID(rs.getString(1));
			vo.setFFID(rs.getString(2));
			vo.setFATT(rs.getString(3));
			vo.setFCES(rs.getString(4));
			vo.setFCEE(rs.getString(5));
			vo.setCODE(rs.getString(6));
			vo.setCKAT(rs.getString(7));
			vo.setBTSC(rs.getString(8));
			all.add(vo);
		}
		return all;
	}

	@Override
	public int getCount(String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String sql = "SELECT COUNT(distinct FLID, FFID, FATT, FCES, FCEE, CODE, CKAT, BTSC) FROM ckls where FLID like ? and FCEE <= ?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, "%" + FLID + "%");
		this.pstmt.setString(2, dateString);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

}
