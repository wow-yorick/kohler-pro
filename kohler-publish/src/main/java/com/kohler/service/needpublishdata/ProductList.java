/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.needpublishdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.CategoryMetadataDao;
import com.kohler.dao.ProductDao;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.service.NeedPublishDataService;

/**
 * 获取产品列表
 *
 * @author Administrator
 * @Date 2014年11月17日
 */
@Component
public class ProductList implements NeedPublishDataService {
    
    @Autowired
    private CategoryMetadataDao categoryMetadataDao;

    @Autowired
    private ProductDao productDao;
    
    private Integer failure;


    /**
     * {@inheritDoc}
     */
    public List<Integer> getPrimaryKeyList(Integer categoryMetadataId, ConfPrepareData confPrepareData) {
        List<Object> sqlParams = new ArrayList<Object>();

        List<CategoryMetadataEntity> categoryMetadata =  getCurrentCategoryAndChild(categoryMetadataId);
        if(categoryMetadata != null && categoryMetadata.size() > 0) {
            for(CategoryMetadataEntity _catta : categoryMetadata) {
                sqlParams.add(_catta.getCategoryMetadataId());
            }
        }
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.PRODUCT_METADATA_ID FROM PRODUCT P ");
        sql.append(" LEFT JOIN PRODUCT_METADATA M ON P.PRODUCT_METADATA_ID = M.PRODUCT_METADATA_ID ");
        sql.append(" WHERE M.IS_ENABLE = P.IS_ENABLE AND P.IS_ENABLE = 1 AND  M.CATEGORY_METADATA_ID IN (");
        for(int i=0; i < sqlParams.size(); i++) {
            sql.append("?");
            if(i != sqlParams.size()-1) {
                sql.append(",");
            }
        }
        sql.substring(0, sql.length()-1);
        sql.append(")");
        
        if(confPrepareData.getLang() != null) {
            sql.append(" AND P.LANG =");
            sql.append(confPrepareData.getLang());
        }
        List<Map<String, Object>> productList = productDao.selectByConditionWithMap(sql.toString(),sqlParams);
        //获取产品ID
        List<Integer> retList = new ArrayList<Integer>();
        for(Map<String,Object> _product : productList) {
            Object productMID = _product.get("PRODUCT_METADATA_ID");
            if(productMID != null) {
                Integer productMetadataId = Integer.valueOf(productMID.toString());
                retList.add(productMetadataId);
            }
        }
        return retList;
    }
    
    
    /**
     * 由目录id获取当前目录信息及子目录信息
     * @param categoryMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月27日
     * @version
     */
    private List<CategoryMetadataEntity> getCurrentCategoryAndChild(Integer categoryMetadataId) {
        
        List<CategoryMetadataEntity> tree = new ArrayList<CategoryMetadataEntity>();//category需发布的列表容器
        CategoryMetadataEntity categoryMetadataEntity = categoryMetadataDao.selectById(categoryMetadataId);
        tree.add(categoryMetadataEntity);//当前category
        //子category
        if(categoryMetadataEntity != null && categoryMetadataEntity.getLevel() != 3) {
            recursionGetCategoryMetadataByParentId(categoryMetadataEntity.getCategoryMetadataId(), tree);
        }
        return tree;
    }
    
    /**
     * 根据parent_id 查询目录 结果保存在传入的tree参数中
     * @param categoryMetadataParentId
     * @return
     * @author Administrator
     * Date 2014年10月27日
     * @version
     */
    private void recursionGetCategoryMetadataByParentId(Integer parentId, List<CategoryMetadataEntity> tree) {
        //查询子集
        CategoryMetadataEntity categoryMetadataEntity = new CategoryMetadataEntity();
        categoryMetadataEntity.setIsEnable(true);
        categoryMetadataEntity.setParentId(parentId);
        List<CategoryMetadataEntity> _listCategory = categoryMetadataDao.selectByCondition(categoryMetadataEntity);
        
        //添加入结果集
        tree.addAll(_listCategory);
        
        //如果还有子集进行递归
        if(_listCategory != null && _listCategory.size() > 0) {
            for(CategoryMetadataEntity _ctm : _listCategory) {
                try{
                    if(_ctm != null && _ctm.getLevel() != null && _ctm.getLevel().intValue() != 3) { //三級目录就不用循环了
                        recursionGetCategoryMetadataByParentId(_ctm.getCategoryMetadataId(),tree);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    ++failure;
                }
            }
        }
    }
    
    /**
     * 获取失败次数
     * @return
     * @author Administrator
     * Date 2014年11月17日
     * @version
     */
    public Integer getFailure() {
        return failure;
    }
    

}
