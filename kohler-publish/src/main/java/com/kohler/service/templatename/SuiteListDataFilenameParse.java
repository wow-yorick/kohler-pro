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
import com.kohler.service.base.BaseForSuite;
import com.kohler.service.util.TemplateNameParse;

/**
 * suite list data
 *
 * @author Administrator
 * @Date 2014年11月20日
 */
@Component
public class SuiteListDataFilenameParse implements TemplateNameParseService {
    
    @Autowired
    private BaseForSuite baseForSuite;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGoalName(String generalName, ConfPrepareData conf) {
        
        Map<String, Object> suite = baseForSuite.getSuiteInfoWithMap(conf);
        String retName = TemplateNameParse.patternCompile(generalName, suite);
        return retName;
    }

}
