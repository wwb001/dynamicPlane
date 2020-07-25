package cn.wwb.service;

import java.util.Map;

public interface STLSService {
	public Map<String, Object> search(int currentPage,int lineSize,String FLID)throws Exception;
}
