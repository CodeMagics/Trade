package com.skyfin.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyfin.bean.Album;
import com.skyfin.bean.Commodity;
import com.skyfin.dao.AlbumDao;
import com.skyfin.dao.CommodityDao;
import com.skyfin.daoimpl.AlbumDaoImpl;
import com.skyfin.daoimpl.CommodityImpl;
import com.skyfin.util.StringRandom;

import Decoder.BASE64Decoder;

public class UpLoadImage extends HttpServlet {

	public UpLoadImage() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		    request.setCharacterEncoding("utf-8");  
	        response.setCharacterEncoding("utf-8");  
	        response.setContentType("text/html");  
	        //��Ʒ���
	        String commodityid = request.getParameter("commodityid");  
	        //ͼƬ��Դ
	        String photo = request.getParameter("image");  
	        //ͼƬ����
	        String ImageName = StringRandom.getRandomString(36);
	        //ϵͳ·��
	         String path = "/usr/local/tomcat7/webapps/Trade/image/";
	         
	        //����ͼƬ·��
	        String picPath=path+ImageName;
	        Album alb=new Album();
	        alb.setAlbuId(commodityid);
	        alb.setAlbuPic(picPath);
	        AlbumDao albDao=new AlbumDaoImpl();
	        albDao.insertAlbum(alb);
	        //�ж��Ƿ������ͼ
	        CommodityDao commDao = new CommodityImpl();
	        String commPic = commDao.selectByCommNo(commodityid);
	        if(commPic==" "){
	        	commDao.updatePicPathByCommNum(commodityid, commPic);
	        }
	        
	        
	        try {  
	  
	            // ��base64���ݽ��н��� ���� �ֽ����飬����ֱ����Base64.decode���������н���  
	            byte[] photoimg = new BASE64Decoder().decodeBuffer(photo);  
	            for (int i = 0; i < photoimg.length; ++i) {  
	                if (photoimg[i] < 0) {  
	                    // �����쳣����  
	                    photoimg[i] += 256;  
	                }  
	            } 
	    
	            File file = new File(path,ImageName+".png");  
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

	public void init() throws ServletException {
		// Put your code here
	}

}
