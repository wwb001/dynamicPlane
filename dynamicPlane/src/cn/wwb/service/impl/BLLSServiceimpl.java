package cn.wwb.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;
import cn.wwb.service.BLLSService;

public class BLLSServiceimpl implements BLLSService {
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> search(int currentPage, int lineSize, String FLID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("Hangbans", DAOFactory.getBLLSdaoinstance(this.dbc.getConn()).findBySplit(currentPage, lineSize,FLID));
			map.put("counts", DAOFactory.getBLLSdaoinstance(this.dbc.getConn()).getCount(FLID));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<String> getzhuanpanCount(List<String> dateList, String code) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getBLLSdaoinstance(this.dbc.getConn()).getzhuanpanCount(dateList, code);			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

}
