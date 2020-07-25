package cn.wwb.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;
import cn.wwb.service.DFDLService;

public class DFDLServiceimpl implements DFDLService{
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> search(int currentPage, int lineSize, String FLID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("Hangbans", DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).findBySplit(currentPage, lineSize,FLID));
			map.put("counts", DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getCount(FLID));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> getPerCount(List<String> dateList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("dateList", dateList);
			map.put("countList", DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getPerCount(dateList));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> getyingdaogangCount(List<String> dateList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("dateList", dateList);
			map.put("yingdaogangcountList", DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getyingdaogangCount(dateList));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> getyingligangCount(List<String> dateList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("dateList", dateList);
			map.put("yingligangcountList", DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getyingligangCount(dateList));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<String> getguoneidaogangCount(List<String> dateList) throws Exception{
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getguoneidaogangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<String> getguojidaogangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getguojidaogangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<String> gethunhedaogangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).gethunhedaogangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<String> getdiqudaogangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getdiqudaogangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<String> getguoneiligangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getguoneiligangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<String> getguojiligangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getguojiligangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<String> gethunheligangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).gethunheligangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<String> getdiquligangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDFDLdaoinstance(this.dbc.getConn()).getdiquligangCount(dateList);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

}
