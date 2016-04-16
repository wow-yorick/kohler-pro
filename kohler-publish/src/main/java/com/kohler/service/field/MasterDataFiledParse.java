/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.field;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.MasterDataDao;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.SpecialFieldParseService;

/**
 * 字典类型字段解析
 *
 * @author Administrator
 * @Date 2014年11月24日
 */
@Component
public class MasterDataFiledParse implements SpecialFieldParseService {
    
    @Autowired
    private MasterDataDao masterDataDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String parse(Map<String, Object> dataSource, Map<String, Object> _filedMap, ConfPrepareData conf) {
            
        String fieldName = (String)_filedMap.get("name");
        String groupType = "";

        Object fieldNameVal =  dataSource.get(fieldName);
        MasterdataEntity masterdataEntity = new MasterdataEntity();
        masterdataEntity.setLang(conf.getLang());
        masterdataEntity.setIsEnable(true);
        masterdataEntity.setMasterdataMetadataId(Integer.valueOf(fieldNameVal.toString()));
        List<MasterdataEntity> masterdataEntities = masterDataDao.selectByCondition(masterdataEntity);
        groupType = masterdataEntities.get(0).getMasterdataName();

        return groupType;
    }
    

}
