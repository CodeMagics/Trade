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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skyfin.bean.CommodityDetail;
import com.skyfin.dao.AlbumDao;
import com.skyfin.dao.CommodityDao;
import com.skyfin.daoimpl.AlbumDaoImpl;
import com.skyfin.daoimpl.CommodityImpl;

public class ShowTypeComm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowTypeComm() {
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
        //获取商品编号
		String typeName = request.getParameter("typeName");
		// 获取到param内容
		typeName = new String(typeName.getBytes("ISO-8859-1"), "UTF-8");
		// 查询商品信息
	
		List<CommodityDetail> commdetail=new ArrayList<CommodityDetail>();
		CommodityDao comm=new CommodityImpl();
		commdetail=comm.selectByCommType(typeName);
		
		
	
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		out.print(gson.toJson(commdetail));
		//for(int i=0;i<commdetail.size();i++){
		//	String jsonString = gson.toJson(commdetail.get(i),CommodityDetail.class);  
		//	out.print(jsonString);
		//}
		 	
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
