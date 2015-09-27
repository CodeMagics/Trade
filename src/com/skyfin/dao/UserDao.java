package com.skyfin.dao;

import java.sql.SQLException;
import java.util.List;

import com.skyfin.bean.User;

public interface UserDao {
	/**
	 * ע������û�
	 * @param user �û���ʵ��
	 * @throws SQLException
	 */
    public boolean insert(User user)throws SQLException;
    
    /**
     * �޸��û�������
     * @param user
     * @return ok ��������ɹ�  no �û����������
     * @throws SQLException
     */
    public boolean update(User user)throws SQLException;
    /**
     * �����û��Ƿ�������
     * @param user
     * @return true �����ݿ���  false û�������ݿ���
     * @throws SQLException
     */
    public boolean checkUser(String username)throws SQLException;
    
    public boolean delete(String idNum);
    
    public User info(String id);
    
    public User login(User user);
}
