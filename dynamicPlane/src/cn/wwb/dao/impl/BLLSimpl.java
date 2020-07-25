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

import cn.wwb.dao.BLLSdao;
import cn.wwb.vo.BLLS;

public class BLLSimpl implements BLLSdao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public BLLSimpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertBLLS(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into BLLS(FLID, FFID, FATT, BTNO, ID, CODE, BTAT, ESTR, EEND, RSTR, REND, BTSC) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("FFID"));
			this.pstmt.setString(3, map.get("FATT"));
			this.pstmt.setString(4, map.get("BTNO"));
			this.pstmt.setString(5, map.get("ID"));	
			this.pstmt.setString(6, map.get("CODE"));
			this.pstmt.setString(7, map.get("BTAT"));
			this.pstmt.setString(8, map.get("ESTR"));
			this.pstmt.setString(9, map.get("EEND"));
			this.pstmt.setString(10, map.get("RSTR"));
			this.pstmt.setString(11, map.get("REND"));
			this.pstmt.setString(12, map.get("BTSC"));
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public List<BLLS> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<BLLS> all = new ArrayList<BLLS>();
		String sql = "select distinct FLID, FFID, FATT, BTNO, ID, CODE, BTAT, ESTR, EEND, RSTR, REND, BTSC from blls where EEND <=? and FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, dateString);
		this.pstmt.setString(2, "%" + FLID + "%");
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		this.pstmt.setInt(4, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new BLLS();
			vo.setFLID(rs.getString(1));
			vo.setFFID(rs.getString(2));
			vo.setFATT(rs.getString(3));
			vo.setBTNO(rs.getString(4));
			vo.setID(rs.getString(5));
			vo.setCODE(rs.getString(6));
			vo.setBTAT(rs.getString(7));
			vo.setESTR(rs.getString(8));
			vo.setEEND(rs.getString(9));
			vo.setRSTR(rs.getString(10));
			vo.setREND(rs.getString(11));
			vo.setBTSC(rs.getString(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public int getCount(String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String sql = "SELECT COUNT(distinct FLID, FFID, FATT, BTNO, ID, CODE, BTAT, ESTR, EEND, RSTR, REND, BTSC) FROM blls where FLID like ? and EEND <= ?";
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
	public List<String> getzhuanpanCount(List<String> dateList, String code) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add(code);
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(*) FROM BLLS where ESTR >= ? and ESTR <= ? and ESTR <= ? and CODE = ?";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, code);
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}

}
