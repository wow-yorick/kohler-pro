package com.kohler.service;

import java.util.List;

import com.kohler.entity.CategoryComAttrEntity;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.ProductComAttrEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.SKUMetadataEntity;
/**
 * 
 * ProductComAttrService service
 *
 * @author whh
 * @Date 2014年11月14日
 */
public interface ProductComAttrService {
    
    /**
     * 通过categoryMetadataId获取所有com属性
     * @param categoryMetadataId
     * @return
     * @author sana
     * Date 2014年11月12日
     * @version
     */
    public List<CategoryComAttrMetadataEntity> getCategoryComAttrMetadataById(String categoryMetadataId);
    
    /**
     * 获取对应语言值
     * @param categoryComAttrMetadataId
     * @return
     * @author sana
     * Date 2014年11月12日
     * @version
     */
    public List<CategoryComAttrEntity> getCategoryComAttrById(String categoryComAttrMetadataId, String lang);
    
    /**
     * 获取各个attr值
     * @param lang
     * @param productMetadataId
     * @param categoryComAttrMetadataId
     * @return
     * @author sana
     * Date 2014年11月12日
     * @version
     */
    public List<ProductComAttrEntity> getProductComAttr(String lang, String productMetadataId, String categoryComAttrMetadataId);
    
    /**
     * 获取下拉
     * @param lang
     * @param masterdataTypeName
     * @return
     * @author sana
     * Date 2014年11月12日
     * @version
     */
    public List<MasterdataEntity> getMasterdata(String lang, String masterdataTypeName);
    
    /**
     * 获取ProductName
     * @param lang
     * @param productMetadataId
     * @return
     * @author sana
     * Date 2014年11月12日
     * @version
     */
    public List<ProductEntity> getProductName(String lang,String productMetadataId);
    
    /**
     * 获取skuCode
     * @param skuMetadataId
     * @return
     * @author sana
     * Date 2014年11月12日
     * @version
     */
    public List<ProductEntity> getSKUProductName(String skuMetadataId);
}
