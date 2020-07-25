package cn.wwb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.wwb.factory.ServiceFactory;
import cn.wwb.vo.Admin;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tel = request.getParameter("tel");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String path = request.getParameter("path");
		Admin admin = new Admin(tel, pwd, name, path);
		Map<String, String> map = new HashMap<String, String>();
		int n = 0;
		try {
			n = ServiceFactory.getAdminServiceinstance().selectAdmin(name, tel);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (n>0) {
			map.put("msg", "0");
			String json = JSON.toJSONString(map);
			response.getWriter().println(json);
		}else {
			try {
				boolean res = ServiceFactory.getAdminServiceinstance().registerAdmin(admin);
				if (res == true) {
					map.put("msg", "1");
					String json = JSON.toJSONString(map);
					response.getWriter().println(json);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
