package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.skyfin.bean.CommodityDetail;
import com.skyfin.dao.AlbumDao;
import com.skyfin.dao.CommodityDao;
import com.skyfin.daoimpl.AlbumDaoImpl;
import com.skyfin.daoimpl.CommodityImpl;

public class ShowGoods extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowGoods() {
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
        //获取商品编号
		String commNum = request.getParameter("commNum");
		// 获取到param内容
		commNum = new String(commNum.getBytes("ISO-8859-1"), "UTF-8");
		// 查询商品信息
	
		CommodityDetail commdetail;
		CommodityDao comm=new CommodityImpl();
		commdetail=comm.selectByCommId(commNum);
		
		AlbumDao ablum=new AlbumDaoImpl();
		commdetail.setPicPath(ablum.selectByCommId(commNum));
		
		//转为json
		String jsonStrng = JSON.toJSONString(commdetail);
	    out.print(jsonStrng);
		
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
