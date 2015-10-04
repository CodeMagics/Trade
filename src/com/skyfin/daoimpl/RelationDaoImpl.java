package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skyfin.bean.Relation;
import com.skyfin.util.*;
import com.skyfin.dao.RelationDao;

public class RelationDaoImpl implements RelationDao{

	@Override
	public boolean insert(Relation rela){
			String sql = "insert into rela(rela_userid,rela_commid) VALUES (?,?)";
			DBUtil util = new DBUtil();
			Connection conn = (Connection) util.openConnection();
			try{
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

				pstmt.setString(1, rela.getRelaUserId());
				pstmt.setString(2, rela.getRelaCommId());

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
	public boolean delete(String relaCommId){
		String sql = " delete from rela where rela_commid = ?";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, relaCommId);
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
	public boolean update(Relation rela){
		String sql = " update  rela set rela_userid = ?,rela_commid= ?where rela_id = ?";
		DBUtil util = new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setInt(3, rela.getId());
			pstmt.setString(1, rela.getRelaUserId());
			pstmt.setString(2, rela.getRelaCommId());

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
	public List<Relation> selectByUserId(String relaUserId){
		String sql="select rela_id,rela_commid from rela where rela_userid=?";
		DBUtil util=new DBUtil();
		List<Relation> relaList=null;
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1,relaUserId);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				Relation rela = new Relation();
				rela.setId(rs.getInt(1));
				rela.setRelaCommId(rs.getString(3));
				rela.setRelaUserId(relaUserId);
				relaList.add(rela);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return relaList;
		
	}
	
	@Override
	public Relation selectByCommId(String relaCommId){
		String sql="select rela_id from rela where rela_commid=?";
		DBUtil util=new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, relaCommId);

			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				Relation rela = new Relation();
				rela.setId(id);
				rela.setRelaUserId(rs.getString(2));
				rela.setRelaCommId(relaCommId);
				return  rela;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
}
