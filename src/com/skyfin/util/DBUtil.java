package com.skyfin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * ���ݿ⹤����
 */
public class DBUtil {

	public DBUtil() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}

	/*
	 * �ر����ݿ�����
	 */
	public void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * �����ݿ�����
	 */
	public Connection openConnection() {
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;

		try {
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream("DBConfig.properties"));

			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();

			if (ps != null)
				ps.close();

			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
