package com.kohler.service;

import java.util.List;
import java.util.Map;
import com.kohler.entity.AggCategoryEntity;

/**
 * Page Interface
 *
 * @author ys
 * @Date 2014年10月16日
 */
public interface ProductPickerService {
	//查询并显示
		public List<Map<String, Object>> selectCategoryById(AggCategoryEntity category);
		//下拉框填充
		public List<Map<String, Object>> getProductName(List<Object> lsit);
}
