package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductEntity;

public interface ProductService {
	
	public List<ProductEntity> getProductListByEntity(ProductEntity productEntity);

    /**
     * @param product
     * @return
     * @author sana
     * Date 2014年12月5日
     * @version
     */
    public List<ProductEntity> checkProductName(ProductEntity product);
	
}
