/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.needpublishdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.CategoryMetadataDao;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.service.NeedPublishDataService;

/**
 * 查询category
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Component
public class CategoryList implements NeedPublishDataService {
    
    @Autowired
    private CategoryMetadataDao categoryMetadataDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getPrimaryKeyList(Integer parentId, ConfPrepareData conf) {
        List<Integer> retList = new ArrayList<Integer>();//结果集
        
        CategoryMetadataEntity cAMetadataEntity = new CategoryMetadataEntity();
        cAMetadataEntity.setParentId(parentId);
        cAMetadataEntity.setIsEnable(true);
        List<CategoryMetadataEntity> cMetadataEntities = categoryMetadataDao.selectByCondition(cAMetadataEntity);
        for(CategoryMetadataEntity _cmes : cMetadataEntities) {
            retList.add(_cmes.getCategoryMetadataId());
        }
        return retList;
    }

}
