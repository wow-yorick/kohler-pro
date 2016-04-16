/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.ContentFieldValuesDao;
import com.kohler.dao.FileAssetDao;
import com.kohler.dao.FolderDao;
import com.kohler.dao.PublishFolderDao;
import com.kohler.dao.SiteSettingDao;
import com.kohler.dao.TemplateDao;
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.FolderEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;

/**
 * 准备数据中用到的公共方法
 *
 * @author Administrator
 * @Date 2014年10月27日
 */
@Component
public class BaseCommon {
    
    private final static Logger logger = Logger.getLogger(BaseCommon.class);
    
    @Autowired
    private ContentFieldValuesDao contentFieldValuesDao;
    
    @Autowired
    protected FileAssetDao fileAssetDao;
    
    @Autowired
    protected FolderDao folderDao;

    @Autowired
    protected SiteSettingDao siteSettingDao;
    
    @Autowired
    protected TemplateDao templateDao;
    
    @Autowired
    protected PublishFolderDao publishFolderDao;
    
    /**
     *文件 路径组合
     * @param assetId
     * @return
     * @author Administrator
     * Date 2014年11月6日
     * @version
     */
    public String combineFileUrlPath(Object fileAssetId, ConfPrepareData confPrepareData) {
        try {
            FileAssetEntity fileAssetEntity = fileAssetDao.selectById(Integer.valueOf(fileAssetId.toString()));
            String physicalFileName = fileAssetEntity.getPhysicalFilename();
            Integer folderId = fileAssetEntity.getFolderId();
            
            String folder = getFileFolderPath(folderId, new StringBuilder());

            SiteSettingEntity uploadServer = getSiteSetting(CommonConstants.FILE_PLATFORM);//上传文件服务配置
            String fileURL = uploadServer.getSiteDomainName();//发布物理路径
            
            if(confPrepareData.getIsPreview()){
                SiteSettingEntity siteSet = getSitePlatformSet(confPrepareData);//预览操作文件url为预览的
                fileURL = siteSet.getSiteDomainName();                
            }
            String retFilePath = fileURL + folder+physicalFileName;
            return retFilePath;    
        } catch (Exception e) {
            logger.debug("combineUrlPath Failure fileAssetId: "+fileAssetId+" func:combineFileUrlPath line:70");
            return "";
        }
        
    }
    
