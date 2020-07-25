package cn.wwb.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wwb.util.Date2;

/**
 * Servlet implementation class DateServlet
 */
@WebServlet("/DateServlet")
public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateServlet() {
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
		Date2 date2 = new Date2();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		Date date = new Date();
		String End = df.format(date);		
		Date dEnd = null;
		Date dBegin = null;
		List<String> list = new ArrayList<String>();
		try {
			dBegin = df.parse("20170601");
			dEnd = df.parse(End);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = date2.findDates(dBegin, dEnd);
		JSONObject object = new JSONObject();
		object.put("date", list);
		response.getWriter().print(object);
	}

}
