/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.service.StaticResourceCopyService;
import com.kohler.service.base.BaseCommon;
import com.kohler.util.CopyDirectory;

/**
 * 静态资源copy
 *
 * @author Administrator
 * @Date 2014年11月19日
 */
@Service
public class StaticResourceCopyServiceImpl implements StaticResourceCopyService {
    
    private final static Logger logger = Logger.getLogger(StaticResourceCopyServiceImpl.class);
    
    @Value("#{settings['file.website.dir']}")
    private String websiteDir;
    
    @Value("#{settings['js.global.var.chinaweb']}")
    private String chinaweb;
    
    @Value("#{settings['js.global.var.china_search']}")
    private String chinaSearch;
    
    @Autowired
    private BaseCommon baseCommon;
    
    private static final String JS_GLOBAL_FILEPATH = "js/chinaweb.js";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean copy(ConfPrepareData conf) throws IOException {
        //time remark
        long startTime = Calendar.getInstance().getTimeInMillis();
        //站点配置
        SiteSettingEntity siteSet = baseCommon.getSitePlatformSet(conf);//站点配置
        String targetDir = siteSet.getSitePath();//发布物理路径
        //china web js 全局变量文件生成
        File jsfile = new File(targetDir + JS_GLOBAL_FILEPATH);
        if(!jsfile.exists()) {
            makeDir(jsfile.getParentFile());
            jsfile.createNewFile();
        }
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(jsfile, false);
            out.write(("var chinaweb = '"+chinaweb+"';").getBytes());
            out.write(("var china_search = '"+chinaSearch+"';").getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        //copy all 
        CopyDirectory.copyDirectiory(websiteDir, targetDir);
        if(!conf.getIsPreview()) {//如果不为预览
            //上传文件 copy
            SiteSettingEntity uploadServer = baseCommon.getSiteSetting(CommonConstants.FILE_PLATFORM);//上传文件服务配置
            String upladDir = uploadServer.getSitePath();//发布物理路径
            CopyDirectory.copyDirectiory(websiteDir+"upload/", upladDir);// 发布目录copy
            
            //images copy
            SiteSettingEntity imageServer = baseCommon.getSiteSetting(CommonConstants.IMAGE_PLATFORM);//静态图片服务配置
            String imageDir = imageServer.getSitePath();//发布物理路径
            CopyDirectory.copyDirectiory(websiteDir+"images/", imageDir);// 发布目录copy
        } 
        
        long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("copy file time is "+ (endTime-startTime));
        return true;
    }
    
    private static void makeDir(File dir) {  
        if(! dir.getParentFile().exists()) {  
            makeDir(dir.getParentFile());  
        }  
        dir.mkdir();  
    }  

}
