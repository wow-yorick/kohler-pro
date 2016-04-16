/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.CategoryDao;
import com.kohler.dao.CategoryMetadataDao;
import com.kohler.dao.PageDao;
import com.kohler.dao.PublishFolderDao;
import com.kohler.dao.TemplateDao;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.PageEntity;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.url.CategoryUrlAnalysis;
import com.kohler.service.util.DataCacheServiceImplBaseDB;

/**
 * category
 *
 * @author Administrator
 * @Date 2014年11月24日
 */
@Component
public class BaseForCategory {
    
    private final static Logger logger = Logger.getLogger(BaseForCategory.class);
    
    public final static String CATEGORY_BREADCRUMB = "CATEGORY_BREADCRUMB";
    
    public final static String SEGMENT_SPLIT = "=";//段分割
    
    public final static String SLICE_SPLIT = "!"; //片分割
    
    @Autowired
    private CategoryDao categoryDao;
    
    @Autowired
    private CategoryMetadataDao categoryMetadataDao;
    
    @Autowired
    private CategoryUrlAnalysis categoryUrlAnalysis;
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private TemplateDao templateDao;
    
    @Autowired
    private PublishFolderDao publishFolderDao;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private DataCacheServiceImplBaseDB dataCacheServiceImplBaseDB;
    
    
    /**
     * 获取产品面包屑信息
     * @return
     * @author Administrator
     * Date 2014年10月28日
     * @version
     * @throws DataException 
     */
    public List<Map<String,Object>> categoryBreadCrumb(Integer categoryMetadataId,ConfPrepareData conf) throws DataException {
        
        List<Map<String,Object>> breadCrumbs = new ArrayList<Map<String,Object>>();//存放返回的面包屑
        
        //cache data hit
        String catBreadCrumb = dataCacheServiceImplBaseDB.hget(BaseForCategory.CATEGORY_BREADCRUMB, categoryMetadataId.toString()+conf.getLang().toString()+conf.getWebsitePlatform());
        StringBuffer cacheData = new StringBuffer();
        if(null != catBreadCrumb) {
            String[] catbTmp = catBreadCrumb.split(BaseForCategory.SEGMENT_SPLIT);
            for(String _catbTmp : catbTmp) {
                String[] nameAndUrl = _catbTmp.split(BaseForCategory.SLICE_SPLIT);
                Map<String,Object> breadBean = new HashMap<String, Object>();//面包屑单元素容器
                breadBean.put("name", nameAndUrl[0]);//获取面包屑名称
                breadBean.put("url", nameAndUrl[1]);
                breadCrumbs.add(breadBean);
            }
            return breadCrumbs;
        }
        
        //首页面包屑
        Map<String, Object> homeBreadBean =  getHomeBreadcrumb(conf);
        breadCrumbs.add(homeBreadBean);
        //cache data 
        cacheData.append(homeBreadBean.get("name")+BaseForCategory.SLICE_SPLIT+homeBreadBean.get("url"));
        cacheData.append(BaseForCategory.SEGMENT_SPLIT);
        
        //递归获取父级路径CategoryMetadata树
        List<CategoryEntity> tree = new ArrayList<CategoryEntity>(); //存放目录树
        setCategoryTree(categoryMetadataId,tree, conf);
        for(int i= tree.size()-1;i >=0; i--) {
            Map<String,Object> breadBean = new HashMap<String, Object>();//面包屑单元素容器
            CategoryEntity categoryEntity = tree.get(i);//获取当前category
            String brName = categoryEntity.getBreadcrumbName();
            breadBean.put("name", brName);//获取面包屑名称
            String url = categoryUrlAnalysis.getUrl(categoryEntity.getCategoryMetadataId(), conf);//获取url
            breadBean.put("url", url);
            breadCrumbs.add(breadBean);
            
            //cache data
            cacheData.append(brName+BaseForCategory.SLICE_SPLIT+url);
            cacheData.append(BaseForCategory.SEGMENT_SPLIT);
        }
        //cache db
        dataCacheServiceImplBaseDB.hset(BaseForCategory.CATEGORY_BREADCRUMB, categoryMetadataId.toString()+conf.getLang().toString()+conf.getWebsitePlatform(), cacheData.substring(0, cacheData.length()-1));
        
        return breadCrumbs;
    }

