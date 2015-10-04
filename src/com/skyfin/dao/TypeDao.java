package com.skyfin.dao;
/*
 * @description 
 * @author zhangshan
 */

public interface TypeDao {
	/*
	 * @param id 类型的id
	 * @return 类型名称
	 * 
	 */
	public String selectTypeNamebyId(int id);
	
	/*
	 * 
	 */
	public int selectIdByTypeName(String type);
	
}
