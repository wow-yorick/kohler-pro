/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.dao.ProductPDFDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.ProductPDFEntity;

/**
 * Class Function Description
 * ProductPDFDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class ProductPDFDaoImpl extends BaseDaoImpl<ProductPDFEntity> 
    implements ProductPDFDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPDFEntity getProductPDFById(Integer productPDFMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(productPDFMetadataId);
        
        StringBuilder querySql = new StringBuilder("SELECT * from PRODUCT_PDF pdf left join LOCALE lan on pdf.LANG=lan.LOCALE_ID ");
        querySql.append("where pdf.IS_ENABLE=1 and lan.IS_DEFAULT=1 and lan.IS_ENABLE=1 ");
        querySql.append("and pdf.PRODUCT_PDF_METADATA_ID = ?");
        
        List<Object> list=jdbcTemplate.query(querySql.toString(), queryParams.toArray(),
                new DefaultRowMapper(ProductPDFEntity.class, new DefaultNameHandler()));
        if(list.size()>0){
            return (ProductPDFEntity)list.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getMapList(String sql, Integer lang) {
        return jdbcTemplate.queryForList(sql,new Object[]{lang});
    }


}
