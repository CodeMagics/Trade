package com.skyfin.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Decoder.BASE64Decoder;

import com.skyfin.bean.Album;
import com.skyfin.dao.AlbumDao;
import com.skyfin.dao.CommodityDao;
import com.skyfin.daoimpl.AlbumDaoImpl;
import com.skyfin.daoimpl.CommodityImpl;
import com.skyfin.util.ImageCompressUtil;
import com.skyfin.util.StringRandom;

public class UpdateUserImage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateUserImage() {
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

	    request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
        response.setContentType("text/html");  
        //商品编号
        String imageid = request.getParameter("imageid");  
        //图片资源
        String photo = request.getParameter("image");  
        //系统路径
        String path = "/usr/local/tomcat7/webapps/Trade/image/";
        try {  
  
            // 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密  
            byte[] photoimg = new BASE64Decoder().decodeBuffer(photo);  
            for (int i = 0; i < photoimg.length; ++i) {  
                if (photoimg[i] < 0) {  
                    // 调整异常数据  
                    photoimg[i] += 256;  
                }  
            }
            File file = new File(path,imageid+".png");  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
            FileOutputStream out = new FileOutputStream(file);  
            out.write(photoimg);  
            out.flush();  
            out.close();   
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        
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
