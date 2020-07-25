package cn.wwb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.wwb.dao.CANEdao;
import cn.wwb.vo.CANE;

public class CANEimpl implements CANEdao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public CANEimpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertCANE(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into CANE(FLID, FFID, FATT, ABST, IAST, ABRS, IARS) values (?,?,?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("FFID"));
			this.pstmt.setString(3, map.get("FATT"));
			this.pstmt.setString(4, map.get("ABST"));
			this.pstmt.setString(5, map.get("IAST"));	
			this.pstmt.setString(6, map.get("ABRS"));
			this.pstmt.setString(7, map.get("IARS"));
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public List<CANE> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		List<CANE> all = new ArrayList<CANE>();
		String sql = "select distinct FLID, FFID, FATT, ABST, IAST, ABRS, IARS from cane where FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, "%" + FLID + "%");
		this.pstmt.setInt(2, (currentPage - 1) * lineSize);
		this.pstmt.setInt(3, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new CANE();
			vo.setFLID(rs.getString(1));
			vo.setFFID(rs.getString(2));
			vo.setFATT(rs.getString(3));
			vo.setABST(rs.getString(4));
			vo.setIAST(rs.getString(5));
			vo.setABRS(rs.getString(6));
			vo.setIARS(rs.getString(7));
			all.add(vo);
		}
		return all;
	}

	@Override
	public int getCount(String FLID) throws SQLException {
		String sql = "SELECT COUNT(distinct FLID, FFID, FATT, ABST, IAST, ABRS, IARS) FROM cane where FLID like ?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, "%" + FLID + "%");
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

}
