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
import com.kohler.service.base.BaseForNewArrival;
import com.kohler.service.util.TemplateNameParse;

/**
 * 解析新品html命名
 *
 * @author Administrator
 * @Date 2014年11月19日
 */
@Component
public class NewArrivalDataFilenameParse implements
        TemplateNameParseService {
    
    @Autowired
    private BaseForNewArrival baseForNewArrival;
    
    /**
     * {@inheritDoc}
     * @throws TemplateGetException 
     */
    @Override
    public String getGoalName(String generalName, ConfPrepareData conf){
        
        Map<String, Object> newProduct = baseForNewArrival.getNewArrival(conf);
        String retName = TemplateNameParse.patternCompile(generalName, newProduct);
        return retName;
    }
    
   

}
