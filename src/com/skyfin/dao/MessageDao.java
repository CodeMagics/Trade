package com.skyfin.dao;

import java.util.List;

import com.skyfin.bean.Message;


public interface MessageDao {
	
    public  boolean insert(Message mess);
    
    public List<String>  selectMessage(String userName,String commNum);

}
