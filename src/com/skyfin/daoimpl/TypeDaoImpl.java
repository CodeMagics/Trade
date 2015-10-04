package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;






import com.mysql.jdbc.PreparedStatement;
import com.skyfin.dao.TypeDao;
import com.skyfin.util.DBUtil;

public class TypeDaoImpl implements TypeDao {

	@Override
	public String selectTypeNamebyId(int id) {
		String sql="select type_name from type where type_id=?";
		String typeName=null;
		DBUtil util=new DBUtil();
		Connection conn =  util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if (rs.next()) {			
				typeName=rs.getString(1);		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
			
		return typeName;
	}
	
	@Override
	public int selectIdByTypeName(String type){
		int id=-1;
		
		String sql="select type_id from type where type_name=?";
		DBUtil util=new DBUtil();
		Connection conn = (Connection) util.openConnection();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			pstmt.setString(1, type);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if (rs.next()) {			
				id=rs.getInt(1);		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		
		return id;
		
	}
	
	

}
