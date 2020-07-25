package cn.wwb.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.wwb.vo.CKLS;

public interface CKLSdao {
	/**
	 * 用于插入航班值机柜台动态信息数据
	 */
	public boolean insertCKLS(List<Map<String, String>> list) throws SQLException;
	
	/**
	 * 表示数据分页功能
	 * @param currentPage 表示当前页
	 * @param lineSize  表示每页显示记录数
	 * @param FLID  表示航班号
	 * @return 
	 * @throws SQLException
	 */
	public List<CKLS> findBySplit(int currentPage,int lineSize,String FLID)throws SQLException;
	
	/**
	 * 统计数据量
	 * @return 有数据返回统计后的数据量，没有返回0
	 * @throws SQLException
	 */
	public int getCount(String FLID)throws SQLException;
}
