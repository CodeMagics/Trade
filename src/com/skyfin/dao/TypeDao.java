package com.skyfin.dao;
/*
 * @description 
 * @author zhangshan
 */

public interface TypeDao {
	/*
	 * @param id ���͵�id
	 * @return ��������
	 * 
	 */
	public String selectTypeNamebyId(int id);
	
	/*
	 * 
	 */
	public int selectIdByTypeName(String type);
	
}
