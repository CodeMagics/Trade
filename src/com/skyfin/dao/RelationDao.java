package com.skyfin.dao;

import java.util.List;
import com.skyfin.bean.Relation;

public interface RelationDao {

		/**
		 *���һ����ϵ
		 */
		public boolean insert(Relation rela);
		/**
		 * ͨ����Ʒ ���ɾ����ϵ
		 * @param ��Ʒ���
		 */
		public boolean delete(String relaCommId);
		/**
		 * ���¹�ϵ
		 * @param rela  ��ϵ
		 * @param relaCommId ��Ʒ���
		 * @return �޸ĳɹ�����true ���򷵻�false
		 */
		public boolean update(Relation rela);
		/**
		 * ͨ���û���Ų��ҹ�ϵ
		 * @param relaUserId �û����
		 * @return ��ϵ�б�
		 */
		public List<Relation> selectByUserId(String relaUserId);
		/**
		 * ͨ����Ʒ��Ų��ҹ�ϵ
		 * @param relaCommId ��Ʒ���
		 * @return ��ϵ
		 */
		public Relation selectByCommId(String relaCommId);
}
