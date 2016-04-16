/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.ContentFieldValuesDao;
import com.kohler.dao.DbSolrMappingDao;
import com.kohler.dao.MasterDataMetaDataDao;
import com.kohler.dao.PageFieldValuesDao;
import com.kohler.dao.VwSolrProductComAttrDao;
import com.kohler.dao.VwSolrSkuAttrDao;
import com.kohler.dao.VwSolrSkuDao;
import com.kohler.dao.utils.CommonFileUrl;
import com.kohler.dao.utils.PropertyUtil;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.DbSolrMappingEntity;
import com.kohler.entity.MasterdataMetadataEntity;
import com.kohler.service.SolrService;
import com.kohler.solr.bean.ContentSolrBean;
import com.kohler.solr.bean.ProductSolrBean;


/**
 * solr service implement
 * 
 * @author zqq
 * @Date 10/9/2014
 */
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private DbSolrMappingDao        dbSolrMappingDao;

    @Autowired
    private VwSolrSkuDao            vmSolrSkuDao;

    @Autowired
    private VwSolrSkuAttrDao        vmSolrSkuAttrDao;

    @Autowired
    private VwSolrProductComAttrDao vmSolrProductComAttrDao;

    @Autowired
    private MasterDataMetaDataDao           masterDataMetaDataDao;

    @Autowired
    private CommonFileUrl           commonFileUrl;
    
    @Autowired
    private PageFieldValuesDao pageFieldValuesDao;
    
    @Autowired
    private ContentFieldValuesDao contentFieldValuesDao;

    // private String serverAddr = "http://192.168.0.105:8088/solr/collection1";

    /**
     * http solr server
     * */
    private HttpSolrServer          server = new HttpSolrServer(
                                                   PropertyUtil
                                                           .getPropertyByKey("solr.server.addr"));

    /**
     * 
     * {@inheritDoc}
     */
    public void importAllIndex() throws Exception {
        server.deleteByQuery("*:*");// clean the solr
        server.commit();

        // clear DB_SOLR_MAPPING
        Integer type = 10160001;
        String solr_time = "";
        Integer masterdataId = 10230001;
        dbSolrMappingDao.deleteByCondition(type);

        // get db_solr_mapping : db_name=>solr_name
        List<DbSolrMappingEntity> solrList = dbSolrMappingDao.selectAll();
        Map<String, String> solrMap = new HashMap<String, String>();
        for (DbSolrMappingEntity dbsolrMapping : solrList) {
            solrMap.put(dbsolrMapping.getDbFieldName(), dbsolrMapping.getSolrFieldName());
        }
        // get all attr from the view and save to the db_solr_mapping
        List<Map<String,Object>> skuAttrList = vmSolrSkuAttrDao.getAttr();
        List<Map<String,Object>> productAttrList = vmSolrProductComAttrDao.getAttr();
        Integer num = 1;
        // Integer max_num = 100;
        for (Map<String,Object> vmSolrSkuAttr : skuAttrList) {
            // if(num>max_num)
            // return ;
            type = 10160003;
            
            DbSolrMappingEntity dbSolrMapping = new DbSolrMappingEntity();
            dbSolrMapping.setDbFieldName(vmSolrSkuAttr.get("KEY_NAME").toString());
            dbSolrMapping.setSolrFieldName("col" + num);
            dbSolrMapping.setType(type);
            dbSolrMapping.setIsEnable(true);
            dbSolrMapping.setCreateTime(new Date());
            dbSolrMappingDao.insert(dbSolrMapping);
            solrMap.put(vmSolrSkuAttr.get("KEY_NAME").toString(), "col" + num);
            num++;
        }
        for (Map<String,Object> vmSolrProductComAttr : productAttrList) {
            type = 10160002;
            DbSolrMappingEntity dbSolrMapping = new DbSolrMappingEntity();
            dbSolrMapping.setDbFieldName(vmSolrProductComAttr.get("KEY_NAME").toString());
            dbSolrMapping.setSolrFieldName("col" + num);
            dbSolrMapping.setType(type);
            dbSolrMapping.setIsEnable(true);
            dbSolrMapping.setCreateTime(new Date());
            dbSolrMappingDao.insert(dbSolrMapping);
            solrMap.put(vmSolrProductComAttr.get("KEY_NAME").toString(), "col" + num);
            num++;
        }

        // get all skus from the view and import into the solr index
        List<Map<String,Object>> skuLists = vmSolrSkuDao.getAll();
        solr_time = skuLists.get(0).get("MODIFY_TIME").toString();
        // import the skus
        try {
            for (Map<String,Object> vmSolrSku : skuLists) {
                ProductSolrBean product = new ProductSolrBean();
                // System.out.println("-----------------------------------------------zqq:"+vmSolrSku.getSkuId());;
                // attr
                type = 10160001;
                DbSolrMappingEntity dbSolrMap = new DbSolrMappingEntity();
                dbSolrMap.setType(type);
                List<DbSolrMappingEntity> typeList = dbSolrMappingDao.selectByCondition(dbSolrMap);
                for (DbSolrMappingEntity dbSolrMapping : typeList) {
                    String properName = dbSolrMapping.getSolrFieldName();
                    String getName = dbSolrMapping.getDbFieldName();
                    // upper the first char
                    if (!Character.isUpperCase(properName.charAt(0)))
                        properName = "set"
                                + (new StringBuilder())
                                        .append(Character.toUpperCase(properName.charAt(0)))
                                        .append(properName.substring(1)).toString();
                    if (!Character.isUpperCase(getName.charAt(0)))
                        getName = commonFileUrl.camelToUnderline(getName);
                    Method skuSetMethod = product.getClass().getMethod(properName, String.class);
                    if (null != vmSolrSku.get(getName))
                        skuSetMethod.invoke(product, vmSolrSku.get(getName).toString());
                }
                // product com attr
                type = 10160002;
                Integer productMetadataId = Integer.parseInt(vmSolrSku.get("PRODUCT_METADATA_ID").toString());
                Integer lang = Integer.parseInt(vmSolrSku.get("LANG").toString());
                List<Object> queryParams = new ArrayList<Object>();
                queryParams.add(productMetadataId);
                queryParams.add(lang);
                List<Map<String,Object>> comAttrList = vmSolrProductComAttrDao.search(queryParams);
                for (Map<String,Object> comAttr : comAttrList) {
                    if (null == comAttr.get("KEY_NAME") || null == solrMap.get(comAttr.get("KEY_NAME").toString()))
                        continue;
                    String properName = solrMap.get(comAttr.get("KEY_NAME").toString()).toString();
                    if (!Character.isUpperCase(properName.charAt(0)))
                        properName = (new StringBuilder())
                                .append(Character.toUpperCase(properName.charAt(0)))
                                .append(properName.substring(1)).toString();
                    Method setMethod = product.getClass().getMethod("set" + properName,
                            String.class);
                    if (null != comAttr.get("VALUE"))
                        setMethod.invoke(product, comAttr.get("VALUE").toString());
                }
                // sku attr
                type = 10160003;
                productMetadataId = Integer.parseInt(vmSolrSku.get("SKU_METADATA_ID").toString());
                queryParams = new ArrayList<Object>();
                queryParams.add(productMetadataId);
                queryParams.add(lang);
                List<Map<String,Object>> skuAttr = vmSolrSkuAttrDao.search(queryParams);
                for (Map<String,Object> vwSolrSkuAttr : skuAttr) {
                    if (null == vwSolrSkuAttr.get("KEY_NAME") || null == solrMap.get(vwSolrSkuAttr.get("KEY_NAME").toString()))
                        continue;
                    System.out.println("-----------------------------------------------zqq:"+vwSolrSkuAttr.get("KEY_NAME").toString());
                    String properName = solrMap.get(vwSolrSkuAttr.get("KEY_NAME").toString()).toString();
                    if (!Character.isUpperCase(properName.charAt(0)))
                        properName = (new StringBuilder())
                                .append(Character.toUpperCase(properName.charAt(0)))
                                .append(properName.substring(1)).toString();
                    Method setMethod = product.getClass().getMethod("set" + properName,
                            String.class);
                    if (null != vwSolrSkuAttr.get("ATTR_VALUE"))
                        setMethod.invoke(product, vwSolrSkuAttr.get("ATTR_VALUE").toString());
                }
                // set id->skuId
                product.setId(vmSolrSku.get("SKU_ID").toString());
                // set imgUrl
                if (CommonConstants.FILE_SOURCE_EXTERNAL == Integer.parseInt(vmSolrSku.get("IMAGE_SOURCE").toString())) {
                    if (null != vmSolrSku.get("LIST_IMAGE_URL").toString())
                        product.setListImageUrl(vmSolrSku.get("LIST_IMAGE_URL").toString());
                    if (null != vmSolrSku.get("DETAIL_IMAGE1_URL").toString())
                        product.setDetailImageUrl(vmSolrSku.get("DETAIL_IMAGE1_URL").toString());
                } else if (CommonConstants.FILE_SOURCE_INTERNAL == Integer.parseInt(vmSolrSku.get("IMAGE_SOURCE").toString())) {
                    if (null != vmSolrSku.get("LIST_IAMGE_ID"))
                        product.setListImageUrl(commonFileUrl.combineFileUrlPath(Integer.parseInt(vmSolrSku.get("LIST_IAMGE_ID").toString())));
                    if (null != vmSolrSku.get("DETAIL_IMAGE1_ID"))
                        product.setDetailImageUrl(commonFileUrl.combineFileUrlPath(Integer.parseInt(vmSolrSku.get("DETAIL_IMAGE1_ID").toString())));
                }
                //set targetUrl
                String platform = "pc";
                String targetUrl = commonFileUrl.getSiteUrl(platform, vmSolrSku);
                product.setTargetUrl(targetUrl);

                server.addBean(product);
                server.commit();
            }
        } catch (Exception e) {
            throw e;
        }

        // update the solr_time
        MasterdataMetadataEntity masterdata = masterDataMetaDataDao.selectById(masterdataId);
        masterdata.setMasterdataCode(solr_time);
        try {
            masterDataMetaDataDao.update(masterdata);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * 
     * {@inheritDoc}
     */
    public void importDeltaIndex() throws Exception {
         Integer keyId = 10230001;
         String solr_time = "";
         
      // get db_solr_mapping : db_name=>solr_name
         List<DbSolrMappingEntity> solrList = dbSolrMappingDao.selectAll();
         Map<String, String> solrMap = new HashMap<String, String>();
         for (DbSolrMappingEntity dbsolrMapping : solrList) {
             solrMap.put(dbsolrMapping.getDbFieldName(), dbsolrMapping.getSolrFieldName());
         }
         // get all attr from the view and save to the db_solr_mapping
         List<Map<String,Object>> skuAttrList = vmSolrSkuAttrDao.getAttr();
         List<Map<String,Object>> productAttrList = vmSolrProductComAttrDao.getAttr();
         Integer num = 1;
         Integer type = 0;
         // Integer max_num = 100;
         for (Map<String,Object> vmSolrSkuAttr : skuAttrList) {
             solrMap.put(vmSolrSkuAttr.get("KEY_NAME").toString(), "col" + num);
         }
         for (Map<String,Object> vmSolrProductComAttr : productAttrList) {
             solrMap.put(vmSolrProductComAttr.get("KEY_NAME").toString(), "col" + num);
         }

         // get all skus from the view and import into the solr index
         solr_time = masterDataMetaDataDao.selectById(keyId).getMasterdataCode();
         List<Map<String,Object>> skuLists = new ArrayList<Map<String,Object>>();
         if(!"SOLR_UPDATE_TIME".equals(solr_time))
             skuLists = vmSolrSkuDao.getByTime(solr_time);
         else
             skuLists = vmSolrSkuDao.getAll();
         solr_time = skuLists.get(0).get("MODIFY_TIME").toString();
         // import the skus
         try {
             for (Map<String,Object> vmSolrSku : skuLists) {
                 ProductSolrBean product = new ProductSolrBean();
                 // attr
                 type = 10160001;
                 DbSolrMappingEntity dbSolrMap = new DbSolrMappingEntity();
                 dbSolrMap.setType(type);
                 List<DbSolrMappingEntity> typeList = dbSolrMappingDao.selectByCondition(dbSolrMap);
                 for (DbSolrMappingEntity dbSolrMapping : typeList) {
                     String properName = dbSolrMapping.getSolrFieldName();
                     String getName = dbSolrMapping.getDbFieldName();
                     // upper the first char
                     if (!Character.isUpperCase(properName.charAt(0)))
                         properName = "set"
                                 + (new StringBuilder())
                                         .append(Character.toUpperCase(properName.charAt(0)))
                                         .append(properName.substring(1)).toString();
                     if (!Character.isUpperCase(getName.charAt(0)))
                         getName = commonFileUrl.camelToUnderline(getName);
                     Method skuSetMethod = product.getClass().getMethod(properName, String.class);
                     if (null != vmSolrSku.get(getName))
                         skuSetMethod.invoke(product, vmSolrSku.get(getName).toString());
                 }
                 // product com attr
                 type = 10160002;
                 if(null==vmSolrSku.get("PRODUCT_METADATA_ID")){
                     System.out.println("add indexs failed--------PRODUCT_METADATA_ID is null-------skuId:"+vmSolrSku.get("SKU_ID").toString());
//                     continue;
                     break;//test
                 }
                 Integer productMetadataId = Integer.parseInt(vmSolrSku.get("PRODUCT_METADATA_ID").toString());
                 Integer lang = Integer.parseInt(vmSolrSku.get("LANG").toString());
                 List<Object> queryParams = new ArrayList<Object>();
                 queryParams.add(productMetadataId);
                 queryParams.add(lang);
                 List<Map<String,Object>> comAttrList = vmSolrProductComAttrDao.search(queryParams);
                 for (Map<String,Object> comAttr : comAttrList) {
                     if (null == comAttr.get("KEY_NAME") || null == solrMap.get(comAttr.get("KEY_NAME").toString()))
                         continue;
                     String properName = solrMap.get(comAttr.get("KEY_NAME").toString()).toString();
                     if (!Character.isUpperCase(properName.charAt(0)))
                         properName = (new StringBuilder())
                                 .append(Character.toUpperCase(properName.charAt(0)))
                                 .append(properName.substring(1)).toString();
                     Method setMethod = product.getClass().getMethod("set" + properName,
                             String.class);
                     if (null != comAttr.get("VALUE"))
                         setMethod.invoke(product, comAttr.get("VALUE").toString());
                 }
                 // sku attr
                 type = 10160003;
                 productMetadataId = Integer.parseInt(vmSolrSku.get("SKU_METADATA_ID").toString());
                 queryParams = new ArrayList<Object>();
                 queryParams.add(productMetadataId);
                 queryParams.add(lang);
                 List<Map<String,Object>> skuAttr = vmSolrSkuAttrDao.search(queryParams);
                 for (Map<String,Object> vwSolrSkuAttr : skuAttr) {
                     if (null == vwSolrSkuAttr.get("KEY_NAME") || null == solrMap.get(vwSolrSkuAttr.get("KEY_NAME").toString()))
                         continue;
                     String properName = solrMap.get(vwSolrSkuAttr.get("KEY_NAME").toString()).toString();
                     if (!Character.isUpperCase(properName.charAt(0)))
                         properName = (new StringBuilder())
                                 .append(Character.toUpperCase(properName.charAt(0)))
                                 .append(properName.substring(1)).toString();
                     Method setMethod = product.getClass().getMethod("set" + properName,
                             String.class);
                     if (null != vwSolrSkuAttr.get("ATTR_VALUE"))
                         setMethod.invoke(product, vwSolrSkuAttr.get("ATTR_VALUE").toString());
                 }
                 // set id->skuId
                 product.setId(vmSolrSku.get("SKU_ID").toString());
                 // set imgUrl
                 if (CommonConstants.FILE_SOURCE_EXTERNAL == Integer.parseInt(vmSolrSku.get("IMAGE_SOURCE").toString())) {
                     if (null != vmSolrSku.get("LIST_IMAGE_URL").toString())
                         product.setListImageUrl(vmSolrSku.get("LIST_IMAGE_URL").toString());
                     if (null != vmSolrSku.get("DETAIL_IMAGE1_URL").toString())
                         product.setDetailImageUrl(vmSolrSku.get("DETAIL_IMAGE1_URL").toString());
                 } else if (CommonConstants.FILE_SOURCE_INTERNAL == Integer.parseInt(vmSolrSku.get("IMAGE_SOURCE").toString())) {
                     if (null != vmSolrSku.get("LIST_IAMGE_ID"))
                         product.setListImageUrl(commonFileUrl.combineFileUrlPath(Integer.parseInt(vmSolrSku.get("LIST_IAMGE_ID").toString())));
                     if (null != vmSolrSku.get("DETAIL_IMAGE1_ID"))
                         product.setDetailImageUrl(commonFileUrl.combineFileUrlPath(Integer.parseInt(vmSolrSku.get("DETAIL_IMAGE1_ID").toString())));
                 }
                 //set targetUrl
                 String platform = "pc";
                 String targetUrl = commonFileUrl.getSiteUrl(platform, vmSolrSku);
                 product.setTargetUrl(targetUrl);

                 server.addBean(product);
                 server.commit();
             }
         } catch (Exception e) {
             throw e;
         }
       
         // update the solr_time
         MasterdataMetadataEntity masterdata = masterDataMetaDataDao.selectById(keyId);
         masterdata.setMasterdataCode(solr_time);
         try {
             masterDataMetaDataDao.update(masterdata);
         } catch (Exception e) {
             throw e;
         }
    }
    
    /**
     * 
     * {@inheritDoc}
     */
    public void importContentAllIndex() throws Exception{
        server = new HttpSolrServer(PropertyUtil.getPropertyByKey("solr.server.solr")+"collectionArticle");
        server.deleteByQuery("*:*");// clean the solr
        server.commit();

        String solr_time = "";
        Integer masterdataId = 10230002;

        List<Map<String,Object>> pageList = pageFieldValuesDao.searchByFieldname("Article");
        try {
            for(Map<String,Object> page:pageList) {
                ContentSolrBean content = new ContentSolrBean();
                content.setPageId(page.get("PAGE_ID").toString());
                ContentFieldValues contentSearch = new ContentFieldValues();
                contentSearch.setContentMetadataId(Integer.getInteger(page.get("FIELD_VALUE").toString()));
                List<ContentFieldValues> contentList = contentFieldValuesDao.selectByCondition(contentSearch);
                for(ContentFieldValues contentField:contentList) {
                    if(null==contentField.getFieldName()||"".equals(contentField.getFieldName()))
                        continue;
                    String properName = contentField.getFieldName();
                    if (!Character.isUpperCase(properName.charAt(0)))
                        properName = (new StringBuilder())
                                .append(Character.toUpperCase(properName.charAt(0)))
                                .append(properName.substring(1)).toString();
                    Method setMethod = content.getClass().getMethod("set" + properName,
                            String.class);
                    if (null != contentField.getFieldValue())
                        setMethod.invoke(content, contentField.getFieldValue());
                }
                //set url
                String platform = "pc";
                String contentUrl = commonFileUrl.getContentUrl(platform,Integer.getInteger(page.get("PAGE_ID").toString()));
                content.setContentUrl(contentUrl);
                
                server.addBean(content);
                server.commit();
            }
        } catch (Exception e) {
            throw e;
        }
     // update the solr_time
        MasterdataMetadataEntity masterdata = masterDataMetaDataDao.selectById(masterdataId);
        masterdata.setMasterdataCode(solr_time);
        try {
            masterDataMetaDataDao.update(masterdata);
        } catch (Exception e) {
            throw e;
        }
       
    }
    
    
    /**
     * 
     * {@inheritDoc}
     */
    public void importContentDeltaIndex() throws Exception {
        server = new HttpSolrServer(PropertyUtil.getPropertyByKey("solr.server.solr")+"collectionArticle");
        server.deleteByQuery("*:*");// clean the solr
        server.commit();
        
        Integer masterdataId = 10230002;
        String solr_time = masterDataMetaDataDao.selectById(masterdataId).getMasterdataCode();
        List<Map<String,Object>> pageList = new ArrayList<Map<String,Object>>();
        if(!"SOLR_CONTENT_UPDATE_TIME".equals(solr_time))
            pageList = pageFieldValuesDao.searchByTime("Article",solr_time);
        else
            pageList = pageFieldValuesDao.searchByFieldname("Article");
        try {
            for(Map<String,Object> page:pageList) {
                ContentSolrBean content = new ContentSolrBean();
                content.setPageId(page.get("PAGE_ID").toString());
                ContentFieldValues contentSearch = new ContentFieldValues();
                contentSearch.setContentMetadataId(Integer.getInteger(page.get("FIELD_VALUE").toString()));
                List<ContentFieldValues> contentList = contentFieldValuesDao.selectByCondition(contentSearch);
                for(ContentFieldValues contentField:contentList) {
                    if(null==contentField.getFieldName()||"".equals(contentField.getFieldName()))
                        continue;
                    String properName = contentField.getFieldName();
                    if (!Character.isUpperCase(properName.charAt(0)))
                        properName = (new StringBuilder())
                                .append(Character.toUpperCase(properName.charAt(0)))
                                .append(properName.substring(1)).toString();
                    Method setMethod = content.getClass().getMethod("set" + properName,
                            String.class);
                    if (null != contentField.getFieldValue())
                        setMethod.invoke(content, contentField.getFieldValue());
                }
                //set url
                String platform = "pc";
                String contentUrl = commonFileUrl.getContentUrl(platform,Integer.getInteger(page.get("PAGE_ID").toString()));
                content.setContentUrl(contentUrl);
                
                server.addBean(content);
                server.commit();
            }
        } catch (Exception e) {
            throw e;
        }
     // update the solr_time
        MasterdataMetadataEntity masterdata = masterDataMetaDataDao.selectById(masterdataId);
        masterdata.setMasterdataCode(solr_time);
        try {
            masterDataMetaDataDao.update(masterdata);
        } catch (Exception e) {
            throw e;
        }
    }
}
