/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.templatename;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.service.TemplateNameParseService;
import com.kohler.service.base.BaseForProduct;
import com.kohler.service.util.TemplateNameParse;

/**
 * 解析产品html命名
 *
 * @author Administrator
 * @Date 2014年11月18日
 */
@Component
public class ProductDataFilenameParse implements TemplateNameParseService {
    
    @Autowired
    private BaseForProduct baseForProduct;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getGoalName(String generalName, ConfPrepareData conf) {
        
        Map<String, Object> product = baseForProduct.getProductInfoWithMap(conf);
        String retName = TemplateNameParse.patternCompile(generalName, product);
        return retName;
    }
    


}
