package com.kohler.service;

import java.util.List;
import java.util.Map;
import com.kohler.entity.AggSKUEntity;

/**
 * Page Interface
 *
 * @author ys
 * @Date 2014年10月17日
 */
public interface SKUPickerService {
	//查询并显示
	public List<Map<String, Object>> selectSKUById(AggSKUEntity category);
	//查询attribute值
	public List<Map<String, Object>> getSKUAttribute(List<Object> lsit);
	//下拉框填充
	public List<Map<String, Object>> getProductName(List<Object> lsit);
}
