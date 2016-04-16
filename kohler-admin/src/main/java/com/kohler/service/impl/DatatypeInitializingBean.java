/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.EditDataType;
import com.kohler.bean.ListDataType;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.DatatypeDao;
import com.kohler.entity.DatatypeEntity;
import com.kohler.framework.util.ParseXml;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月16日
 */
@Component
public class DatatypeInitializingBean implements InitializingBean {

    
    //DatatypeDao类的
    @Autowired 
    private DatatypeDao dataTypeDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        DatatypeEntity d = new DatatypeEntity();
        d.setIsEnable(true);
        List<DatatypeEntity> datatypeList = dataTypeDao.selectByCondition(d);
        for (DatatypeEntity datatypeEntity : datatypeList) {
            List<Object> l = new ArrayList<Object>();
            ListDataType list = ParseXml.parseListXml(datatypeEntity.getListDefinationXML());
            
            EditDataType edit = ParseXml.parseEditXml(datatypeEntity.getEditDefinationXML());
            l.add(list);
            l.add(edit);
            CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.put(datatypeEntity.getDatatypeId().toString(), l);
        }

    }

}
