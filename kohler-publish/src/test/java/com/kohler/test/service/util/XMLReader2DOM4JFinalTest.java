/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.util;

import java.io.File;

import org.apache.velocity.VelocityContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.constants.CommonConstants;
import com.kohler.service.util.XMLReader2DOM4JFinal;
import com.kohler.util.GenerateHtml;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月15日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class XMLReader2DOM4JFinalTest {
    
    @Value("#{settings['file.resources.dir']}")
    private String resourcesDir;
    
    @Value("#{settings['file.website.dir']}")
    private String websiteDir;
    
    
    @Value("#{settings['file.velocity.dataConfig.dir']}")
    private String preDataConfig;    
    
    @Value("#{settings['file.velocity.template.dir']}")
    private String velocityDir;

    /**
     * Test method for {@link com.kohler.service.util.XMLReader2DOM4JFinal#readFileXml(java.io.File)}.
     */
    @Test
    @Ignore
    public void testReadFileXml() {
        XMLReader2DOM4JFinal.readFileXml(new File(resourcesDir + "velocityTemplateALG/"+CommonConstants.XML_FOR_CATEGORY_PC_CN));
    }
    
    @Test
    @Ignore
    public void testReadFileXmlProductData() {
        XMLReader2DOM4JFinal.readFileXml(new File(resourcesDir + "velocityTemplateALG/"+ CommonConstants.XML_FOR_PRODUCT));
    }
    
    @Test
    public void testGeneralHtml() {
        try {
            GenerateHtml.generateHtml(websiteDir+"vm/", "header_pc_cn.vm","C:/header.html", new VelocityContext());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
