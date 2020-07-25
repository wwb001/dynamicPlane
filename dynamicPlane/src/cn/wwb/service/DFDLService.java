package cn.wwb.service;

import java.util.List;
import java.util.Map;

public interface DFDLService {
	public Map<String, Object> search(int currentPage,int lineSize,String FLID)throws Exception;
	
	public Map<String, Object> getPerCount(List<String> dateList)throws Exception;
	
	public Map<String, Object> getyingdaogangCount(List<String> dateList)throws Exception;
	
	public Map<String, Object> getyingligangCount(List<String> dateList)throws Exception;
	
	public List<String> getguoneidaogangCount(List<String> dateList)throws Exception;
	
	public List<String> getguojidaogangCount(List<String> dateList)throws Exception;
	
	public List<String> gethunhedaogangCount(List<String> dateList)throws Exception;
	
	public List<String> getdiqudaogangCount(List<String> dateList)throws Exception;
	
	public List<String> getguoneiligangCount(List<String> dateList)throws Exception;
	
	public List<String> getguojiligangCount(List<String> dateList)throws Exception;
	
	public List<String> gethunheligangCount(List<String> dateList)throws Exception;
	
	public List<String> getdiquligangCount(List<String> dateList)throws Exception;
}
