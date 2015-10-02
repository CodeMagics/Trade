package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyfin.bean.Collect;
import com.skyfin.daoimpl.CollectDaoImpl;

public class MakeCollection extends HttpServlet{
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
			throws ServletException, IOException{
		response.setContentType("text/html");
		// ���ñ����ʽ
		response.setCharacterEncoding("utf8");
		// �����
		PrintWriter out = response.getWriter();
		//
		String userid = request.getParameter("userid");
		String commid = request.getParameter("commid");
		
		// ��ȡ��param����
		userid = new String(userid.getBytes("ISO-8859-1"), "UTF-8");
		commid = new String(commid.getBytes("ISO-8859-1"), "UTF-8");

		// �û�ע��Ľӿ�
		CollectDaoImpl collectimpl = new CollectDaoImpl();
		try {
			if (collectimpl.checkcollection(userid, commid)) {
				out.println("����Ʒ���ղ�");
			} else {
				Collect collect = new Collect();
				collect.setUserid(userid);
				collect.setComm(commid);
				if(collectimpl.insert(collect))
					out.println("�ղسɹ�");
				
				else
					out.println("�ղ�ʧ��");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
