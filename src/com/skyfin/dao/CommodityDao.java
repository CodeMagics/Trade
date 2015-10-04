package com.skyfin.dao;


import java.util.List;

import com.skyfin.bean.Commodity;
import com.skyfin.bean.CommodityDetail;


public interface CommodityDao {
	/*
	 * ͨ����Ʒ���Ͳ�ѯ��Ʒ
	 * @param ��Ʒ����
	 * @return ��Ʒʵ��
	 */
	public List<Commodity> selectByType(String type);
	
	/*
	 * ͨ����Ʒ��Ų�ѯ��Ʒ
	 * @param  ��Ʒ���
	 * @return ��Ʒʵ��
	 */
	public CommodityDetail selectByCommId(String commId);
	
	/*
	 * ͨ����Ʒ���ɾ����Ʒ
	 * @param  ��Ʒ���
	 */
	public  boolean deleteByCommId(String commId);
	
	/*
	 * ͨ����Ʒ��Ų�����Ʒ
	 * @param  ��Ʒ���
	 */
	public  boolean insertByCommId(Commodity comm);
	
	/*
	 * ������Ʒ
	 * @param  ��Ʒ
	 */
	public  boolean updateByCommId(Commodity comm);
	
	/*
	 * @param ��Ʒ���
	 * @return ͼƬ·��
	 */
	public List<String> selectByCommNum(String commId);
	
	/*
	 * @return ��ȡ������Ʒ���
	 */
	public List<String> getAll();
	
	
	
	
	
	

}
