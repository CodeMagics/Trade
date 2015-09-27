package com.skyfin.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.resource.cci.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.skyfin.bean.Album;
import com.skyfin.dao.AlbumDao;
import com.skyfin.util.DBUtil;

public class AlbumDaoImpl implements AlbumDao {

	@SuppressWarnings("null")
	@Override
	public List<String> selectByCommId(String commNum) {
		String sql="select ablu_pic from album where ablu_id=?";
		DBUtil util=new DBUtil();
		List<String> picPath=null;
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, commNum);

			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				String path;
				path=rs.getString(1);
				picPath.add(path);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
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
