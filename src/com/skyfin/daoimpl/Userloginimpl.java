package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skyfin.bean.User;
import com.skyfin.dao.Userlogin;
import com.skyfin.util.*;

public class Userloginimpl implements Userlogin {
	private DBUtil dbutil = null;

	public Userloginimpl() {
		dbutil = new DBUtil();
	}

	@Override
	public void add(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into login(username,passwd)values(?,?)";
		try {
			conn = dbutil.openConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPasswd());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("添加数据失败");
		} finally {
			dbutil.close(null, ps, conn);
		}
	}

	@Override
	public boolean update(User user, String NewPasswd) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User puser = null;
		String sql = "select username,passwd from login";
		try {
			conn = dbutil.openConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			puser = new User();

			while (rs.next()) {
				puser.setUsername(rs.getString(1));
				puser.setPasswd(rs.getString(2));
				if (user.toString().equals(puser.toString())) {
					sql = "update login set passwd=? where username=?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, NewPasswd);
					ps.setString(2, user.getUsername());
					ps.executeUpdate();
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("添加数据失败");
		} finally {
			dbutil.close(null, ps, conn);
		}
		return false;

	}

	@Override
	public boolean findById(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// Person p = null;
		String sql = "select username from login";
		try {
			conn = dbutil.openConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (username.equals(rs.getString(1))) {
					return true;
				}
			}
			// return false;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("添加数据失败");
		} finally {
			dbutil.close(null, ps, conn);
		}
		return false;

	}

	@Override
	public List<User> findAll() throws SQLException {
		return null;
	}

	@Override
	public boolean LoginJudge(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// Person p = null;
		String sql = "select username,passwd from login";
		try {
			conn = dbutil.openConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (user.getUsername().equals(rs.getString(1))
						&& user.getPasswd().equals(rs.getString(2))) {
					return true;
				}
			}
			// return false;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("查询数据失败");
		} finally {
			dbutil.close(null, ps, conn);
		}
		return false;
	}
}
