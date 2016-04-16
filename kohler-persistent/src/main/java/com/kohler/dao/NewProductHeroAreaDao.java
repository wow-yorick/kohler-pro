package com.kohler.dao;


import java.util.List;
import java.util.Map;

import com.kohler.entity.NewArrivalHeroAreaEntity;


public interface NewProductHeroAreaDao extends BaseDao<NewArrivalHeroAreaEntity>{
        public List<Map<String,Object>> NewProductHeroArea(Integer NewArrivalMetadataId,String fieldname);
 }
