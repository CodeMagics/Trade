package com.skyfin.servlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.skyfin.bean.Commodity;
import com.skyfin.daoimpl.CollectDaoImpl;

/**
 * @author yu
 * ��ʾ�û��ղ���Ʒ��ͼ
 *
 */
public class ShowCollection extends HttpServlet{
	/**
	 * Constructor of the object.
	 */
	public ShowCollection(){
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

		String username = request.getParameter("username");
		// ��ȡ��param����
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		// ��ѯ�û���Ϣ�Ľӿ�
		CollectDaoImpl collectimpl = new CollectDaoImpl();
		//java.util.List<Commodity> commlist = collectimpl.select(username);
		//String jsonStrng = JSON.toJSONString(commlist);
		//out.print(jsonStrng);
		System.out.println(collectimpl.select(username));
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