package cn.wwb.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wwb.factory.ServiceFactory;

/**
 * Servlet implementation class DFDLServletSearch
 */
@WebServlet("/DFDLServletSearch")
public class DFDLServletSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DFDLServletSearch() {
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
		//1.接收参数
				int currentPage = 1;
				int lineSize = 10;
				String keyWord = request.getParameter("kw");
				if (keyWord == null) {
					keyWord = "";
				}
				try {
					lineSize = Integer.parseInt(request.getParameter("limit"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					currentPage = Integer.parseInt(request.getParameter("page"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = ServiceFactory.getDFDLServiceinstance().search(currentPage, lineSize, keyWord);		
					obj.put("data",map.get("Hangbans"));  //取得数据
					obj.put("count", map.get("counts"));  //取得数据量
					obj.put("code", 0);
					obj.put("msg", "");
					response.getWriter().print(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

}
