package com.skyfin.dao;

import java.util.List;
import com.skyfin.bean.Relation;

public interface RelationDao {

		/**
		 *添加一个关系
		 */
		public boolean insert(Relation rela);
		/**
		 * 通过商品 编号删除关系
		 * @param 商品编号
		 */
		public boolean delete(String relaCommId);
		/**
		 * 更新关系
		 * @param rela  关系
		 * @param relaCommId 商品编号
		 * @return 修改成功返回true 否则返回false
		 */
		public boolean update(Relation rela);
		/**
		 * 通过用户编号查找关系
		 * @param relaUserId 用户编号
		 * @return 关系列表
		 */
		public List<Relation> selectByUserId(String relaUserId);
		/**
		 * 通过商品编号查找关系
		 * @param relaCommId 商品编号
		 * @return 关系
		 */
		public Relation selectByCommId(String relaCommId);
}
