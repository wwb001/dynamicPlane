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

import cn.wwb.dao.STLSdao;
import cn.wwb.vo.STLS;

public class STLSimpl implements STLSdao {
	private Connection connection;
	private PreparedStatement pstmt;
	public STLSimpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertSTLS(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into STLS(FLID, FFID, FATT, STNO, CODE, ESTR, EEND, RSTR, REND) values (?,?,?,?,?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("FFID"));
			this.pstmt.setString(3, map.get("FATT"));
			this.pstmt.setString(4, map.get("STNO"));
			this.pstmt.setString(5, map.get("CODE"));	
			this.pstmt.setString(6, map.get("ESTR"));
			this.pstmt.setString(7, map.get("EEND"));
			this.pstmt.setString(8, map.get("RSTR"));
			this.pstmt.setString(9, map.get("REND"));
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public List<STLS> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<STLS> all = new ArrayList<STLS>();
		String sql = "select distinct FLID, FFID, FATT, STNO, CODE, ESTR, EEND, RSTR, REND from stls where EEND <=? and FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, dateString);
		this.pstmt.setString(2, "%" + FLID + "%");
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		this.pstmt.setInt(4, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new STLS();
			vo.setFLID(rs.getString(1));
			vo.setFFID(rs.getString(2));
			vo.setFATT(rs.getString(3));
			vo.setSTNO(rs.getString(4));
			vo.setCODE(rs.getString(5));
			vo.setESTR(rs.getString(6));
			vo.setEEND(rs.getString(7));
			vo.setRSTR(rs.getString(8));
			vo.setREND(rs.getString(9));
			all.add(vo);
		}
		return all;
	}

	@Override
	public int getCount(String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String sql = "SELECT COUNT(distinct FLID, FFID, FATT, STNO, CODE, ESTR, EEND, RSTR, REND) FROM stls where FLID like ? and EEND <= ?";
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
