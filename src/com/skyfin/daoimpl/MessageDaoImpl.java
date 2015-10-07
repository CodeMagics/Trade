package com.skyfin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skyfin.bean.Commodity;
import com.skyfin.bean.Message;
import com.skyfin.bean.MessageDetail;
import com.skyfin.dao.MessageDao;
import com.skyfin.util.DBUtil;

public class MessageDaoImpl implements MessageDao {

	@Override
	public boolean insert(Message mess) {
		// TODO Auto-generated method stub
		
		String sql = " insert into message (mess_user,mess_commnum,mess_content) VALUES (?,?,?)";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mess.getMessUserId());
			pstmt.setString(2, mess.getMessCommNum());
			pstmt.setString(3,mess.getMessContent());

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
	 public List<String>  selectMessage(String userName,String commNum){
		 List <String>messList=new ArrayList<String>();
		 String sql="select mess_content from message where mess_user=? and mess_commnum=?";
			DBUtil util=new DBUtil();
			Connection conn = util.openConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1,userName);
				pstmt.setString(2,commNum);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String mess=rs.getString(1);
					messList.add(mess);
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				util.closeConn(conn);
			}
		 
		 
		 return messList;
	 }
	

}
