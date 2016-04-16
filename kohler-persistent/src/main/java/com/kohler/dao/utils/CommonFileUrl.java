/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.CategoryPathDao;
import com.kohler.dao.FileAssetDao;
import com.kohler.dao.FolderDao;
import com.kohler.dao.PageDao;
import com.kohler.dao.ProductSolrDao;
import com.kohler.dao.PublishFolderDao;
import com.kohler.dao.SectionDao;
import com.kohler.dao.SiteSettingDao;
import com.kohler.entity.CategoryPathEntity;
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.FolderEntity;
import com.kohler.entity.PageEntity;
import com.kohler.entity.ProductSolrEntity;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SectionEntity;
import com.kohler.entity.SiteSettingEntity;


/**
 * 准备数据中用到的公共方法
 *
 * @author Administrator
 * @Date 2014年10月27日
 */
@Component
public class CommonFileUrl {
    
    protected final static Logger logger = Logger.getLogger(CommonFileUrl.class);
    
    @Autowired
    private FileAssetDao fileAssetDao;
    
    @Autowired
    private FolderDao folderDao;
    
    @Autowired
    private SiteSettingDao siteSettingDao;
    
    @Autowired
    private CategoryPathDao categoryPathDao;
    
    @Autowired
    private ProductSolrDao productDao;
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private SectionDao sectionDao;
    
    @Autowired
    private PublishFolderDao publishFolderDao;
    
