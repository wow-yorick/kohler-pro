package com.kohler.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.entity.CategoryComAttrEntity;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.ProductComAttrEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.SKUMetadataEntity;
/**
 * 
 * ProductComAttrServiceTest test
 *
 * @author sana
 * @Date 2014年11月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductComAttrServiceTest {
    
    @Autowired
    private ProductComAttrService productComAttrService;
   
    /**
     * 
     Test method for {@link com.kohler.service.impl.ProductComAttrServiceImpl#getCategoryComAttrMetadataById(com.kohler.entity.CategoryComAttrMetadataEntity, java.util.List)}.
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    @Test
    public void testGetCategoryComAttrMetadataById(){
        String categoryMetadataId = "101005000";
        List<CategoryComAttrMetadataEntity> list = productComAttrService.getCategoryComAttrMetadataById(categoryMetadataId);
        for(CategoryComAttrMetadataEntity entity:list){
            assertEquals(entity.getCategoryMetadataId().toString(), "101005000");
        }
    }
    
    /**
     * 
     Test method for {@link com.kohler.service.impl.ProductComAttrServiceImpl#getCategoryComAttrById(com.kohler.entity.CategoryComAttrEntity, java.util.List)}.
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    @Test
    public void testGetCategoryComAttrById(){
        String categoryComAttrMetadataId = "1";
        String lang = "1";
        List<CategoryComAttrEntity> list = productComAttrService.getCategoryComAttrById(categoryComAttrMetadataId,lang);
        for(CategoryComAttrEntity entity:list){
            assertEquals(entity.getCategoryComAttrMetadataId().toString(), "1");
        }
    }
    
    /**
     * 
     Test method for {@link com.kohler.service.impl.ProductComAttrServiceImpl#getProductComAttr(com.kohler.entity.ProductComAttrEntity, java.util.List)}.
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    @Test
    public void testGetProductComAttr(){
        String productMetadataId = "1";
        String categoryComAttrMetadataId = "1";
        String lang = "1";
        List<ProductComAttrEntity> list = productComAttrService.getProductComAttr(lang, productMetadataId, categoryComAttrMetadataId);
        for(ProductComAttrEntity entity:list){
            assertEquals(entity.getProductMetadataId().toString(), "1");
        }
    }
    
    /**
     * 
     Test method for {@link com.kohler.service.impl.ProductComAttrServiceImpl#getMasterdata(com.kohler.entity.MasterdataEntity, java.util.List)}.
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    @Test
    public void testGetMasterdata(){
        String lang = "1";
        String masterdataTypeName = "TYPE_MAIN_CHANNEL";
        List<MasterdataEntity> list = productComAttrService.getMasterdata(lang, masterdataTypeName);
        for(MasterdataEntity entity:list){
            assertEquals(entity.getLang().toString(), "1");
        }
    }
    
    /**
     * 
     Test method for {@link com.kohler.service.impl.ProductComAttrServiceImpl#getProductName(com.kohler.entity.ProductEntity, java.util.List)}.
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    @Test
    public void testGetProductName(){
        String lang = "1";
        String productMetadataId = "1";
        List<ProductEntity> list = productComAttrService.getProductName(lang, productMetadataId);
        for(ProductEntity entity:list){
            assertEquals(entity.getProductMetadataId().toString(), "1");
        }
    }
    
    /**
     * 
     Test method for {@link com.kohler.service.impl.ProductComAttrServiceImpl#getSKUProductName(com.kohler.entity.ProductEntity, java.util.List)}.
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    @Test
    public void testGetSKUCode(){
        String skuMetadataId = "1";
        List<ProductEntity> list = productComAttrService.getSKUProductName(skuMetadataId);
        for(ProductEntity entity:list){
            assertEquals(entity.getLang().toString(), "1");
        }
    }

}
