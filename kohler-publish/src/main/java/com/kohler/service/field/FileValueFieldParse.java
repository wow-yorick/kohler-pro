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
import com.kohler.constants.CommonConstants;
import com.kohler.service.SpecialFieldParseService;
import com.kohler.service.base.BaseCommon;

/**
 * 文件字段解析
 *
 * @author Administrator
 * @Date 2014年11月24日
 */
@Component
public class FileValueFieldParse implements SpecialFieldParseService {
    
    @Autowired
    private BaseCommon baseCommon;

    /**
     * {@inheritDoc}
     */
    @Override
    public String parse(Map<String, Object> dataSource, Map<String, Object> _filedMap, ConfPrepareData conf) {

        String fileAssetSource = (String)_filedMap.get("fileAssetSource");//资源类型
        String fileAssetExternal = (String)_filedMap.get("fileAssetExternal");//外部资源
        String fileAssetInternal = (String)_filedMap.get("fileAssetInternal");//内部资源
        String fileUrl = "";

        //获取数据源中具体依赖的字段
        Object fileSourceObj = dataSource.get(fileAssetSource);
        Integer fileSourceInt = Integer.valueOf(fileSourceObj.toString());
        
        if(fileSourceInt.equals(CommonConstants.FILE_SOURCE_INTERNAL)) {
            Object InternalFileAssetId =  dataSource.get(fileAssetInternal);
            fileUrl = baseCommon.combineFileUrlPath(InternalFileAssetId, conf);
        } else {
            fileUrl = (String) dataSource.get(fileAssetExternal);
        }
           
        return fileUrl;
    }
    

}
