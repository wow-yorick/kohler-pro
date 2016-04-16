/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service;

import java.io.File;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.exception.DataException;
import com.kohler.service.base.XmlElementDefineMath;
import com.kohler.service.impl.XmlStrategyParseServiceImpl;
import com.kohler.service.util.XMLReader2DOM4JFinal;

/**
 * 测试xml算法转为实际数据
 *
 * @author Administrator
 * @Date 2014年10月30日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class XmlStrategyParseServiceImplTest {
    
    @Value("#{settings['file.resources.dir']}")
    private String resourcesDir;
    
    @Autowired
    XmlStrategyParseServiceImpl xmlStrategyParseServiceImpl;

    /**
     * Test method for {@link com.kohler.service.impl.XmlStrategyParseServiceImpl#resolver(java.lang.String, com.kohler.bean.ConfPrepareData)}.
     */
    @Test
    @Ignore
    public void testResolverCategory() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(101000000);
        confPrepareData.setLang(1);
        confPrepareData.setWebsitePlatform("pc");
        
        Map<String,Object> agv = XMLReader2DOM4JFinal.readFileXml(new File(resourcesDir +"velocityTemplateALG/category_pc_cn.xml"));
        XmlElementDefineMath.xmlALGMap.put("category_pc_cn.xml", agv);
        try {
            xmlStrategyParseServiceImpl.resolver(confPrepareData);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    @Ignore
    public void testResolverProductData() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(155);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName("productData.xml");
        confPrepareData.setWebsitePlatform("mobile");

        Map<String,Object> agv = XMLReader2DOM4JFinal.readFileXml(new File(resourcesDir +"velocityTemplateALG/productData.xml"));
        XmlElementDefineMath.xmlALGMap.put("productData.xml", agv);
        try {
            xmlStrategyParseServiceImpl.resolver(confPrepareData);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    @Ignore
    public void testResolverNewProductData() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(8);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName("new_product_pc_cn.xml");
        confPrepareData.setWebsitePlatform("mobile");

        Map<String,Object> agv = XMLReader2DOM4JFinal.readFileXml(new File(resourcesDir +"velocityTemplateALG/new_product_pc_cn.xml"));
        XmlElementDefineMath.xmlALGMap.put("new_product_pc_cn.xml", agv);
        try {
            xmlStrategyParseServiceImpl.resolver(confPrepareData);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
