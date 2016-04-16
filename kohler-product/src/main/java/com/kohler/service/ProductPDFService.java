/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.ProductPDFEntity;

/**
 * Class Function Description
 * ProductPDFService
 * @author ztt
 * @Date 2014年10月11日
 */
public interface ProductPDFService {
    
    public ProductPDFEntity getProductPDFById(Integer productPDFMetadataId);
    
    
    
    public List<ProductPDFEntity> getProductPDFListByEntity(ProductPDFEntity entity);



    /**
     * @param fileId
     * @return
     * @author sana
     * Date 2014年11月27日
     * @version
     */
    public FileAssetEntity getFileAssetById(Integer fileId);
}
