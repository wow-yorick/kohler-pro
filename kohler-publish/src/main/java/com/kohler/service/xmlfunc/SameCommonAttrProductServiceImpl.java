/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.xmlfunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.CategoryComAttrDao;
import com.kohler.dao.ProductComAttrDao;
import com.kohler.entity.ProductComAttrEntity;
import com.kohler.entity.ProductMetadataEntity;
import com.kohler.exception.DataException;
import com.kohler.service.SameCommonAttrProductService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForProduct;
import com.kohler.service.url.ProductUrlAnalysis;

/**
 * 获取指定产品公共属性一样的产品
 *
 * @author Administrator
 * @Date 2014年11月22日
 */
@Service
public class SameCommonAttrProductServiceImpl implements SameCommonAttrProductService {
    
    private final static Logger logger = Logger.getLogger(SameCommonAttrProductServiceImpl.class);
    
    @Autowired
    private ProductUrlAnalysis productUrlAnalysis;
    
    @Autowired
    private ProductComAttrDao productComAttrDao;
    
    @Autowired
    private CategoryComAttrDao categoryComAttrDao;
    
    @Autowired
    private BaseForProduct baseForProduct;
    
    @Autowired
    private BaseCommon baseCommon;
    
    private ConfPrepareData confPrepareData;
    
    

    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    @Override
    public List<Map<String, Object>> getProductWith(String commonAttrKeyName, ConfPrepareData conf) throws DataException {
        
        this.confPrepareData = conf;
        
        List<Map<String, Object>> productList = new ArrayList<Map<String,Object>>();
        //try {
            //获取当前产品信息
            ProductMetadataEntity productMetadataInfo = baseForProduct.getProductMetadataInfo(conf);
            //获取指定的common attr
            Map<String, Object> catgoryCommonAttr = getCategoryCommonAttrWith(commonAttrKeyName, productMetadataInfo.getCategoryMetadataId());
            if(catgoryCommonAttr != null && catgoryCommonAttr.size() <= 0) {
                return productList;
            }
            Integer categoryComAttrMetadataId = Integer.valueOf(catgoryCommonAttr.get("CATEGORY_COM_ATTR_METADATA_ID").toString());
            //获取SKU列表
            List<Object> skuIdList = getSKUListByFilter(categoryComAttrMetadataId);
            
            //得到指定产品列表
            productList = gainProduct(skuIdList);
            //增加特殊信息字段
            productList =  specialFieldPut(productList);
            
        //} catch (Exception e) {
        //    logger.debug("don't have common attr match! commonAttrKeyName is " +commonAttrKeyName+", product id is "+ conf.getDataId());
        //}
        
        return productList;
        
    }
    
    /**
     * 根据keyName 和categoryMetadataId获取category common attr
     * @param keyName
     * @param categoryMetadataId
     * @return
     * @author Administrator
     * Date 2014年11月22日
     * @version
     */
    private Map<String, Object> getCategoryCommonAttrWith(String keyName, Integer categoryMetadataId) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder("SELECT CATEGORY_COM_ATTR_METADATA_ID,KEY_NAME FROM CATEGORY_COM_ATTR_METADATA WHERE KEY_NAME = ? AND CATEGORY_METADATA_ID = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(keyName);
        params.add(categoryMetadataId);
        List<Map<String,Object>> retMapList = categoryComAttrDao.selectByConditionWithMap(sql.toString(), params);
        if(retMapList != null && retMapList.size() > 0) {
            retMap = retMapList.get(0);
        }
        return retMap;
    }
    