    /**
     * 获取首页的url
     * @return
     * @author Administrator
     * Date 2014年12月4日
     * @version
     */
    public Map<String, Object> getHomeBreadcrumb(ConfPrepareData conf) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        //存放首页面包屑
        PageEntity pageEntity = new PageEntity();
        pageEntity.setIsEnable(true);
        pageEntity.setDatatypeId(CommonConstants.PAGE_DATATYPE_HOMEPAGE);//首页页面类型
        if(conf.getLang() == CommonConstants.LOCALE_CN && conf.getWebsitePlatform() == CommonConstants.PC_PLATFORM) { //PC/CN
            pageEntity.setSectionId(CommonConstants.SECTION_HOME_PC_CN);
        } else if(conf.getLang() == CommonConstants.LOCALE_EN && conf.getWebsitePlatform() == CommonConstants.PC_PLATFORM) { //PC/EN
            pageEntity.setSectionId(CommonConstants.SECTION_HOME_PC_EN);
        } else if(conf.getLang() == CommonConstants.LOCALE_CN && conf.getWebsitePlatform() == CommonConstants.MOBILE_PLATFORM) { //MOBILE/CN
            pageEntity.setSectionId(CommonConstants.SECTION_HOME_MOBILE_CN);
        }else {
            pageEntity.setSectionId(CommonConstants.SECTION_HOME_MOBILE_EN);
        }
        
