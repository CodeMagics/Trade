package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.skyfin.bean.Commodity;
import com.skyfin.dao.RelationDao;
import com.skyfin.daoimpl.CollectDaoImpl;
import com.skyfin.daoimpl.RelationDaoImpl;

public class ShowUploadComm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowUploadComm() {
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

		String userName = request.getParameter("userName");
		// ��ȡ��param����
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		
		// ��ѯ�û���Ϣ�Ľӿ�
		CollectDaoImpl collectimpl = new CollectDaoImpl();
		
		RelationDao relaDao=new RelationDaoImpl();
		List<Commodity> commlist = relaDao.showUploadComm(userName);
		
		String jsonStrng;
		//δ�����κζ���
		if(commlist.size()==0)
			jsonStrng = JSON.toJSONString("115");
		else
			jsonStrng = JSON.toJSONString(commlist);
		out.print(jsonStrng);
		// ˢ��
		out.flush();
		// �ر�
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
