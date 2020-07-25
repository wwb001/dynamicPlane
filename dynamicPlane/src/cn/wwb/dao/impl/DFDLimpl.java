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

import cn.wwb.vo.DFDL;

public class DFDLimpl implements cn.wwb.dao.DFDLdao {
	private Connection connection;
	private PreparedStatement pstmt;
	
	public DFDLimpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public boolean insertDFDL(List<Map<String, String>> list) throws SQLException {
		for (Map<String, String> map : list) {
			String sql = "insert into DFDL(FLID, AFID, FFID, AWCD, FLNO, FEXD, FLIO, FLTK, FATT, CFTP, CFNO, ABST, ABRS, IAST, IARS, 起飞站APNO,"
					+ " 起飞站航站三字码,  起飞站计划起飞时间,起飞站预计起飞时间,起飞站实际起飞时间,中转站APNO,中转站航站三字码,中转站计划起飞时间,中转站预计起飞时间"
					+ ",中转站实际起飞时间,中转站计划降落时间,中转站预计降落时间,中转站实际降落时间,目的站APNO,目的站航站三字码,目的站计划降落时间"
					+ ",目的站预计降落时间,目的站实际降落时间) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, map.get("FLID"));
			this.pstmt.setString(2, map.get("AFID"));
			this.pstmt.setString(3, map.get("FFID"));
			this.pstmt.setString(4, map.get("AWCD"));
			this.pstmt.setString(5, map.get("FLNO"));	
			this.pstmt.setString(6, map.get("FEXD"));
			this.pstmt.setString(7, map.get("FLIO"));
			this.pstmt.setString(8, map.get("FLTK"));
			this.pstmt.setString(9, map.get("FATT"));
			this.pstmt.setString(10, map.get("CFTP"));
			this.pstmt.setString(11, map.get("CFNO"));
			this.pstmt.setString(12, map.get("BTSC"));
			this.pstmt.setString(13, map.get("ABRS"));
			this.pstmt.setString(14, map.get("IAST"));
			this.pstmt.setString(15, map.get("IARS"));
			this.pstmt.setString(16, map.get("起飞站APNO"));
			this.pstmt.setString(17, map.get("起飞站航站三字码"));	
			this.pstmt.setString(18, map.get("起飞站计划起飞时间"));
			this.pstmt.setString(19, map.get("起飞站预计起飞时间"));
			this.pstmt.setString(20, map.get("起飞站实际起飞时间"));
			this.pstmt.setString(21, map.get("中转站APNO"));
			this.pstmt.setString(22, map.get("中转站航站三字码"));
			this.pstmt.setString(23, map.get("中转站计划起飞时间"));
			this.pstmt.setString(24, map.get("中转站预计起飞时间"));
			this.pstmt.setString(25, map.get("中转站实际起飞时间"));	
			this.pstmt.setString(26, map.get("中转站计划降落时间"));
			this.pstmt.setString(27, map.get("中转站预计降落时间"));
			this.pstmt.setString(28, map.get("中转站实际降落时间"));
			this.pstmt.setString(29, map.get("目的站APNO"));
			this.pstmt.setString(30, map.get("目的站航站三字码"));
			this.pstmt.setString(31, map.get("目的站计划降落时间"));
			this.pstmt.setString(32, map.get("目的站预计降落时间"));
			this.pstmt.setString(33, map.get("目的站实际降落时间"));
			this.pstmt.executeUpdate();
		}
		return this.pstmt.executeUpdate() > 0;
	}
	@Override
	public List<DFDL> findBySplit(int currentPage, int lineSize, String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<DFDL> all = new ArrayList<DFDL>();
		String sql = "select distinct FLID, AFID, FFID, AWCD, FLNO, FEXD, FLIO, FLTK, FATT, CFTP, CFNO, ABRS, IAST, IARS, 起飞站APNO,起飞站航站三字码,  起飞站计划起飞时间,中转站APNO,中转站航站三字码,中转站计划起飞时间,中转站计划降落时间,目的站APNO,目的站航站三字码,目的站计划降落时间 from dfdl where 目的站计划降落时间 <=? and FLID like ? limit ?,?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, dateString);
		this.pstmt.setString(2, "%" + FLID + "%");
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		this.pstmt.setInt(4, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			var vo = new DFDL();
			vo.setFLID(rs.getString(1));
			vo.setAFID(rs.getString(2));
			vo.setFFID(rs.getString(3));
			vo.setAWCD(rs.getString(4));
			vo.setFLNO(rs.getString(5));
			vo.setFEXD(rs.getString(6));
			vo.setFLIO(rs.getString(7));
			vo.setFLTK(rs.getString(8));
			vo.setFATT(rs.getString(9));
			vo.setCFTP(rs.getString(10));
			vo.setCFNO(rs.getString(11));
			vo.setABRS(rs.getString(12));
			vo.setIAST(rs.getString(13));
			vo.setIARS(rs.getString(14));
			vo.set起飞站APNO(rs.getString(15));
			vo.set起飞站航站三字码(rs.getString(16));
			vo.set起飞站计划起飞时间(rs.getString(17));
			vo.set中转站APNO(rs.getString(18));
			vo.set中转站航站三字码(rs.getString(19));
			vo.set中转站计划起飞时间(rs.getString(20));
			vo.set中转站计划降落时间(rs.getString(21));
			vo.set目的站APNO(rs.getString(22));
			vo.set目的站航站三字码(rs.getString(23));
			vo.set目的站计划降落时间(rs.getString(24));
			all.add(vo);
		}
		return all;
	}
	@Override
	public int getCount(String FLID) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String sql = "SELECT COUNT(distinct FLID, AFID, FFID, AWCD, FLNO, FEXD, FLIO, FLTK, FATT, CFTP, CFNO, ABRS, IAST, IARS, 起飞站APNO,起飞站航站三字码,  起飞站计划起飞时间,中转站APNO,中转站航站三字码,中转站计划起飞时间,中转站计划降落时间,目的站APNO,目的站航站三字码,目的站计划降落时间) FROM dfdl where FLID like ? and 目的站计划降落时间 <= ?";
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
	public List<String> getPerCount(List<String> dateList) throws SQLException {
		List<String> countList = new ArrayList<String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		for (String string : dateList) {
			String sql = "SELECT COUNT(distinct FLID, AFID, FFID, AWCD, FLNO, FEXD, FLIO, FLTK, FATT, CFTP, CFNO, ABRS, IAST, IARS, 起飞站APNO,起飞站航站三字码,  起飞站计划起飞时间,中转站APNO,中转站航站三字码,中转站计划起飞时间,中转站计划降落时间,目的站APNO,目的站航站三字码,目的站计划降落时间) FROM dfdl where 目的站计划降落时间  like ? and 目的站计划降落时间 <= ?";
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
	public List<String> getyingdaogangCount(List<String> dateList) throws SQLException {
		List<String> countList = new ArrayList<String>();
		countList.add("应到港");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		for (String string : dateList) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 目的站计划降落时间  like ? and 目的站计划降落时间 <= ? and FLIO = ?";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, "%" + string + "%");
			this.pstmt.setString(2, dateString);
			this.pstmt.setString(3, "A");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> getyingligangCount(List<String> dateList) throws SQLException {
		List<String> countList = new ArrayList<String>();
		countList.add("应离港");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		for (String string : dateList) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 目的站计划降落时间  like ? and 目的站计划降落时间 <= ? and FLIO = ?";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, "%" + string + "%");
			this.pstmt.setString(2, dateString);
			this.pstmt.setString(3, "D");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> getguoneidaogangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("国内到港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 目的站计划降落时间 >= ? and 目的站计划降落时间 <= ? and 目的站计划降落时间 <= ? and FLIO = ? and FATT = '2403'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "A");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> getguojidaogangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("国际到港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 目的站计划降落时间 >= ? and 目的站计划降落时间 <= ? and 目的站计划降落时间 <= ? and FLIO = ? and FATT = '2401'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "A");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> gethunhedaogangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("混合到港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 目的站计划降落时间 >= ? and 目的站计划降落时间 <= ? and 目的站计划降落时间 <= ? and FLIO = ? and FATT = '2404'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "A");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> getdiqudaogangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("地区到港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 目的站计划降落时间 >= ? and 目的站计划降落时间 <= ? and 目的站计划降落时间 <= ? and FLIO = ? and FATT = '2402'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "A");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> getguoneiligangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("国内离港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 起飞站计划起飞时间 >= ? and 起飞站计划起飞时间 <= ? and 起飞站计划起飞时间 <= ? and FLIO = ? and FATT = '2403'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "D");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> getguojiligangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("国际离港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 起飞站计划起飞时间 >= ? and 起飞站计划起飞时间 <= ? and 起飞站计划起飞时间 <= ? and FLIO = ? and FATT = '2401'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "D");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> gethunheligangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("混合离港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 起飞站计划起飞时间 >= ? and 起飞站计划起飞时间 <= ? and 起飞站计划起飞时间 <= ? and FLIO = ? and FATT = '2404'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "D");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}
	@Override
	public List<String> getdiquligangCount(List<String> dateList) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		List<String> countList = new ArrayList<String>();
		countList.add("地区离港");
		for (int i = 0; i < dateList.size()-1; i++) {
			String sql = "SELECT COUNT(distinct FLID) FROM dfdl where 起飞站计划起飞时间 >= ? and 起飞站计划起飞时间 <= ? and 起飞站计划起飞时间 <= ? and FLIO = ? and FATT = '2402'";
			this.pstmt = this.connection.prepareStatement(sql);
			this.pstmt.setString(1, dateList.get(i));
			this.pstmt.setString(2, dateList.get(i+1));
			this.pstmt.setString(3, dateString);
			this.pstmt.setString(4, "D");
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				countList.add(rs.getString(1));
			}
		}
		return countList;
	}

}
