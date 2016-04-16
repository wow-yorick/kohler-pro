/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.ProductComAttrDao;
import com.kohler.dao.ProductDao;
import com.kohler.dao.ProductMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.entity.ProductComAttrEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.ProductMetadataEntity;
import com.kohler.service.ProductMetadataService;

/**
 * Class Function Description
 * ProductMetadataServiceImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Service
public class ProductMetadataServiceImpl implements ProductMetadataService {
    
    @Autowired
    private ProductMetadataDao pmdao;
    
    @Autowired
    private ProductDao pdao;
    
    @Autowired
    private SysLogDao sysLogDao;
    
    @Autowired
    private ProductComAttrDao pcaDao;

    @Override
    public ProductMetadataEntity addProductMetadata() {
        ProductMetadataEntity productMetadata=new ProductMetadataEntity();
        productMetadata.setIsEnable(false);
        
        int productMetadataId=pmdao.insert(productMetadata);
        productMetadata.setProductMetadataId(productMetadataId);
        
        sysLogDao.insertLogForInsert(productMetadataId, "PRODUCT_METADATA");
        
        return productMetadata;
    }

    
    
    @Override
    @Transactional
    public void saveProductMetadata(ProductMetadataEntity productMetadata, List<ProductEntity> productList, List<Map<String, Object>> attrlist) throws UnsupportedEncodingException {
        if(null != productMetadata && !"".equals(productList)){
            productMetadata.setIsEnable(true);
            pmdao.update(productMetadata);
            
            sysLogDao.insertLogForUpdate(productMetadata.getProductMetadataId(), "PRODUCT_METADATA");
            
            if(productList.size()>0){
                for(ProductEntity product:productList){
                    product.setProductMetadataId(productMetadata.getProductMetadataId());
                    int productId=pdao.insert(product);
                    
                    sysLogDao.insertLogForInsert(productId, "PRODUCT");
                }
            }
            
            if(attrlist != null && attrlist.size() > 0){
                for (Map<String, Object> temp : attrlist) {
                    String strtemp= (String)temp.get("lang");
                    String[] langstrs = strtemp.split("&");
                    String[] lang = langstrs[0].split("=");
                    for (String langstr : langstrs) {
                        String[] keyval = langstr.split("=");
                        if(keyval[0]!=null&&!"lang".equals(keyval[0])){
                            ProductComAttrEntity pcae = new ProductComAttrEntity();
                            pcae.setIsEnable(true);
                            pcae.setLang(Integer.parseInt(lang[1]));
                            pcae.setProductMetadataId(productMetadata.getProductMetadataId());
                            pcae.setCategoryComAttrMetadataId(Integer.parseInt(keyval[0]));
                            
                            List<ProductComAttrEntity> pcaelist = pcaDao.selectByCondition(pcae);
                            if(pcaelist!=null&&pcaelist.size()>0){
                                pcae = pcaelist.get(0);
                                if(keyval!=null&&keyval.length>1){
                                    pcae.setValue(URLDecoder.decode(keyval[1],"utf-8"));
                                }else{
                                    pcae.setValue("");
                                }
                                pcaDao.update(pcae);
                                sysLogDao.insertLogForUpdate(pcae.getProductComAttrId(), "PRODUCT_COM_ATTR");
                            }else{
                                if(keyval!=null&&keyval.length>1){
                                    pcae.setValue(URLDecoder.decode(keyval[1],"utf-8"));
                                }else{
                                    pcae.setValue("");
                                }
                                int pcaId = pcaDao.insert(pcae);
                                sysLogDao.insertLogForInsert(pcaId, "PRODUCT_COM_ATTR");
                            }
                        }
                    }
                }   
            }
        }
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductMetadataEntity getProductMetadataById(Integer productMetadataId) {
        return pmdao.selectById(productMetadataId);
    }

    
    
    /**
     * {@inheritDoc}
     * @throws UnsupportedEncodingException 
     */
    @Override
    @Transactional
    public void updateProductMetadata(ProductMetadataEntity productMetadata,
            List<ProductEntity> productList, List<Map<String, Object>> attrlist) throws UnsupportedEncodingException {
        if(null != productMetadata && !"".equals(productList)){
            productMetadata.setIsEnable(true);
            pmdao.update(productMetadata);
            
            sysLogDao.insertLogForUpdate(productMetadata.getProductMetadataId(), "PRODUCT_METADATA");
            
            if(productList.size()>0){
                for(ProductEntity product:productList){
                    product.setProductMetadataId(productMetadata.getProductMetadataId());
                    product.setIsEnable(true);
                    pdao.update(product);
                    
                    sysLogDao.insertLogForUpdate(product.getProductId(), "PRODUCT");
                }
            }
            
            if(attrlist != null && attrlist.size() > 0){
                for (Map<String, Object> temp : attrlist) {
                    String strtemp= (String)temp.get("lang");
                    String[] langstrs = strtemp.split("&");
                    String[] lang = langstrs[0].split("=");
                    for (String langstr : langstrs) {
                        String[] keyval = langstr.split("=");
                        if(keyval[0]!=null&&!"lang".equals(keyval[0])){
                            ProductComAttrEntity pcae = new ProductComAttrEntity();
                            pcae.setIsEnable(true);
                            pcae.setLang(Integer.parseInt(lang[1]));
                            pcae.setProductMetadataId(productMetadata.getProductMetadataId());
                            pcae.setCategoryComAttrMetadataId(Integer.parseInt(keyval[0]));
                            
                            List<ProductComAttrEntity> pcaelist = pcaDao.selectByCondition(pcae);
                            if(pcaelist!=null&&pcaelist.size()>0){
                                pcae = pcaelist.get(0);
                                if(keyval!=null&&keyval.length>1){
                                    pcae.setValue(URLDecoder.decode(keyval[1],"utf-8"));
                                }else{
                                    pcae.setValue("");
                                }
                                pcaDao.update(pcae);
                                sysLogDao.insertLogForUpdate(pcae.getProductComAttrId(), "PRODUCT_COM_ATTR");
                            }else{
                                if(keyval!=null&&keyval.length>1){
                                    pcae.setValue(URLDecoder.decode(keyval[1],"utf-8"));
                                }else{
                                    pcae.setValue("");
                                }
                                int pcaId = pcaDao.insert(pcae);
                                sysLogDao.insertLogForInsert(pcaId, "PRODUCT_COM_ATTR");
                            }
                        }
                    }
                }   
            }
        }
    }



    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public int deleteProduct(String productMetadataIds) {
        int i = pdao.deleteProduct(productMetadataIds);
        return pmdao.deleteProduct(productMetadataIds);
    }
}
