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
	public boolean insert(Collect coll) throws SQLException {
		String sql = " insert into collection (coll_userid,coll_comm) VALUES (?,?)";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, coll.getUserid());
			pstmt.setString(2, coll.getComm());

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
	public boolean delete(String id) throws SQLException {
		String sql = " delete from  collection where collection_userid = ?";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id); 
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
	public List<Commodity> select(String username) throws SQLException {
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
				comm.setCommType(rs.getString(6));
				commList.add(comm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return commList;
	}

}
