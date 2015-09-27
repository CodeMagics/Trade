package com.skyfin.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.resource.cci.ResultSet;





import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.skyfin.bean.Commodity;
import com.skyfin.dao.CommodityDao;
import com.skyfin.util.DBUtil;

public class CommodityImpl implements CommodityDao {

	@SuppressWarnings("null")
	@Override
	public List<Commodity> selectByType(String type) {
		String sql="select comm_id,comm_num,comm_title,comm_intro,comm_price from commodity where comm_type=?";
		DBUtil util=new DBUtil();
		List<Commodity> commList=null;
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, type);

			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				Commodity comm = new Commodity();
				comm.setId(rs.getInt(1));
				comm.setCommNum(rs.getString(2));
				comm.setCommTitle(rs.getString(3));
				comm.setCommIntro(rs.getString(4));
				comm.setCommPrice(rs.getInt(5) );
				comm.setCommType(type);
				commList.add(comm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return commList;
	}
//commIdÎªÉÌÆ·±àºÅ
	@Override
	public Commodity selectByCommId(String commId) {
		String sql="select comm_id from commodity where comm_num=?";
		DBUtil util=new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, commId);

			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				Commodity comm=new Commodity();
				comm.setId(id);
				comm.setCommNum(commId);
				comm.setCommTitle(rs.getString(3));
				comm.setCommIntro(rs.getString(4));
				comm.setCommPrice(Integer.parseInt(rs.getString(5)) );
				comm.setCommType(rs.getString(7));
				
				return  comm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	
	}

	@Override
	public boolean deleteByCommId(String commId) {
		
		String sql = " delete from  commodity where comm_num = ?";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, commId); 
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
	public boolean insertByCommId(Commodity comm) {  
		String sql = " insert into commodity (comm_num,comm_title,comm_intro,comm_price,comm_type) VALUES (?,?,?,?,?)";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, comm.getCommNum());
			pstmt.setString(2, comm.getCommTitle());
			pstmt.setString(3,comm.getCommIntro());
			pstmt.setInt(4, comm.getCommPrice());
			pstmt.setString(5, comm.getCommType());

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
	public boolean updateByCommId(Commodity comm) {
		String sql = " update  commodity set comm_num = ?,comm_title= ?,comm_intro= ?,comm_price= ?,comm_type= ? where comm_id = ?";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, comm.getCommNum());
			pstmt.setString(2, comm.getCommTitle());
			pstmt.setString(3, comm.getCommIntro());
			pstmt.setInt(4,comm.getCommPrice());
			pstmt.setString(5, comm.getCommType());
			pstmt.setInt(6, comm.getId());

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
	public List<String> selectByCommNum(String commId){
		List<String> picPath=null;
		String sql="SELECT album.ablu_pic FROM album,commodity WHERE album.ablu_id=commodity.comm_num";
		DBUtil util=new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

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
	

}
