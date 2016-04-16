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

import com.kohler.dao.ProductPDFDao;
import com.kohler.dao.utils.CommonFileUrl;
import com.kohler.dao.utils.PropertyUtil;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.service.PDFSearchSolrService;
import com.kohler.solr.bean.PDFSolrBean;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月30日
 */
@Service
public class PDFSearchSolrServiceImpl implements PDFSearchSolrService {

    @Autowired
    private ProductPDFDao productPDFDao;
    
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
        String sql ="select pc.LANG,pc.PRODUCT_PDF_METADATA_ID,pc.PDF_TYPE,m.MASTERDATA_NAME,pc.PRODUCT_PDF_NAME,pc.FILE_SOURCE,pc.FILE_URL,pc.FILE_ID, "+
        " c.CATEGORY_METADATA_ID,c.CATEGORY_NAME,c.LEVEL,pcm.PRODUCT_METADATA_ID,pm.PRODUCT_CODE,pm.PRODUCT_NAME "+
        " from PRODUCT_PDF pc "+
        " left join PRODUCT_PDF_METADATA pcm on pc.PRODUCT_PDF_METADATA_ID = pcm.PRODUCT_PDF_METADATA_ID "+
        " left join MASTERDATA m on pc.PDF_TYPE = m.MASTERDATA_METADATA_ID "+
        " left join VW_PRODUCT pm on pcm.PRODUCT_METADATA_ID = pm.PRODUCT_METADATA_ID "+
        " left join VW_CATEGORY c on pm.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID "+
        " where c.LANG = pc.LANG and m.LANG = pc.LANG and pm.LANG = pc.LANG and pc.IS_ENABLE = 1 and pm.IS_ENABLE = 1 "+ 
        " and c.IS_ENABLE = 1 and pcm.IS_ENABLE = 1 and pc.LANG = ?";
        //Integer lang = 1;
        List<Map<String, Object>> pdflist = productPDFDao.getMapList(sql,lang);
        for (Map<String, Object> map : pdflist) {
            PDFSolrBean pdfSolrBean = new PDFSolrBean();
            String productPDFMetadataId = map.get("PRODUCT_PDF_METADATA_ID").toString();
            pdfSolrBean.setId("PDF"+productPDFMetadataId);
            pdfSolrBean.setProductPDFMetadataId(productPDFMetadataId);
            pdfSolrBean.setProductMetadataId(map.get("PRODUCT_METADATA_ID").toString());
            pdfSolrBean.setProductPDFName(map.get("PRODUCT_PDF_NAME").toString());
            pdfSolrBean.setCategoryMetadataId(map.get("CATEGORY_METADATA_ID").toString());
            pdfSolrBean.setCategoryName(map.get("CATEGORY_NAME").toString());
            pdfSolrBean.setPdfType(map.get("PDF_TYPE").toString());
            pdfSolrBean.setPdfTypeName(map.get("MASTERDATA_NAME").toString());
            pdfSolrBean.setProductName(map.get("PRODUCT_NAME").toString());
            pdfSolrBean.setProductCode(map.get("PRODUCT_CODE").toString());
            if(map.get("LEVEL")!=null&&"3".equals(map.get("LEVEL").toString())){
                pdfSolrBean.setSubCategoryMetadataId(map.get("CATEGORY_METADATA_ID").toString());
                pdfSolrBean.setSubCategoryName(map.get("CATEGORY_NAME").toString());
            }
            String fileurl = "";
            if(map.get("FILE_SOURCE")!=null&&String.valueOf(MasterDataCodeConstant.FILE_SOURCE_INTERNAL).equals(map.get("FILE_SOURCE").toString())){
                fileurl = commonFileUrl.combineFileUrlPath(map.get("FILE_ID"));
                
            }else{
                fileurl = map.get("FILE_URL").toString();
            }
            pdfSolrBean.setProductPDFUrl(fileurl);
            server.addBean(pdfSolrBean);
            server.commit();
            
        }

    }
    

}
