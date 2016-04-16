/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.kohler.dao.CollectionStyleDao;
import com.kohler.entity.CollectionStyleEntity;
/**
 * Class Function Description
 * @author fujiajun
 * @Date 2014年9月25日
 */
@Repository
public class CollectionStyleDaoImpl extends BaseDaoImpl<CollectionStyleEntity> implements CollectionStyleDao {
    @Override
    public Integer DelectCollectionStyle(String sql, Integer[] ids) {
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder(sql);
        sb.append(" WHERE COLLECTION_STYLE_METADATA_ID IN ( ");
        List<Object> list=new LinkedList<Object>();
        for(int i=0,j=ids.length;i<j;i++){
            if(i<1){
                sb.append("?");
            }else{
                sb.append(",?");
            }
            list.add(ids[i].intValue());
        }   
        sb.append(")");
        return jdbcTemplate.update(sb.toString(), list.toArray());
    }

}
