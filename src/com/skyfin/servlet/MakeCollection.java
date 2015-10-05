package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyfin.bean.Collect;
import com.skyfin.daoimpl.CollectDaoImpl;

public class MakeCollection extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MakeCollection() {
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
		this.doPost(request, response);
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
		String username = request.getParameter("username");
		String commid = request.getParameter("commid");
		//获取param的内容
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        commid = new String(commid.getBytes("ISO-8859-1"),"UTF-8");
        
        CollectDaoImpl collectimpl = new CollectDaoImpl();
        
        if(collectimpl.checkcollection(username, commid)){
        	if(collectimpl.delete(username,commid))
        	    out.print("113");
        	else
        	    out.print("114");
        }
        else{
        	Collect collect = new Collect();
        	collect.setUserid(username);
        	collect.setComm(commid); 
        	if(collectimpl.insert(collect))
            	out.print("109");
        	else 
        		out.print("110");
        	}
        //刷新
		out.flush();
		//关闭
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
