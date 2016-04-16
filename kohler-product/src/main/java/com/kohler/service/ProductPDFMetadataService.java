/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductPDFEntity;
import com.kohler.entity.ProductPDFMetadataEntity;

/**
 * Class Function Description
 * ProductVideoMetadataService
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductPDFMetadataService {  
    /**
     * inset a isEnable=0 ProductPDFMetadataEntity 
     */
    public ProductPDFMetadataEntity addProductPDFMetadata();
    
    

    /**
     * inset into ProductPDFMetadataEntity and ProductPDFEntity
     */
    public void saveProductPDFMetadata(ProductPDFMetadataEntity productPDFMetadata,List<ProductPDFEntity> productPDFList);
    
    
    
    /**
     * update ProductPDFMetadataEntity and ProductPDFEntity
     */
    public void updateProductPDFMetadata(ProductPDFMetadataEntity productPDFMetadata,List<ProductPDFEntity> productPDFList);

    
    
    /**
     * delete ProductPDF
     * @param productPDFMetadatId
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public void deleteProductPDFMetadata(Integer productPDFMetadatId);
    
    
    
    /**
     * Get ProductPDFMetadataEntity By productPDFMetadatId
     * @param productPDFMetadatId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public ProductPDFMetadataEntity getPDFMetadataById(Integer productPDFMetadatId);
    
    
    
    /**
     * Get ProductPDFMetadataEntity List By productMetadataId
     * @param productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public List<ProductPDFMetadataEntity> getPDFMetadatalistById(Integer productMetadataId);
}
