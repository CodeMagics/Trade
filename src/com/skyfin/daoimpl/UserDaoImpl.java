package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skyfin.bean.User;
import com.skyfin.util.DBUtil;
import com.skyfin.dao.UserDao;

public class UserDaoImpl implements UserDao{
	@Override
	public User login(User user) {
		String sql="select user_id from user where user_cardid=? and user_password=?";
		DBUtil util=new DBUtil();
		Connection conn = util.openConnection();
		String username = user.getUserName();
		String password = user.getPassWord();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				User tmpuser = new User();
				tmpuser.setId(id);
				tmpuser.setPassWord(password);
				tmpuser.setUserName(username);
				return tmpuser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}

	@Override
	public boolean insert(User user) {
		String sql = " insert into user (user_cardid,user_nickname,user_password,user_email) VALUES (?,?,?,?)";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getNickName());
			pstmt.setString(3, user.getPassWord());
			pstmt.setString(4, user.getEmail());

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

	@Override
	public boolean update(User user) {
		String sql = " update  user set user_nickname = ?,user_password = ?,user_introduction = ?,user_phone = ?,user_image = ? where user_id = ?";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getNickName());
			pstmt.setString(2, user.getPassWord());
			pstmt.setString(3, user.getIntroduction());
			pstmt.setString(4, Integer.toString(user.getPhone()));
			pstmt.setString(5, user.getImg());
			pstmt.setString(6, Integer.toString(user.getId()));

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

	@Override
	public boolean delete(String idNum) {
		String sql = " delete from  user where user_id = ?";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idNum); 
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
	
	@Override
	public boolean checkUser(String username) {
		String sql="select user_cardid from user where user_cardid=?";
		DBUtil util=new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return false;
	}

}
