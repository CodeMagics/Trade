package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.skyfin.bean.Message;
import com.skyfin.bean.MessageDetail;
import com.skyfin.dao.MessageDao;
import com.skyfin.dao.UserDao;
import com.skyfin.daoimpl.MessageDaoImpl;
import com.skyfin.daoimpl.UserDaoImpl;

public class ShowMessage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowMessage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		response.setContentType("text/html");
		// ���ñ����ʽ
		response.setCharacterEncoding("utf8");
		// �����
		PrintWriter out = response.getWriter();
		//��ȡ����
		String messUser = request.getParameter("messUser");
		String messCommNum = request.getParameter("messCommNum");
		
		//��ȡparam������
		messUser = new String(messUser.getBytes("ISO-8859-1"), "UTF-8");
		messCommNum = new String(messCommNum.getBytes("ISO-8859-1"),"UTF-8");
	
		MessageDetail messDetail=new MessageDetail();
		
		MessageDao messDao =new MessageDaoImpl();
		Message mess=new Message();
		messDetail.setMessList(messDao.selectMessage(messUser, messCommNum));
		
		UserDao userDao =new UserDaoImpl();
		messDetail.setUser(userDao.info(messUser));
		//�ڱ���Ʒ��δ����
		if(messDetail.getMessList().size()<=0){
			out.print("121");	
		}
		else{
			String jsonString =JSON.toJSONString(messDetail);
			out.print(jsonString);
		}
		out.flush();
		out.close();

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
