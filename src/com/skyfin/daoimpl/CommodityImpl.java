package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.skyfin.bean.Commodity;
import com.skyfin.bean.CommodityDetail;
import com.skyfin.bean.User;
import com.skyfin.dao.CommodityDao;
import com.skyfin.util.DBUtil;


public class CommodityImpl implements CommodityDao {

	@SuppressWarnings("null")
	@Override
	public List<Commodity> selectByType(String type) {
		String sql="select comm_id,comm_num,comm_title,comm_intro,comm_price,type.type_id from commodity,type "
				+ "where type_name=? and type.type_id=commodity.type";
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
//commIdÎªÉÌÆ·±àºÅ
	@Override
	public CommodityDetail selectByCommId(String commId) {
		String sql="select comm_num,comm_title,comm_intro,type_name,comm_price,user_nickname,user_cardid,comm_date from commodity,rela,user,type where user_cardid=rela_userid and comm_num=? and rela_commid=comm_num "
				+ "and type_id=comm_type";
		DBUtil util=new DBUtil();
		Connection conn =  util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, commId);

			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if (rs.next()) {
				CommodityDetail commdetail=new CommodityDetail();
				
				User user=new User();	
				user.setNickName(rs.getString(6));
				user.setUserName(rs.getString(7));
				
				Commodity comm=new Commodity();
				comm.setCommNum(rs.getString(1));
				comm.setCommTitle(rs.getString(2));
				comm.setCommIntro(rs.getString(3));
				comm.setCommPrice(rs.getInt(5));
				comm.setCommDate(rs.getDate(8));
				
				commdetail.setUser(user);
				commdetail.setCommdity(comm);
				commdetail.setTypeName(rs.getString(4));
				return  commdetail;
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
		String sql = " insert into commodity (comm_num,comm_title,comm_intro,comm_price,comm_type,comm_date) VALUES (?,?,?,?,?,?)";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, comm.getCommNum());
			pstmt.setString(2, comm.getCommTitle());
			pstmt.setString(3,comm.getCommIntro());
			pstmt.setInt(4, comm.getCommPrice());
			pstmt.setInt(5, comm.getCommType());
			pstmt.setDate(6, new Date(comm.getCommDate().getTime()));

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
			pstmt.setInt(5, comm.getCommType());
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
	
	@SuppressWarnings("null")
	@Override
	public List<String> getAll(){
		
		String sql = "select comm_num from commodity order by comm_num";
		DBUtil util=new DBUtil();
		Connection conn = util.openConnection();
		List<String> numList=new ArrayList<String>();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				String num=rs.getString(1);
				numList.add(num);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return numList;
		
	}
	
	public String selectByCommNo(String commNum){
		String pic=null;
		String sql = "select comm_pic from commodity where comm_num=?";
		DBUtil util=new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, commNum);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if (rs.next()) {
				pic=rs.getString(1);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}	
		return pic;
	}

	
	
	public boolean updatePicPathByCommNum(String commNum,String path){
		String sql = " update  commodity set comm_pic = ? where comm_id = ?";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1,path);
			pstmt.setString(2, commNum);

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
