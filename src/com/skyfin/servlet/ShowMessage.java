package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
		// 设置编码格式
		response.setCharacterEncoding("utf8");
		// 输出流
		PrintWriter out = response.getWriter();
		//获取参数
		String messCommNum = request.getParameter("messCommNum");
		
		//获取param的内容
		messCommNum = new String(messCommNum.getBytes("ISO-8859-1"),"UTF-8");
	
		List<MessageDetail>messDetailList=new ArrayList<MessageDetail>();
		
		MessageDao messDao =new MessageDaoImpl();
		messDetailList=messDao.select(messCommNum);
		
		
		//在本商品中未留言
		if(messDetailList.size()<=0){
			out.print("121");	
		}
		else{
			String jsonString =JSON.toJSONString(messDetailList);
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
