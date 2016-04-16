/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.io.IOException;

import com.kohler.bean.ConfPrepareData;


/**
 * 复制站点css,js image ...到生成目录
 *
 * @author Administrator
 * @Date 2014年11月19日
 */
public interface StaticResourceCopyService {

    /**
     * 执行copy
     * @param sourceDir
     * @param targetDir
     * @return
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    public boolean copy(ConfPrepareData conf) throws IOException;

}
