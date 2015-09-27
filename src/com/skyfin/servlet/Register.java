package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyfin.bean.User;
import com.skyfin.daoimpl.UserDaoImpl;

public class Register extends HttpServlet{
	/**
	 * Constructor of the object.
	 */
	public Register() {
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
		// ���ñ����ʽ
		response.setCharacterEncoding("utf8");
		// �����
		PrintWriter out = response.getWriter();
		//
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		

		// ��ȡ��param����
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
		passwd = new String(passwd.getBytes("ISO-8859-1"), "UTF-8");
		email = new String(email.getBytes("ISO-8859-1"), "UTF-8");


		// �û�ע��Ľӿ�
		UserDaoImpl muserloginimpl = new UserDaoImpl();
		if (muserloginimpl.checkUser(username)) {
			out.println("�û����Ѵ���");
		} else {
			User user = new User();
			user.setUserName(username);
			user.setNickName(nickname);
			user.setPassWord(passwd);
			user.setEmail(email);
			muserloginimpl.insert(user);
			out.println("ע��ɹ�");
		}
		// ˢ��
		out.flush();
		// �ر�
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
