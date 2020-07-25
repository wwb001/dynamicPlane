package cn.wwb.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.wwb.vo.DFDL;

public interface DFDLdao {
	public boolean insertDFDL(List<Map<String, String>> list) throws SQLException;
	/**
	 * 表示数据分页功能
	 * @param currentPage 表示当前页
	 * @param lineSize  表示每页显示记录数
	 * @param FLID  表示航班号
	 * @return 
	 * @throws SQLException
	 */
	public List<DFDL> findBySplit(int currentPage,int lineSize,String FLID)throws SQLException;
	
	/**
	 * 统计数据量
	 * @return 有数据返回统计后的数据量，没有返回0
	 * @throws SQLException
	 */
	public int getCount(String FLID)throws SQLException;
	
	/**
	 * 统计每日数据量
	 * @return 有数据返回统计后的数据量，没有返回0
	 * @throws SQLException
	 */
	public List<String> getPerCount(List<String> dateList)throws SQLException;
	
	public List<String> getyingdaogangCount(List<String> dateList)throws SQLException;
	
	public List<String> getyingligangCount(List<String> dateList)throws SQLException;
	
	public List<String> getguoneidaogangCount(List<String> dateList)throws SQLException;
	
	public List<String> getguojidaogangCount(List<String> dateList)throws SQLException;
	
	public List<String> gethunhedaogangCount(List<String> dateList)throws SQLException;
	
	public List<String> getdiqudaogangCount(List<String> dateList)throws SQLException;
	
	public List<String> getguoneiligangCount(List<String> dateList)throws SQLException;
	
	public List<String> getguojiligangCount(List<String> dateList)throws SQLException;
	
	public List<String> gethunheligangCount(List<String> dateList)throws SQLException;
	
	public List<String> getdiquligangCount(List<String> dateList)throws SQLException;
}

