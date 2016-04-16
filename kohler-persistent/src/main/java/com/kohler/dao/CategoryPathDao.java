package com.kohler.dao;

import com.kohler.entity.CategoryPathEntity;

/**
 * @author zhangqiqi
 */
public interface CategoryPathDao extends BaseDao<CategoryPathEntity> {
    public CategoryPathEntity selectByPrimaryKey(Integer categoryId);

    public CategoryPathEntity selectFolder(Integer productId);
}
