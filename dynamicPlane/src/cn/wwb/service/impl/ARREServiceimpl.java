package cn.wwb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;
import cn.wwb.service.ARREService;

public class ARREServiceimpl implements ARREService {
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> search(int currentPage, int lineSize, String FLID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("Hangbans", DAOFactory.getARREdaoinstance(this.dbc.getConn()).findBySplit(currentPage, lineSize,FLID));
			map.put("counts", DAOFactory.getARREdaoinstance(this.dbc.getConn()).getCount(FLID));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> getyidaogangCount(List<String> dateList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("dateList", dateList);
			map.put("yidaogangcountList", DAOFactory.getARREdaoinstance(this.dbc.getConn()).getyidaogangCount(dateList));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<String> getdaogangCount(List<String> dateList) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			list = DAOFactory.getARREdaoinstance(this.dbc.getConn()).getdaogangCount(dateList);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return list;
	}

}
