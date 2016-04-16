package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.ProductDao;
import com.kohler.entity.ProductEntity;
import com.kohler.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDao productDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductEntity> getProductListByEntity(ProductEntity productEntity) {
        return productDao.selectByCondition(productEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductEntity> checkProductName(ProductEntity product) {
        String selectsql = "SELECT * from PRODUCT "+
                " where IS_ENABLE=1 and PRODUCT_NAME = ? and LANG = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(product.getProductName());
        params.add(product.getLang());
        //判断是新增还是编辑
        if(product.getProductMetadataId()!=null&&!"".equals(product.getProductMetadataId().toString())){
            selectsql += " and PRODUCT_METADATA_ID != ?";
            params.add(product.getProductMetadataId());
        }
        
        return productDao.selectByCondition(selectsql, params);
    }

	

}