    /**
     * 递归获取文件目录路径
     * @param folderId
     * @param basePath
     * @return
     * @author Administrator
     * Date 2014年11月7日
     * @version
     */
    private String getFileFolderPath(Integer folderId,StringBuilder basePath) {
        FolderEntity folderEntity = folderDao.selectById(folderId);
        Integer parentId = folderEntity.getParentId();
        String folder = folderEntity.getFolderPath();
        basePath.insert(0, folder);
        if(parentId != null && !parentId.equals(0)) {
            getFileFolderPath(parentId,basePath);
        }
        return basePath.toString();
    }
    
    
    /**
     * 根据逗号隔开的ID字符串获取内容属性List
     * @param contIds
     * @return
     * @author Administrator
     * Date 2014年10月22日
     * @version
     */
    protected List<Map<String,Object>> getContentFieldValuesForList(String contIds, ConfPrepareData conf ) {
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        if(contIds == null) {
            return result;
        }
        String[] _contIdTmp = contIds.split(",");
        for(String _ids : _contIdTmp) {
            int contentMetadataId = Integer.valueOf(_ids);
            Map<String,Object> customClassify = getContentFieldValues(contentMetadataId, conf);
            result.add(customClassify);
        }
        return result;
    }
    
    
    /**
     * 获取内容信息
     * @param contentMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月21日
     * @version
     */
    private Map<String,Object> getContentFieldValues(Integer contentMetadataId, ConfPrepareData conf) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT v.* FROM CONTENT_FIELD_VALUES v LEFT JOIN CONTENT_METADATA m ");
        sql.append(" ON v.CONTENT_METADATA_ID = m.CONTENT_METADATA_ID ");
        sql.append(" WHERE v.IS_ENABLE = 1 AND m.IS_ENABLE = 1 AND v.LANG = ");
        sql.append(conf.getLang());
        sql.append(" AND m.CONTENT_METADATA_ID =? ORDER BY m.CONTENT_METADATA_ID DESC");
        List<Object> id = new ArrayList<Object>();
        id.add(contentMetadataId);
        List<Map<String,Object>> dbData = contentFieldValuesDao.selectByConditionWithMap(sql.toString(), id);
        Map<String,Object> result = dbDataListToMap(dbData, conf);
        return result;
    }
    
    
    /**
     * 数据库查询的属性转为Map
     * @return
     * @author Administrator
     * Date 2014年10月22日
     * @version
     */
    public  Map<String,Object> dbDataListToMap(List<Map<String,Object>> dbData, ConfPrepareData conf) {
        List<String> IDfields = new ArrayList<String>();//属性值为ID的需要转换
        IDfields.add(CommonConstants.IMAGE_ID);
        IDfields.add(CommonConstants.FILE_ID);
        
        Map<String,Object> result = new HashMap<String, Object>();
        for(Map<String,Object> _val : dbData) {
            String key = _val.get("FIELD_NAME").toString();
            Object val = _val.get("FIELD_VALUE");
            if(IDfields.contains(key) && val != null) {
                String pathVal = combineFileUrlPath(val, conf);
                result.put(key,pathVal); 
            } else {
                result.put(key,val);                
            }
        }
        return result;
    }
    
    
    
    /**
     * 获取站点配置
     * @param platform
     * @return
     * @author Administrator
     * Date 2014年11月7日
     * @version
     */
    public SiteSettingEntity getSiteSetting(String platform) {
        Map<String,String> caseSiteSet = new HashMap<String, String>();
        caseSiteSet.put(CommonConstants.PC_PLATFORM, CommonConstants.PC_WEBSITE);
        caseSiteSet.put(CommonConstants.MOBILE_PLATFORM, CommonConstants.MOBILE_WEBSITE);
        caseSiteSet.put(CommonConstants.FILE_PLATFORM, CommonConstants.FILE_SERVER);
        caseSiteSet.put(CommonConstants.IMAGE_PLATFORM, CommonConstants.IMAGE_SERVER);
        caseSiteSet.put(CommonConstants.PREVIEW_PLATFORM, CommonConstants.PREVIEW_SERVER);
        caseSiteSet.put("TEST-1", "TEST-1");//TODO 以后删除
        caseSiteSet.put("TEST-2", "TEST-2");
        caseSiteSet.put("TEST-3", "TEST-3");
        caseSiteSet.put("TEST-4", "TEST-4");
        caseSiteSet.put("TEST-5", "TEST-5");
        caseSiteSet.put("TEST-6", "TEST-6");
        caseSiteSet.put("TEST-7", "TEST-7");
        caseSiteSet.put("TEST-8", "TEST-8");
        caseSiteSet.put("TEST-9", "TEST-9");
        caseSiteSet.put("TEST-10", "TEST-10");
        
        
        
        SiteSettingEntity siteSettingEntity = new SiteSettingEntity();
        siteSettingEntity.setSiteName(caseSiteSet.get(platform));
        siteSettingEntity.setIsEnable(true);
        List<SiteSettingEntity> siteSetting =siteSettingDao.selectByCondition(siteSettingEntity);
        return siteSetting.get(0);
    }
    
    /**
     * 增加预览支持
     * @param conf
     * @return
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    public SiteSettingEntity getSitePlatformSet(ConfPrepareData conf) {
        String platform = conf.getWebsitePlatform();//默认根据配置获取平台
        if(conf.getIsPreview()) {//如果为预览
            platform = CommonConstants.PREVIEW_PLATFORM;
        }
        if(conf.getTestPlatform() != null) {//TODO 以后删除测试平台
            platform = conf.getTestPlatform();
        }
        return getSiteSetting(platform);
    }
    
    
    /**
     * 根据MAP获取模板
     * @param productMap
     * @return
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    public TemplateEntity getTemplateByMap(Map<String, Object> dataMap, ConfPrepareData conf) {
        //获取模板ID
        Integer templateId = (Integer) dataMap.get("PC_TEMPLATE_ID");
        if(conf.getWebsitePlatform() == CommonConstants.PC_PLATFORM) {
            templateId = (Integer) dataMap.get("PC_TEMPLATE_ID");
        } else {
            templateId = (Integer) dataMap.get("MOBILE_TEMPLATE_ID");
        }
        //获取模板
        TemplateEntity templateEntity = templateDao.selectById(templateId);
        return templateEntity;
    }
    
    
    /**
     * 级联创建目录
     * @param fullDir
     * @return
     * @author Administrator
     * Date 2014年11月13日
     * @version
     */
    public boolean makeCascadeDir(String fullDir) {
        File file =new File(fullDir);    
        //如果文件夹不存在则创建    
        if  (!file .exists()  && !file .isDirectory()) {       
            file.mkdirs();   
        }
        return true;
    }

    
}
