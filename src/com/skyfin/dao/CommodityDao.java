package com.skyfin.dao;


import java.util.List;

import com.skyfin.bean.Commodity;
import com.skyfin.bean.CommodityDetail;


public interface CommodityDao {
	/*
	 * 通过商品类型查询商品
	 * @param 商品类型
	 * @return 商品实体
	 */
	public List<Commodity> selectByType(String type);
	
	/*
	 * 通过商品编号查询商品
	 * @param  商品编号
	 * @return 商品实体
	 */
	public CommodityDetail selectByCommId(String commId);
	
	/*
	 * 通过商品编号删除商品
	 * @param  商品编号
	 */
	public  boolean deleteByCommId(String commId);
	
	/*
	 * 通过商品编号插入商品
	 * @param  商品编号
	 */
	public  boolean insertByCommId(Commodity comm);
	
	/*
	 * 更新商品
	 * @param  商品
	 */
	public  boolean updateByCommId(Commodity comm);
	
	/*
	 * @param 商品编号
	 * @return 图片路径
	 */
	public List<String> selectByCommNum(String commId);
	
	/*
	 * @return 获取所有商品编号
	 */
	public List<String> getAll();
	
	/*
	 * 通过商品编号查询每个商品的主图
	 */
	public String selectByCommNo(String commNum);
	
	/*
	 * 
	 */
	public boolean updatePicPathByCommNum(String CommNum,String path);
	
	
	public List<CommodityDetail> selectByCommType(String type);
	
	

	

}
