package com.kohler.dao;


import java.util.List;
import java.util.Map;

import com.kohler.entity.NewArrivalBannerUnitEntity;



public interface NewProductBannerUnitDao extends BaseDao<NewArrivalBannerUnitEntity>{
    public List<Map<String, Object>> BannerUnit(Integer NewArrivalMetadataId,String fieldname);
 }
