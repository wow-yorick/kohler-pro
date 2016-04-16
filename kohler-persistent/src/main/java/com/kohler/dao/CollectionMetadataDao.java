package com.kohler.dao;

import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionMetadataEntity;

public interface CollectionMetadataDao extends BaseDao<CollectionMetadataEntity>{

	Pager<CollectionMetadataEntity> queryForList(Pager<CollectionMetadataEntity> pager,
			Map<String, Object> cod);
	
}
