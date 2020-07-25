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
 * Servlet implementation class DFDLServletEchart
 */
@WebServlet("/DFDLServletEchart")
public class DFDLServletEchart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DFDLServletEchart() {
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
		List<String> countList = new ArrayList<String>();
		String date = null;
		try {
			dBegin = sdf.parse("20170601");
			date = sdf.format(new Date());
			Date dEnd = sdf.parse(date);
			dateList = Date2.findDates(dBegin, dEnd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject();
		try {
			Map<String, Object> map = ServiceFactory.getDFDLServiceinstance().getPerCount(dateList);
			obj.put("dateList", map.get("dateList"));
			obj.put("countList", map.get("countList"));
			obj.put("date", date);
			response.getWriter().print(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
