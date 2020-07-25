package cn.wwb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import cn.wwb.factory.ServiceFactory;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		//获取用户传递过来的账号密码和验证码
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String remember = request.getParameter("remember");
		int n = 0;
		//查找数据库是否有相关的用户信息
		try {
			n = ServiceFactory.getAdminServiceinstance().loginAdmin(name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取验证码框产生的验证码
		String sessionCode = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		Map<String, String> map = new HashMap<String, String>();
		PrintWriter out = response.getWriter();
		System.out.println(n);
		if (n <= 0) {
			map.put("code", "1");
			String json = JSON.toJSONString(map);
			out.print(json);
		}else {

		}
		if (code != null && sessionCode != null) {
			if (code.equalsIgnoreCase(sessionCode)) {
				
			}else {
				map.put("code", "2");
				String json = JSON.toJSONString(map);
				out.print(json);
			}
		}
		if (n>0 && code.equalsIgnoreCase(sessionCode)) {
			map.put("code", "0");
			String json = JSON.toJSONString(map);
			out.print(json);
			if (remember.equals("true")) {
				Cookie cookie1 = new Cookie("name", name);
				Cookie cookie2 = new Cookie("password", password);
				cookie1.setMaxAge(60*60*24*7);
				cookie2.setMaxAge(60*60*24*7);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			String path = null;
			try {
				path = ServiceFactory.getAdminServiceinstance().selectPath(name);
				session.setAttribute("path", path);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
	}

}
