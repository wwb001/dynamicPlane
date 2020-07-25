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
import cn.wwb.util.Date1;
import cn.wwb.util.Date3;

/**
 * Servlet implementation class daoligangDateServletEchart
 */
@WebServlet("/daoligangDateServletEchart")
public class daoligangDateServletEchart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public daoligangDateServletEchart() {
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
		Date1 date1 = new Date1();
		List<String> list = new ArrayList<String>();
		List<String> listdate = new ArrayList<String>();
		listdate.add("product");
		List<Date> ds = date1.test(optionval+"000000", ciriString);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		for (Date d : ds) {
			String string = sdf.format(d);
			list.add(string);
			listdate.add(string);
		}
		list.add(ciriString);
		
		List<String> guoneidaogang = new ArrayList<String>();
		List<String> guojidaogang = new ArrayList<String>();
		List<String> hunhedaogang = new ArrayList<String>();
		List<String> diqudaogang = new ArrayList<String>();
		List<String> guoneiligang = new ArrayList<String>();
		List<String> guojiligang = new ArrayList<String>();
		List<String> hunheligang = new ArrayList<String>();
		List<String> diquligang = new ArrayList<String>();
		try {
			guoneidaogang = ServiceFactory.getDFDLServiceinstance().getguoneidaogangCount(list);
			guojidaogang = ServiceFactory.getDFDLServiceinstance().getguojidaogangCount(list);
			hunhedaogang = ServiceFactory.getDFDLServiceinstance().gethunhedaogangCount(list);
			diqudaogang = ServiceFactory.getDFDLServiceinstance().getdiqudaogangCount(list);
			guoneiligang = ServiceFactory.getDFDLServiceinstance().getguoneiligangCount(list);
			guojiligang = ServiceFactory.getDFDLServiceinstance().getguojiligangCount(list);
			hunheligang = ServiceFactory.getDFDLServiceinstance().gethunheligangCount(list);
			diquligang = ServiceFactory.getDFDLServiceinstance().getdiquligangCount(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject object = new JSONObject();
		object.put("listdate", listdate);
		object.put("guoneidaogang", guoneidaogang);
		object.put("guojidaogang", guojidaogang);
		object.put("hunhedaogang", hunhedaogang);
		object.put("diqudaogang", diqudaogang);
		object.put("guoneiligang", guoneiligang);
		object.put("guojiligang", guojiligang);
		object.put("hunheligang", hunheligang);
		object.put("diquligang", diquligang);
		
		response.getWriter().print(object);
	}

}
