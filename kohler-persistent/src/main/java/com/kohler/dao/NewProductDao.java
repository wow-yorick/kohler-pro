package com.kohler.dao;

import com.kohler.entity.NewArrivalEntity;


public interface NewProductDao extends BaseDao<NewArrivalEntity>{
        /**
         * Write Log For Delect NEW_ARRIVAL
         * @return
         * @author fujiajun
         * Date 2014-10-17
         * @version
         */
        public Integer DelectNewProduct(String sql,Integer[] ids);
 }