    /**
     * 获取SKU列表
     * @param categoryComAttrMetadataId
     * @return
     * @author Administrator
     * Date 2014年11月22日
     * @version
     */
    private List<Object> getSKUListByFilter(Integer categoryComAttrMetadataId) {
        List<Object> retList = new ArrayList<Object>();
        
        ProductComAttrEntity produComAttrEntity = new ProductComAttrEntity();
        produComAttrEntity.setProductMetadataId(confPrepareData.getDataId());
        produComAttrEntity.setCategoryComAttrMetadataId(categoryComAttrMetadataId);
        List<ProductComAttrEntity> productComAttrEntities = productComAttrDao.selectByCondition(produComAttrEntity);
        if(productComAttrEntities != null && productComAttrEntities.size() > 0) {
            String _skuList = productComAttrEntities.get(0).getValue();
            if(_skuList != null) {
                String[] skuListS = _skuList.split(","); 
                for(String _skuIdStr : skuListS) {
                    retList.add(_skuIdStr);
                }
            }
        }
        
        return retList;
    }
    
    /**
     * 获取产品信息
     * @param skuIdList
     * @return
     * @author Administrator
     * Date 2014年11月22日
     * @version
     */
    private List<Map<String, Object>> gainProduct(List<Object> skuIdList) {
        StringBuilder sql = new StringBuilder("SELECT vp.PRODUCT_NAME product_name,vc.COLLECTION_NAME collection_name,vs.PRODUCT_METADATA_ID product_matedata_id,vs.SKU_CODE sku_code,vs.SKU_PRICE sku_price,vs.IMAGE_SOURCE,");
        sql.append("vs.LIST_IMAGE_URL,vs.LIST_IMAGE_ID,vs.LIST_IMAGE_ALT list_image_alt,");
        sql.append("vs.DETAIL_IMAGE1_URL,vs.DETAIL_IMAGE1_ID,vs.DETAIL_IMAGE1_ALT detail_image1_alt");
        sql.append(" FROM VW_SKU vs");
        sql.append(" INNER JOIN VW_PRODUCT vp ON vs.PRODUCT_METADATA_ID = vp.PRODUCT_METADATA_ID AND vp.Lang = "+confPrepareData.getLang());
        sql.append(" LEFT JOIN VW_COLLECTION vc ON vp.COLLECTION_METADATA_ID = vc.COLLECTION_METADATA_ID AND vc.LANG = "+confPrepareData.getLang());
        sql.append(" WHERE vs.SKU_METADATA_ID IN(");
        for (int i=0; i< skuIdList.size(); i++) {
            if (i!=0) sql.append(", ");
            sql.append("?");
        }
        sql.append(")  AND vs.LANG = "+ confPrepareData.getLang());
        
        return productComAttrDao.selectByConditionWithMap(sql.toString(), skuIdList);
    }
    
    /**
     * 添加特殊字段信息
     * @param productList
     * @return
     * @author Administrator
     * Date 2014年11月22日
     * @version
     * @throws DataException 
     */
    private List<Map<String, Object>> specialFieldPut(List<Map<String, Object>> productList) throws DataException {
        for(int i=0; i < productList.size(); i++) {
            //转换图片路径
            Integer imageSource = Integer.valueOf(productList.get(i).get("IMAGE_SOURCE").toString());
            
            String list_image_url_real = productList.get(i).get("LIST_IMAGE_URL").toString();
            String detail_image_url_real = productList.get(i).get("DETAIL_IMAGE1_URL").toString();
            //如果为内部文件资源需要转换
            if(imageSource.equals(CommonConstants.FILE_SOURCE_INTERNAL)) {
                //list image url
                Integer listImageId = Integer.valueOf(productList.get(i).get("LIST_IMAGE_ID").toString());
                list_image_url_real = baseCommon.combineFileUrlPath(listImageId, confPrepareData);
                
                //detail 1 image url
                Integer detailImageId = Integer.valueOf(productList.get(i).get("DETAIL_IMAGE1_ID").toString());
                detail_image_url_real = baseCommon.combineFileUrlPath(detailImageId, confPrepareData);
            }
            productList.get(i).put("list_image_url_real", list_image_url_real);
            productList.get(i).put("detail_image_url_real", detail_image_url_real);
            
            //获取产品url
            Integer productMetadataId = Integer.valueOf(productList.get(i).get("product_matedata_id").toString());
            String productUrl = productUrlAnalysis.getUrl(productMetadataId, confPrepareData);
            productList.get(i).put("product_url", productUrl);
            
        }
        
        return productList;
    }

}
