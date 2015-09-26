package com.skyfin.dao;

import java.sql.SQLException;
import java.util.List;

import com.skyfin.bean.User;

public interface Userlogin {
	/**
	 * ע������û�
	 * @param user �û���ʵ��
	 * @throws SQLException
	 */
    public void add(User user)throws SQLException;
    
    /**
     * �޸��û�������
     * @param user
     * @return ok ��������ɹ�  no �û����������
     * @throws SQLException
     */
    public boolean update(User user,String NewPasswd)throws SQLException;
    /**
     * �����û��Ƿ�������
     * @param user
     * @return true �����ݿ���  false û�������ݿ���
     * @throws SQLException
     */
    public boolean findById(String username)throws SQLException;
    
    /**
     * �õ����е��û�
     * @return �û���ʵ��
     * @throws SQLException
     */
    public List<User> findAll()throws SQLException;
    

    public boolean LoginJudge(User user)throws SQLException;
	
}
