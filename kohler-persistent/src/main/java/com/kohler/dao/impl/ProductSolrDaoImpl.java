/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.ProductSolrSQL;
import com.kohler.dao.ProductSolrDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.ProductSolrEntity;

/**
 * Class Function Description
 * ProductDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class ProductSolrDaoImpl extends BaseDaoImpl<ProductSolrEntity> implements ProductSolrDao {
    public ProductSolrEntity selectByKeyId(Integer productSolrEntityId){
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(productSolrEntityId);
        return (ProductSolrEntity)jdbcTemplate.queryForObject(ProductSolrSQL.SELECT_BY_ID,queryParams.toArray(),new DefaultRowMapper(ProductSolrEntity.class, new DefaultNameHandler()));
    }
}
