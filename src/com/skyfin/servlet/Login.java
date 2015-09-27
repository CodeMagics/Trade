package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.LOCATION_FORWARD;

import com.skyfin.bean.User;
import com.skyfin.daoimpl.UserDaoImpl;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// 设置编码格式
		response.setCharacterEncoding("utf8");
		// 输出流
		PrintWriter out = response.getWriter();
		//
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");

		// 获取到param内容
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		passwd = new String(passwd.getBytes("ISO-8859-1"), "UTF-8");

		// 新建一个实体
		User user = new User();
		user.setUserName(username);
		user.setPassWord(passwd);

		// 用户登陆的接口
		UserDaoImpl muserloginimpl = new UserDaoImpl();
		if (muserloginimpl.login(user) != null) {
			out.print("103");
		} else {
			out.print("104");
		}
		// 刷新
		out.flush();
		// 关闭
		out.close();
		
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
