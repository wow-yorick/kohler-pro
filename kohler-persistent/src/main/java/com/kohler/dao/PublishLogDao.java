package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.PublishLogEntity;;

public interface PublishLogDao extends BaseDao<PublishLogEntity> {
	Pager<PublishLogEntity> queryForList(Pager<PublishLogEntity> pager,
			Map<String, Object> cod);

	/**
	 * @param sql
	 * @author XHY
	 * Date 2014年11月28日
	 * @version
	 */
	public void executeSql(String sql);

	/**
	 * @param params
	 * @return
	 * @author XHY
	 * Date 2014年12月2日
	 * @version
	 */
	PublishLogEntity getCheckPublish(List<Object> params);

	/**
	 * @param params
	 * @return
	 * @author XHY
	 * Date 2014年12月2日
	 * @version
	 */
	PublishLogEntity getOpenPublish(List<Object> params);

}
