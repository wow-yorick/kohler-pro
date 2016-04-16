/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.strategy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.service.PublishStrategyService;
import com.kohler.service.base.BaseForSuite;
import com.kohler.service.templatename.SuiteListDataFilenameParse;

/**
 * 套间详情的发布
 *
 * @author Administrator
 * @Date 2014年11月24日
 */
@Component
public class SuiteDataPublishStrategy extends PublishStrategyAbst implements PublishStrategyService {
     
     @Autowired
     private BaseForSuite baseForSuite;
     
     @Autowired
     private SuiteListDataFilenameParse suiteListDataFilenameParse;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Object> getDataMap(ConfPrepareData conf) {
        Map<String,Object> suite =  baseForSuite.getSuiteInfoWithMap(conf);
        return suite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getErrorMsg(ConfPrepareData conf) {
        String msg = "publish suite html file fail! suite id "+ conf.getDataId();
        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getSuccessMsg(ConfPrepareData conf) {
        String msg = "publish suite html file success! suite id "+ conf.getDataId();
        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGoalNameArith(String generalName, ConfPrepareData conf) {
        return suiteListDataFilenameParse.getGoalName(generalName, conf); //命名规则转换
    }

}
