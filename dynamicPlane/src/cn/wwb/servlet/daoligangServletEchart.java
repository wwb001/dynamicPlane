package cn.wwb.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wwb.factory.ServiceFactory;
import cn.wwb.util.Date2;

/**
 * Servlet implementation class daoligangServletEchart
 */
@WebServlet("/daoligangServletEchart")
public class daoligangServletEchart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public daoligangServletEchart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dBegin;
		List<String> dateList = new ArrayList<String>();
		List<String> datelist = new ArrayList<String>();
		List<String> yingdaogangcountList = new ArrayList<String>();
		List<String> yingligangcountList = new ArrayList<String>();
		List<String> yidaogangcountList = new ArrayList<String>();
		List<String> yiligangcountList = new ArrayList<String>();
		String date = null;
		try {
			dBegin = sdf.parse("20170601");
			date = sdf.format(new Date());
			Date dEnd = sdf.parse(date);
			dateList = Date2.findDates(dBegin, dEnd);
			datelist = Date2.findDates(dBegin, dEnd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject();
		try {
			datelist.add(0, "product");
			Map<String, Object> map1 = ServiceFactory.getDFDLServiceinstance().getyingdaogangCount(dateList);
			obj.put("datelist", datelist);
			obj.put("yingdaogangcountList", map1.get("yingdaogangcountList"));
			Map<String, Object> map2 = ServiceFactory.getDFDLServiceinstance().getyingligangCount(dateList);
			obj.put("yingligangcountList", map2.get("yingligangcountList"));
			Map<String, Object> map3 = ServiceFactory.getARREServiceinstance().getyidaogangCount(dateList);
			obj.put("yidaogangcountList", map3.get("yidaogangcountList"));
			Map<String, Object> map4 = ServiceFactory.getDEPEServiceinstance().getyiligangCount(dateList);
			obj.put("yiligangcountList", map4.get("yiligangcountList"));
			obj.put("date", date);
			response.getWriter().print(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
