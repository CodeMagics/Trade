package com.skyfin.daoimpl;

import java.sql.SQLException;

import javax.resource.cci.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.skyfin.dao.TypeDao;
import com.skyfin.util.DBUtil;

public class TypeDaoImpl implements TypeDao {

	@Override
	public String selectTypeNamebyId(int id) {
		String sql="select type_name from type where type_id=?";
		String typeName=null;
		DBUtil util=new DBUtil();
		Connection conn = (Connection) util.openConnection();
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

}
