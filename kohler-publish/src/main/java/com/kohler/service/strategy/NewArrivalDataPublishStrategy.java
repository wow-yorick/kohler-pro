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
import com.kohler.service.base.BaseForNewArrival;
import com.kohler.service.templatename.NewArrivalDataFilenameParse;

/**
 * new arrival publish strategy
 * 实际算法请看PublishStrategyAbst
 * @author Administrator
 * @Date 2014年11月19日
 */
@Component
public class NewArrivalDataPublishStrategy extends PublishStrategyAbst implements PublishStrategyService {
    
    @Autowired
    private BaseForNewArrival baseForNewArrival;
    
    @Autowired
    private NewArrivalDataFilenameParse newArrivalDataFilenameParse;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Object> getDataMap(ConfPrepareData conf) {
        Map<String,Object> newProduct =  baseForNewArrival.getNewArrival(conf);
        return newProduct;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getErrorMsg(ConfPrepareData conf) {
        String msg = "New Arrival data id "+conf.getDataId()+" publish fail!";
        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getSuccessMsg(ConfPrepareData conf) {
        String msg = "New Arrival data id "+conf.getDataId()+" publish success!";
        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGoalNameArith(String generalName, ConfPrepareData conf) {
        return newArrivalDataFilenameParse.getGoalName(generalName, conf);
    }
    
}

