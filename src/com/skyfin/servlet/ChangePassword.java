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
		// ���ñ����ʽ
		response.setCharacterEncoding("utf8");
		// �����
		PrintWriter out = response.getWriter();
		//
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String newpasswd = request.getParameter("newpasswd");

		// ��ȡ��param����
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		passwd = new String(passwd.getBytes("ISO-8859-1"), "UTF-8");

		// �½�һ��ʵ��
		User user = new User();
		user.setUsername(username);
		user.setPasswd(passwd);

		// �û���½�Ľӿ�
		Userloginimpl muserloginimpl = new Userloginimpl();
		try {
			if (muserloginimpl.update(user,newpasswd)) {
				out.println("�޸�����ɹ�");
			} else {
				muserloginimpl.add(user);
				out.println("ԭ�û������������");
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		// ˢ��
		out.flush();
		// �ر�
		out.close();
	}

	public void init() throws ServletException {
	}

}
