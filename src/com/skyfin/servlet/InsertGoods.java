package com.skyfin.servlet;

import java.io.IOException;
import java.io.PrintWriter;





import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.skyfin.bean.Commodity;
import com.skyfin.bean.CommodityDetail;
import com.skyfin.bean.Relation;
import com.skyfin.dao.AlbumDao;
import com.skyfin.dao.CommodityDao;
import com.skyfin.dao.RelationDao;
import com.skyfin.dao.TypeDao;
import com.skyfin.daoimpl.AlbumDaoImpl;
import com.skyfin.daoimpl.CommodityImpl;
import com.skyfin.daoimpl.RelationDaoImpl;
import com.skyfin.daoimpl.TypeDaoImpl;

public class InsertGoods extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InsertGoods() {
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
       
		String userName= request.getParameter("userName");
		String commNum = request.getParameter("commNum");
		String commTitle = request.getParameter("commTitle");
		String commIntro = request.getParameter("commIntro");
		String commPrice = request.getParameter("commPrice");
		String commType = request.getParameter("commType");
		System.out.println("++"+commType);
		
		// 获取到param内容
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		commNum = new String(commNum.getBytes("ISO-8859-1"), "UTF-8");
		commTitle = new String(commTitle.getBytes("ISO-8859-1"), "UTF-8");
		commIntro = new String(commIntro.getBytes("ISO-8859-1"), "UTF-8");
		commPrice = new String(commPrice.getBytes("ISO-8859-1"), "UTF-8");
		commType = new String(commType.getBytes("ISO-8859-1"), "UTF-8");
		
		//*************
		
		
		//set commodity
		Commodity comm = new Commodity();
		comm.setCommNum(commNum);
		comm.setCommPrice(Integer.parseInt(commPrice));
		comm.setCommTitle(commTitle);
		comm.setCommIntro(commIntro);
		
		
		//获取类型ID
		TypeDao typedao=new TypeDaoImpl();
		int id=typedao.selectIdByTypeName(commType);
		comm.setCommType(id);
		comm.setCommPic(" ");
		Date now =new Date();
		comm.setCommDate(now);
		System.out.println(now);
		//插入商品
		CommodityDao commDao=new CommodityImpl();
		boolean isCommInsert=commDao.insertByCommId(comm);
		//插入关系
		Relation rela = new Relation();
		rela.setRelaUserId(userName);
		rela.setRelaCommId(commNum);
		RelationDao relation = new RelationDaoImpl();
		boolean isRelaInsert =relation.insert(rela);
		//上传成功
		if(isCommInsert&&isRelaInsert){
			out.print("107");
		}
		else{
			out.print("108");
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
