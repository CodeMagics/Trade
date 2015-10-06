package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyfin.bean.User;
import com.skyfin.dao.UserDao;
import com.skyfin.daoimpl.UserDaoImpl;

public class UpdateInfo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateInfo() {
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
		//
		String userName = request.getParameter("userName");
		String param = request.getParameter("param");
		String content = request.getParameter("content");
		
		userName  = new String(userName .getBytes("ISO-8859-1"), "UTF-8");
		param = new String(param.getBytes("ISO-8859-1"), "UTF-8");
		content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
		
		UserDao userDao = new UserDaoImpl();
		User user=userDao.info(userName);
		
        if(param.equals("nickName")){
        	user.setNickName(content);
        	
        }else if(param.equals("userIntro")){
        	user.setIntroduction(content);
        	
        }else if(param.equals("userEmail")){
        	user.setEmail(content);
        	
        }else if(param.equals("userImage")){
        	user.setImg(content);
        	
        }else if(param.equals("userPhone")){
        	user.setPhone(Integer.parseInt(content));
        }else{
        	
        }
        boolean isUpdate=false;
      
			try {
				 isUpdate=userDao.update(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//更新用户成功
			if(isUpdate){
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
