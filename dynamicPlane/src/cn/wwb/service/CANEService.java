package cn.wwb.service;

import java.util.Map;

public interface CANEService {
	public Map<String, Object> search(int currentPage,int lineSize,String FLID)throws Exception;
}
