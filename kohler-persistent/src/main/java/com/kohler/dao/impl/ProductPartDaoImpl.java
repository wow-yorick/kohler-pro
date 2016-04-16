/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.dao.ProductPartDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.ProductPartEntity;

/**
 * Class Function Description
 * ProcdutPartDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class ProductPartDaoImpl extends BaseDaoImpl<ProductPartEntity> 
    implements ProductPartDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPartEntity getProductPartById(Integer productPartMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(productPartMetadataId);
        
        StringBuilder querySql = new StringBuilder("SELECT part.* FROM PRODUCT_PART part left join LOCALE lan ");
        querySql.append("on part.LANG=lan.LOCALE_ID where  part.IS_ENABLE=1 and lan.IS_DEFAULT=1 and lan.IS_ENABLE=1 ");
        querySql.append("and part.PRODUCT_PART_METADATA_ID= ?");
        
        List list=jdbcTemplate.query(querySql.toString(), queryParams.toArray(),
                new DefaultRowMapper(ProductPartEntity.class, new DefaultNameHandler()));
        if(list.size()>0){
            return (ProductPartEntity)list.get(0);
        }
        return null;
    }

}