        List<PageEntity> pageEntities = pageDao.selectByCondition(pageEntity);
        if(pageEntities != null && pageEntities.size() > 0){
            String pageName = pageEntities.get(0).getPageName();
            SiteSettingEntity siteSettingEntity = baseCommon.getSitePlatformSet(conf);
            String homeUrl = siteSettingEntity.getSiteDomainName(); //获取首页url
            retMap.put("name", pageName);
            retMap.put("url", homeUrl);
        }
        return retMap;
    }
   
    /**
     * 从当前目录逐级找父目录
     * @param currentCatgoryMetaId
     * @param tree
     * @author Administrator
     * Date 2014年10月28日
     * @version
     * @throws Exception 
     */
    private void setCategoryTree(Integer currentCatgoryMetaId,List<CategoryEntity> tree,ConfPrepareData conf) throws DataException {
  
        //try {
            //查询目录主表信息
            CategoryMetadataEntity categoryMetadataEntity = categoryMetadataDao.selectById(currentCatgoryMetaId);
            
            //查询目录对应语言的实际信息
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setCategoryMetadataId(categoryMetadataEntity.getCategoryMetadataId());
            categoryEntity.setLang(conf.getLang());
            List<CategoryEntity> categoryRet = categoryDao.selectByCondition(categoryEntity);
            
            //查询到的目录信息加入树中
            if(categoryRet != null && categoryRet.size() > 0) {
                tree.add(categoryRet.get(0));
            }
            
            //如果目录父节点不为null,递归查询
            Integer parentId = categoryMetadataEntity.getParentId();
            if(parentId != null && !parentId.equals(0)) {
                setCategoryTree(categoryMetadataEntity.getParentId(), tree, conf);
            }
        //} catch (Exception e) {
//            logger.warn("get categoryEntity fail currentCatgoryMetaId is "+currentCatgoryMetaId);
//            throw new DataException(e);
//        }
    }
    
    /**
     * 递归获取category的发布folder
     * @param categoryMetadataId
     * @param folder
     * @return
     * @author Administrator
     * Date 2014年11月13日
     * @version
     * @throws DataException 
     */
    public String getCategoryPublishFolder(Integer categoryMetadataId, StringBuilder folder,ConfPrepareData conf) throws DataException {
        //try {
            //查询主表信息
            CategoryMetadataEntity categoryMetadataEntity = categoryMetadataDao.selectById(categoryMetadataId);
            //查询模板信息
            TemplateEntity templateEntity = getTemplateInfoByCategoryMetadataId(categoryMetadataId, conf);
            Integer publishFolderId = templateEntity.getPublishFolderId();//发布目录id
            //获取发布路径
            PublishFolderEntity publishFolderEntity = publishFolderDao.selectById(publishFolderId);
            String publishFolder = publishFolderEntity.getPublishFolderPath();//具体路径
            folder.insert(0, publishFolder);
            Integer parentId = categoryMetadataEntity.getParentId(); 
            if(parentId != null && parentId != 0) {
                getCategoryPublishFolder(parentId, folder, conf);
            }
            
//        } catch (Exception e) {
//            logger.warn("get category publish folder fail!categoryMetadataId is "+ categoryMetadataId);
//            throw new DataException(e);
//        }
        return folder.toString();
    }
    
    /**
     * 面包屑中组织路径如果为第三级需要手动加sc/
     * @param categoryMetadataId
     * @param folder
     * @param conf
     * @return
     * @throws DataException
     * @author Administrator
     * Date 2014年12月16日
     * @version
     */
    public String getCategoryBreadcrumbPath(Integer categoryMetadataId, StringBuilder folder,ConfPrepareData conf) throws DataException {
        //try {
            //查询主表信息
            CategoryMetadataEntity categoryMetadataEntity = categoryMetadataDao.selectById(categoryMetadataId);
            //查询模板信息
            TemplateEntity templateEntity = getTemplateInfoByCategoryMetadataId(categoryMetadataEntity.getCategoryMetadataId(), conf);
            Integer publishFolderId = templateEntity.getPublishFolderId();//发布目录id
            //获取发布路径
            PublishFolderEntity publishFolderEntity = publishFolderDao.selectById(publishFolderId);
            String publishFolder = publishFolderEntity.getPublishFolderPath();//具体路径
            folder.insert(0, publishFolder);
            if(categoryMetadataEntity.getLevel().equals(3)) {
                folder.insert(0, "sc/");
            }
            Integer parentId = categoryMetadataEntity.getParentId(); 
            if(parentId != null && parentId != 0) {
                getCategoryPublishFolder(parentId, folder, conf);
            }
            
//        } catch (Exception e) {
//            logger.warn("get category publish folder fail!categoryMetadataId is "+categoryMetadataId);
//            throw new DataException(e);
//        }
        return folder.toString();
    }
    
    /**
     * 通过categoryMetadataId获取模板信息
     * @param categoryMetadataId
     * @return
     * @author Administrator
     * Date 2014年11月13日
     * @version
     * @throws DataException 
     * @throws PublishException 
     */
    public TemplateEntity getTemplateInfoByCategoryMetadataId(Integer categoryMetadataId, ConfPrepareData conf) throws DataException{
        
        TemplateEntity templateEntity = new TemplateEntity();
        //try {
            //查询category信息
            CategoryEntity categoryFilter = new CategoryEntity();
            categoryFilter.setCategoryMetadataId(categoryMetadataId);
            categoryFilter.setLang(conf.getLang());
            List<CategoryEntity> categoryEntities = categoryDao.selectByCondition(categoryFilter);
            //获取模板ID
            int templateId = categoryEntities.get(0).getPcTemplateId();
            if(conf.getWebsitePlatform() == CommonConstants.PC_PLATFORM) {
                templateId = categoryEntities.get(0).getPcTemplateId();
            } else {
                templateId = categoryEntities.get(0).getMobileTemplateId();
            }
            //获取模板
            templateEntity = templateDao.selectById(templateId);
            
//        } catch (Exception e) {
//            logger.warn("get template fail,category_matedata_id is "+categoryMetadataId);
//            throw new DataException(e);
//        }
        return templateEntity;
    }
    
    /**
     * 获取category map
     * @return
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    public Map<String, Object> getCategory(ConfPrepareData conf) {
        StringBuilder sql = new StringBuilder("SELECT CATEGORY_METADATA_ID,CATEGORY_NAME,BREADCRUMB_NAME,PC_TEMPLATE_ID,MOBILE_TEMPLATE_ID,SEO_TITLE_PC FROM VW_CATEGORY WHERE LANG =");
        
        sql.append(conf.getLang());
        sql.append(" AND CATEGORY_METADATA_ID = ");
        sql.append(conf.getDataId());
        
        List<Map<String,Object>> resultMap = categoryDao.selectByConditionWithMap(sql.toString(), new ArrayList<Object>());
        if(resultMap != null && resultMap.size() > 0) {
            return resultMap.get(0);            
        }else {
            return new HashMap<String, Object>();
        }
        
    }
}
