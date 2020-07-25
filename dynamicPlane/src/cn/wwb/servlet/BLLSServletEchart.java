package cn.wwb.servlet;

import java.io.IOException;
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

import cn.wwb.factory.ServiceFactory;
import cn.wwb.util.Date3;
import cn.wwb.util.Date4;

/**
 * Servlet implementation class daoligangServletEchart
 */
@WebServlet("/BLLSServletEchart")
public class BLLSServletEchart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BLLSServletEchart() {
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
		String optionval = request.getParameter("optionval");
//		String optionval = "20170601";
		Date3 date3 = new Date3();
		String ciridate = date3.addDate(optionval+"000000");
		String ciriString = ciridate+"000000";
		Date4 date4 = new Date4();
		List<String> list = new ArrayList<String>();
		List<String> listdate = new ArrayList<String>();
		listdate.add("product");
		List<Date> ds = date4.test(optionval+"000000", ciriString);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		for (Date d : ds) {
			String string = sdf.format(d);
			list.add(string);
			listdate.add(string);
		}
		list.add(ciriString);
		
		List<String> one = new ArrayList<String>();
		List<String> ten = new ArrayList<String>();
		List<String> elevent = new ArrayList<String>();
		List<String> nine = new ArrayList<String>();
		List<String> twelve = new ArrayList<String>();
		List<String> two = new ArrayList<String>();
		List<String> thirteen = new ArrayList<String>();
		try {
			one = ServiceFactory.getBLLSServiceinstance().getzhuanpanCount(list,"1");
			ten = ServiceFactory.getBLLSServiceinstance().getzhuanpanCount(list,"10");
			elevent = ServiceFactory.getBLLSServiceinstance().getzhuanpanCount(list,"11");
			nine = ServiceFactory.getBLLSServiceinstance().getzhuanpanCount(list,"9");
			twelve = ServiceFactory.getBLLSServiceinstance().getzhuanpanCount(list,"12");
			two = ServiceFactory.getBLLSServiceinstance().getzhuanpanCount(list,"2");
			thirteen = ServiceFactory.getBLLSServiceinstance().getzhuanpanCount(list,"13");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject object = new JSONObject();
		object.put("listdate", listdate);
		object.put("one", one);
		object.put("ten", ten);
		object.put("elevent", elevent);
		object.put("nine", nine);
		object.put("twelve", twelve);
		object.put("two", two);
		object.put("thirteen", thirteen);
		
		response.getWriter().print(object);
	}

}
