package cn.wwb.service;

import java.util.List;
import java.util.Map;

public interface BLLSService {
	public Map<String, Object> search(int currentPage,int lineSize,String FLID)throws Exception;
	
	public List<String> getzhuanpanCount(List<String> dateList, String code)throws Exception;
}
