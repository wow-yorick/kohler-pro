package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.constants.ProductComAttrSQL;
import com.kohler.dao.CategoryComAttrDao;
import com.kohler.dao.CategoryComAttrMetadataDao;
import com.kohler.dao.MasterDataDao;
import com.kohler.dao.ProductComAttrDao;
import com.kohler.dao.ProductDao;
import com.kohler.dao.SKUMetadataDao;
import com.kohler.entity.CategoryComAttrEntity;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.ProductComAttrEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.SKUMetadataEntity;
import com.kohler.service.ProductComAttrService;
/**
 * 
 * ProductComAttrServiceImpl serviceimpl
 *
 * @author whh
 * @Date 2014年11月14日
 */
@Service("productComAttrService")
public class ProductComAttrServiceImpl implements ProductComAttrService {

    @Autowired
    private CategoryComAttrMetadataDao categoryComAttrMetadataDao;
    @Autowired
    private CategoryComAttrDao categoryComAttrDao;
    @Autowired
    private ProductComAttrDao productComAttrDao;
    @Autowired
    private MasterDataDao masterDataDao;
    @Autowired
    private SKUMetadataDao skuMetadataDao;
    @Autowired
    private ProductDao productDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategoryComAttrMetadataEntity> getCategoryComAttrMetadataById(
            String categoryMetadataId) {
        List<Object> param = new ArrayList<Object>();
        param.add(categoryMetadataId);
        return categoryComAttrMetadataDao.selectByCondition(ProductComAttrSQL.SELECT_CATEGORY_COM_ATTR_METADATA,param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategoryComAttrEntity> getCategoryComAttrById(String categoryComAttrMetadataId,
            String lang) {
        List<Object> param = new ArrayList<Object>();
        param.add(categoryComAttrMetadataId);
        param.add(lang);
        return categoryComAttrDao.selectByCondition(ProductComAttrSQL.SELECT_CATEGORY_COM_ATTR, param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductComAttrEntity> getProductComAttr(String lang, String productMetadataId,
            String categoryComAttrMetadataId) {
        List<Object> param = new ArrayList<Object>();
        param.add(productMetadataId);
        param.add(categoryComAttrMetadataId);
        param.add(lang);
        
        return productComAttrDao.selectByCondition(ProductComAttrSQL.SELECT_Product_COM_ATTR, param);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MasterdataEntity> getMasterdata(String lang, String masterdataTypeName) {
        List<Object> param = new ArrayList<Object>();
        param.add(masterdataTypeName);
        param.add(lang);
        return masterDataDao.selectByCondition(ProductComAttrSQL.SELECT_MASTERDATA_BY_NAME, param);
    }

    @Override
    public List<ProductEntity> getProductName(String lang, String productMetadataId) {
        ProductEntity p = new ProductEntity();
        p.setLang(Integer.parseInt(lang));
        p.setProductMetadataId(Integer.parseInt(productMetadataId));
        return productDao.selectByCondition(p);
    }

    @Override
    public List<ProductEntity> getSKUProductName(String skuMetadataId) {
        List<Object> param = new ArrayList<Object>();
        param.add(skuMetadataId);
        return productDao.selectByCondition(ProductComAttrSQL.SELECT_SKU_Product_NAME,param);
    }
        
    
    
}
