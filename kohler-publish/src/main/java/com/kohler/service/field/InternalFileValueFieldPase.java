/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.field;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.service.SpecialFieldParseService;
import com.kohler.service.base.BaseCommon;

/**
 * 内部文件字段解析
 *
 * @author Administrator
 * @Date 2014年11月24日
 */
@Component
public class InternalFileValueFieldPase implements SpecialFieldParseService {
    
    @Autowired
    private BaseCommon baseCommon;
  
    /**
     * {@inheritDoc}
     */
    @Override
    public String parse(Map<String, Object> dataSource,Map<String, Object> _filedMap, ConfPrepareData conf) {
        
        String keyName = (String)_filedMap.get("name");
        String fileUrl = "";
 
        //获取数据源中具体依赖的字段
        Object fileSourceObj = dataSource.get(keyName);
        Integer fileSourceInt = Integer.valueOf(fileSourceObj.toString());
        fileUrl = baseCommon.combineFileUrlPath(fileSourceInt, conf);
        
        return fileUrl;
    }

}
