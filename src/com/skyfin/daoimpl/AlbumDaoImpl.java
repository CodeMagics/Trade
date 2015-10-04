package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.skyfin.bean.Album;
import com.skyfin.dao.AlbumDao;
import com.skyfin.util.DBUtil;

public class AlbumDaoImpl implements AlbumDao {

	@SuppressWarnings("null")
	@Override
	public List<String> selectByCommId(String commNum) {
		String sql="select ablu_pic from album where ablu_id=?";
		List<String> numList=new ArrayList<String>();
		DBUtil util=new DBUtil();
		List<String> picPath=new ArrayList<String>();
		Connection conn =util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, commNum);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				String num=rs.getString(1);
				picPath.add(num);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		System.out.println(picPath+"*********");
		return picPath;
	}

	@Override
	public boolean insertPic(String commNum) {
		
		return false;
	}

	@Override
	public boolean insertAlbum(Album abl) {
		String sql = " insert into album (ablu_id,ablu_pic) VALUES (?,?)";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, abl.getAlbuId());
			pstmt.setString(2, abl.getAlbuPic());
			if (pstmt.executeUpdate() > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return false;
	}

}