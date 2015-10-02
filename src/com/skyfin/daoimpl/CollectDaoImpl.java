package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skyfin.bean.Collect;
import com.skyfin.bean.Commodity;
import com.skyfin.dao.CollectDao;
import com.skyfin.util.DBUtil;

public class CollectDaoImpl implements CollectDao{

	@Override
	public boolean insert(Collect collect){
		String sql = " insert into collection (coll_userid,coll_comm) VALUES (?,?)";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, collect.getUserid());
			pstmt.setString(2, collect.getComm());

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
	public boolean delete(String username,String commid){
		String sql = " delete from  collection where coll_userid = ? and coll_comm = ?";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username); 
			pstmt.setString(2, commid);
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

	@SuppressWarnings("null")
	@Override
	public List<Commodity> select(String username){
		String sql="select comm_id,comm_num,comm_title,comm_intro,comm_price,comm_pic,comm_type "
				+ "from collection,commodity where coll_userid=? and collection.coll_comm=commodity.comm_num";
		DBUtil util=new DBUtil();
		Connection conn = util.openConnection();
		List<Commodity> commList=null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Commodity comm = new Commodity();
				comm.setId(rs.getInt(1));
				comm.setCommNum(rs.getString(2));
				comm.setCommTitle(rs.getString(3));
				comm.setCommIntro(rs.getString(4));
				comm.setCommPrice(rs.getInt(5) );
				comm.setCommType(rs.getInt(6));
				commList.add(comm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return commList;
	}
	
	@Override
	public boolean checkcollection(String username,String commid){
		String sql = "select coll_id from collection where coll_userid=? and coll_comm=?";
		DBUtil util=new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);
			pstmt.setString(2, commid);
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
