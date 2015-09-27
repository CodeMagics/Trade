package com.skyfin.dao;

import java.sql.SQLException;
import java.util.List;

import com.skyfin.bean.User;

public interface UserDao {
	/**
	 * 注册插入用户
	 * @param user 用户的实体
	 * @throws SQLException
	 */
    public boolean insert(User user)throws SQLException;
    
    /**
     * 修改用户的密码
     * @param user
     * @return ok 更新密码成功  no 用户名密码错误
     * @throws SQLException
     */
    public boolean update(User user)throws SQLException;
    /**
     * 查找用户是否重名了
     * @param user
     * @return true 在数据库中  false 没有在数据库中
     * @throws SQLException
     */
    public boolean checkUser(String username)throws SQLException;
    
    public boolean delete(String idNum);
    
    public User info(String id);
    
    public User login(User user);
}
