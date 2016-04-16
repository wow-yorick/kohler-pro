/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.ProductCADDao;
import com.kohler.dao.utils.CommonFileUrl;
import com.kohler.dao.utils.PropertyUtil;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.service.CADCutoutService;
import com.kohler.solr.bean.CADSolrBean;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月30日
 */
@Service
public class CADCutoutServiceImpl implements CADCutoutService {

    @Autowired
    private ProductCADDao productCADDao;
    
    @Autowired
    private CommonFileUrl commonFileUrl;
    
    private HttpSolrServer  server = new HttpSolrServer(PropertyUtil
            .getPropertyByKey("solr.server.solr")+"mycontent");
    /**
     * {@inheritDoc}
     */
    @Override
    public void importAllIndex(Integer lang) throws Exception {
        if(lang == 2){
            server = new HttpSolrServer(PropertyUtil.getPropertyByKey("solr.server.solr")+"encontent");
        }
        String sql ="select pc.LANG,pc.PRODUCT_CAD_METADATA_ID,pcm.CAD_TYPE,m.MASTERDATA_NAME,pc.PRODUCT_CAD_NAME,pc.FILE_SOURCE,pc.FILE_URL,pc.FILE_ID, "+
        " c.CATEGORY_METADATA_ID,c.CATEGORY_NAME,c.LEVEL,pcm.PRODUCT_METADATA_ID "+
        " from PRODUCT_CAD pc "+
        " left join PRODUCT_CAD_METADATA pcm on pc.PRODUCT_CAD_METADATA_ID = pcm.PRODUCT_CAD_METADATA_ID "+
        " left join MASTERDATA m on pcm.CAD_TYPE = m.MASTERDATA_METADATA_ID "+
        " left join PRODUCT_METADATA pm on pcm.PRODUCT_METADATA_ID = pm.PRODUCT_METADATA_ID "+
        " left join VW_CATEGORY c on pm.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID "+
        " where c.LANG = pc.LANG and m.LANG = pc.LANG and pc.IS_ENABLE = 1 and pm.IS_ENABLE = 1 "+ 
        " and c.IS_ENABLE = 1 and pcm.IS_ENABLE = 1 and pc.LANG = ?";
        //Integer lang = 1;
        List<Map<String, Object>> cadlist = productCADDao.getMapList(sql,lang);
        for (Map<String, Object> map : cadlist) {
            CADSolrBean cadSolrBean = new CADSolrBean();
            String productCADMetadataId = map.get("PRODUCT_CAD_METADATA_ID").toString();
            cadSolrBean.setId("CAD"+productCADMetadataId);
            cadSolrBean.setProductCADMetadataId(productCADMetadataId);
            cadSolrBean.setProductMetadataId(map.get("PRODUCT_METADATA_ID").toString());
            cadSolrBean.setProductCADName(map.get("PRODUCT_CAD_NAME").toString());
            cadSolrBean.setCategoryMetadataId(map.get("CATEGORY_METADATA_ID").toString());
            cadSolrBean.setCategoryName(map.get("CATEGORY_NAME").toString());
            cadSolrBean.setCadType(map.get("CAD_TYPE").toString());
            cadSolrBean.setCadTypeName(map.get("MASTERDATA_NAME").toString());
            if(map.get("LEVEL")!=null&&"3".equals(map.get("LEVEL").toString())){
                cadSolrBean.setSubCategoryMetadataId(map.get("CATEGORY_METADATA_ID").toString());
                cadSolrBean.setSubCategoryName(map.get("CATEGORY_NAME").toString());
            }
            String fileurl = "";
            if(map.get("FILE_SOURCE")!=null&&String.valueOf(MasterDataCodeConstant.FILE_SOURCE_INTERNAL).equals(map.get("FILE_SOURCE").toString())){
                //cadSolrBean.setProductCADUrl(commonFileUrl.combineFileUrlPath(map.get("FILE_ID")));
                fileurl = commonFileUrl.combineFileUrlPath(map.get("FILE_ID"));
                
            }else{
                fileurl = map.get("FILE_URL").toString();
                //cadSolrBean.setProductCADUrl(map.get("FILE_URL").toString());
            }
            int i = fileurl.lastIndexOf(".");
            cadSolrBean.setProductCADUrl(fileurl);
            if(i > -1){
                cadSolrBean.setProductCADFormat(fileurl.substring(i+1));
            }
            server.addBean(cadSolrBean);
            server.commit();
            
        }

    }
    

}
