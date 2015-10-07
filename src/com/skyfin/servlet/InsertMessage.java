package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyfin.bean.Message;
import com.skyfin.dao.MessageDao;
import com.skyfin.daoimpl.CollectDaoImpl;
import com.skyfin.daoimpl.MessageDaoImpl;

public class InsertMessage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InsertMessage() {
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
		// 设置编码格式
		response.setCharacterEncoding("utf8");
		// 输出流
		PrintWriter out = response.getWriter();
		//获取参数
		String messUser = request.getParameter("messUser");
		String messCommNum = request.getParameter("messCommNum");
		String messContent = request.getParameter("messContent");
		
		//获取param的内容
		messUser = new String(messUser.getBytes("ISO-8859-1"), "UTF-8");
		messCommNum = new String(messCommNum.getBytes("ISO-8859-1"),"UTF-8");
		messContent = new String(messContent.getBytes("ISO-8859-1"),"UTF-8");
		
		MessageDao messDao =new MessageDaoImpl();
		Message mess=new Message();
		mess.setMessUserId(messUser);
		mess.setMessCommNum(messCommNum);
		mess.setMessContent(messContent);
		if(messDao.insert(mess)){
			out.print("116");
		}
		else{
			out.print("117");
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
