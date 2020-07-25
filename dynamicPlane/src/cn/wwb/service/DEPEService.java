package cn.wwb.service;

import java.util.List;
import java.util.Map;

public interface DEPEService {
	public Map<String, Object> search(int currentPage,int lineSize,String FLID)throws Exception;
	
	public Map<String, Object> getyiligangCount(List<String> dateList)throws Exception;
	
	public List<String> getligangCount(List<String> dateList)throws Exception;
}
