package cn.wwb.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;
import cn.wwb.service.GTLSService;

public class GTLSServiceimpl implements GTLSService {

	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> search(int currentPage, int lineSize, String FLID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("Hangbans", DAOFactory.getGTLSdaoinstance(this.dbc.getConn()).findBySplit(currentPage, lineSize,FLID));
			map.put("counts", DAOFactory.getGTLSdaoinstance(this.dbc.getConn()).getCount(FLID));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
