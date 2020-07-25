package cn.wwb.service;

import java.util.List;
import java.util.Map;

public interface ARREService {
	public Map<String, Object> search(int currentPage,int lineSize,String FLID)throws Exception;
	
	public Map<String, Object> getyidaogangCount(List<String> dateList)throws Exception;
	
	public List<String> getdaogangCount(List<String> dateList)throws Exception;
}
