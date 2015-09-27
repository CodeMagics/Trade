package com.skyfin.dao;

import java.sql.SQLException;
import java.util.List;

import com.skyfin.bean.Collect;
import com.skyfin.bean.Commodity;


public interface CollectDao {

	public boolean insert(Collect coll)throws SQLException;
	public boolean delete(String id)throws SQLException;
	public List<Commodity> select(String username)throws SQLException;
}