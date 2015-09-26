package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyfin.bean.User;
import com.skyfin.daoimpl.Userloginimpl;

public class ChangePassword extends HttpServlet {

	public ChangePassword() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


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
		String newpasswd = request.getParameter("newpasswd");

		// 获取到param内容
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		passwd = new String(passwd.getBytes("ISO-8859-1"), "UTF-8");

		// 新建一个实体
		User user = new User();
		user.setUsername(username);
		user.setPasswd(passwd);

		// 用户登陆的接口
		Userloginimpl muserloginimpl = new Userloginimpl();
		try {
			if (muserloginimpl.update(user,newpasswd)) {
				out.println("修改密码成功");
			} else {
				muserloginimpl.add(user);
				out.println("原用户名或密码错误");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 刷新
		out.flush();
		// 关闭
		out.close();
	}

	public void init() throws ServletException {
	}

}
