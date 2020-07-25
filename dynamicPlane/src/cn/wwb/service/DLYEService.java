package cn.wwb.service;

import java.util.List;
import java.util.Map;

public interface DLYEService {
	public Map<String, Object> search(int currentPage,int lineSize,String FLID)throws Exception;
	
	public List<String> getyanwuCount(List<String> dateList)throws Exception;
}
