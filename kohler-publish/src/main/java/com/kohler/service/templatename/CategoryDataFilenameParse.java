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
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.util.TemplateNameParse;

/**
 * path最后一级解析
 *
 * @author Administrator
 * @Date 2014年12月3日
 */
@Component
public class CategoryDataFilenameParse implements TemplateNameParseService {
    
    @Autowired
    private BaseForCategory baseForCategory;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGoalName(String generalName, ConfPrepareData conf) {

        Map<String, Object> category = baseForCategory.getCategory(conf);
        String retName = TemplateNameParse.patternCompile(generalName, category);
        return retName;
    }

}
