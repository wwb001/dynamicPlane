package cn.wwb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;
import cn.wwb.service.DEPEService;

public class DEPEServiceimpl implements DEPEService {
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> search(int currentPage, int lineSize, String FLID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("Hangbans", DAOFactory.getDEPEdaoinstance(this.dbc.getConn()).findBySplit(currentPage, lineSize,FLID));
			map.put("counts", DAOFactory.getDEPEdaoinstance(this.dbc.getConn()).getCount(FLID));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> getyiligangCount(List<String> dateList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("dateList", dateList);
			map.put("yiligangcountList", DAOFactory.getDEPEdaoinstance(this.dbc.getConn()).getyiligangCount(dateList));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<String> getligangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getDEPEdaoinstance(this.dbc.getConn()).getligangCount(dateList);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

}