    /**
     * 递归获取文件目录路径
     * @param folderId
     * @param basePath
     * @return
     * @author Administrator
     * Date 2014年11月7日
     * @version
     */
    public String getFileFolderPath(Integer folderId,StringBuilder basePath) {
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
     *文件 路径组合
     * @param assetId
     * @return
     * @author Administrator
     * Date 2014年11月6日
     * @version
     */
    public String combineFileUrlPath(Object fileAssetId) {
        try {
            FileAssetEntity fileAssetEntity = fileAssetDao.selectById(Integer.valueOf(fileAssetId.toString()));
            String physicalFileName = fileAssetEntity.getPhysicalFilename();
            Integer folderId = fileAssetEntity.getFolderId();
            
            String folder = getFileFolderPath(folderId, new StringBuilder());

            SiteSettingEntity uploadServer = getSiteSetting(CommonConstants.FILE_PLATFORM);//上传文件服务配置
            String fileURL = uploadServer.getSiteDomainName();//发布物理路径
            
            String retFilePath = fileURL + folder+physicalFileName;
            return retFilePath;    
        } catch (Exception e) {
            logger.debug("combineUrlPath Failure fileAssetId: "+fileAssetId+" func:combineFileUrlPath line:70");
            return "";
        }
        
    }
    
    /**
     * 获取站点配置
     * @param platform
     * @return
     * @author Administrator
     * Date 2014年11月7日
     * @version
     */
    protected SiteSettingEntity getSiteSetting(String platform) {
        Map<String,String> caseSiteSet = new HashMap<String, String>();
        caseSiteSet.put(CommonConstants.PC_PLATFORM, CommonConstants.PC_WEBSITE);
        caseSiteSet.put(CommonConstants.MOBILE_PLATFORM, CommonConstants.MOBILE_WEBSITE);
        caseSiteSet.put(CommonConstants.FILE_PLATFORM, CommonConstants.FILE_SERVER);
        caseSiteSet.put(CommonConstants.IMAGE_PLATFORM, CommonConstants.IMAGE_SERVER);
        caseSiteSet.put(CommonConstants.PREVIEW_PLATFORM, CommonConstants.PREVIEW_SERVER);
        
        
        
        SiteSettingEntity siteSettingEntity = new SiteSettingEntity();
        siteSettingEntity.setSiteName(caseSiteSet.get(platform));
        siteSettingEntity.setIsEnable(true);
        List<SiteSettingEntity> siteSetting =siteSettingDao.selectByCondition(siteSettingEntity);
        return siteSetting.get(0);
    }
    
    /**
     * 匹配一些变量
     * @param str
     * @author Administrator
     * Date 2014年10月30日
     * @version
     */
    public static  String patternCompile(String str,Map<String,String> replaceMap) {

        if(str == null || "".equals(str)) {
            return "";
        }
        str = str.toLowerCase();
        String[] strs = str.split("_");
        str = strs[0];
        for(int i=1;i<strs.length;i++){
            str += strs[i].substring(0,1).toUpperCase() + strs[i].substring(1);
        }
        
        Pattern p = Pattern.compile("@+(\\w*)");
        Matcher m = p.matcher(str);
        if(m.groupCount() >0) {
            while (m.find()) {
                Object paramVal = replaceMap.get(m.group(1));
                if(replaceMap.containsKey(m.group(1)) && paramVal != null) {
                    str = str.replace(m.group(0),paramVal.toString());                
                }
            }
            StringBuilder retStr = new StringBuilder();
            String[] _tmpStr = str.split("&");
            for(String _tmp : _tmpStr) {
                retStr.append(_tmp);
            }
            return retStr.toString();
        } else {
            return str;
        }
        
    }
    
    public String getCategoryPath(Integer categoryId,StringBuilder basePath, String platform){
        CategoryPathEntity categoryPath = categoryPathDao.selectByPrimaryKey(categoryId);
        String category = "";
        if(CommonConstants.PC_PLATFORM.equals(platform)){
            category = categoryPath.getPcPath();
        } else if(CommonConstants.MOBILE_PLATFORM.equals(platform)){
            category = categoryPath.getMobilePath();
        }
        basePath.insert(0, category);
        if(categoryPath.getParentId() != null && !(categoryPath.getParentId()==0)) {
            getCategoryPath(categoryPath.getParentId(),basePath,platform);
        }
        return basePath.toString();
    }
    
    public String getSiteUrl(String platform,Map<String,Object> vwSolrSkuEntity) throws Exception {
        //get base url
        SiteSettingEntity siteSet = getSiteSetting(platform);
        String baseUrl = siteSet.getSiteDomainName();
        //get catelog&category&subcategory folder
        String category = "";
        Integer subCategoryId = Integer.parseInt(vwSolrSkuEntity.get("SUB_CATEGORY_ID").toString());
        category += getCategoryPath(subCategoryId, new StringBuilder(), platform);
        //get product folder
        String folder = "";
        CategoryPathEntity categoryPath = categoryPathDao.selectByPrimaryKey(subCategoryId);
        if(CommonConstants.PC_PLATFORM.equals(platform)){
            folder = categoryPath.getPcFolderPath();
        } else if(CommonConstants.MOBILE_PLATFORM.equals(platform)){
            folder = categoryPath.getMobileFolderPath();
        }
        Integer productId = Integer.parseInt(vwSolrSkuEntity.get("PRODUCT_ID").toString());
        ProductSolrEntity product = productDao.selectByKeyId(productId);
        Map<String,String> replaceMap = new HashMap<String ,String>();
        Field[] fields = product.getClass().getDeclaredFields();
        for(int i = 0;i < fields.length;i++){
            String fieldName = fields[i].getName();
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = product.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(product, new Object[] {});
            if(null!=value)
                replaceMap.put(fieldName, value.toString());
            else
                replaceMap.put(fieldName, "");
        }
        folder = "product/" + patternCompile(folder,replaceMap);
        String siteUrl = baseUrl + category + folder + vwSolrSkuEntity.get("SKU_CODE").toString() + "/";
        return siteUrl;
    }
   
    public String getContentUrl(String platform,Integer pageId) {
      //get base url
        SiteSettingEntity siteSet = getSiteSetting(platform);
        String baseUrl = siteSet.getSiteDomainName();
        PageEntity pageEntity = pageDao.selectById(pageId);
        //获取发布路径
        List<SectionEntity> tree = new ArrayList<SectionEntity>(); //存放sectionEntity的层级关系
        
        setSectionTree(pageId,tree);//获取sectionEntity的层级关系并存入tree
        StringBuilder publishFolder = new StringBuilder();
        for(int i= tree.size()-1;i >=0; i--) {
            PublishFolderEntity publishFolderEntity =publishFolderDao.selectById(tree.get(i).getPublishFolderId());
            publishFolder.append(publishFolderEntity.getPublishFolderPath());
            
        }
        return baseUrl + publishFolder.toString() + pageEntity.getPhysicalName();
    }
      
    public String camelToUnderline(String param){
        char UNDERLINE='_';
        if (param==null||"".equals(param.trim())){  
            return "";  
        }  
        int len=param.length();  
        StringBuilder sb=new StringBuilder(len);  
        for (int i = 0; i < len; i++) {  
            char c=param.charAt(i);  
            if (Character.isUpperCase(c)){  
                sb.append(UNDERLINE);  
                sb.append(Character.toLowerCase(c));  
            }else{  
                sb.append(c);  
            }  
        }  
        return sb.toString().toUpperCase();  
    } 
    
    public void setSectionTree(Integer currentSectionId,List<SectionEntity> tree) {
        //try {
            SectionEntity sectionEntity = sectionDao.selectById(currentSectionId);
            tree.add(sectionEntity);
            if(sectionEntity.getParentId() != 0) {
                setSectionTree(sectionEntity.getParentId(), tree);
            }
//        } catch (Exception e) {
//            logger.debug("get sectionEntity fail currentSectionId:"+currentSectionId);
//        }
    }
    
}
