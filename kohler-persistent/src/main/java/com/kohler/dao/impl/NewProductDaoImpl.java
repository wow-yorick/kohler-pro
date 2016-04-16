package com.kohler.dao.impl;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.dao.NewProductDao;
import com.kohler.entity.NewArrivalEntity;


@Repository
public class NewProductDaoImpl extends BaseDaoImpl<NewArrivalEntity> implements NewProductDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Integer DelectNewProduct(String sql,Integer[] ids) {
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder(sql);
        sb.append(" WHERE NEW_ARRIVAL_METADATA_ID IN ( ");
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
