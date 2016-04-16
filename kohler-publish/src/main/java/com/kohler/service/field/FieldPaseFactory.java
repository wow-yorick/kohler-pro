/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.service.IFactoryService;

/**
 * 特殊字段工厂
 *
 * @author Administrator
 * @Date 2014年12月2日
 */
@Component
public class FieldPaseFactory implements IFactoryService {
    
    @Autowired
    private FileValueFieldParse fileValueFieldParse;
    
    @Autowired
    private InternalFileValueFieldPase internalFileValueFieldPase;
    
    @Autowired
    private MasterDataFiledParse masterDataFiledParse;
    
    @Autowired
    private UrlValueFieldParse urlValueFieldParse;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object createObject(String objName) {
        if("physicalfileasset".equals(objName)) {
            return fileValueFieldParse;
        }
        if("internalfileasset".equals(objName)) {
            return internalFileValueFieldPase;
        }
        if("physicalmasterdata".equals(objName)) {
            return masterDataFiledParse;
        }
        if("urlvalue".equals(objName)) {
            return urlValueFieldParse;
        }
        return null;
    }

}
