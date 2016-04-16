/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kohler.entity.AggSKUEntity;
import com.kohler.service.SKUPickerService;

/**
 * 用于处理Category的相关的请求
 * @author ys
 * @Date 2014年10月16日
 */
@Controller
@RequestMapping(value = "/skupicker")
public class SKUPickerController {
	@Autowired
	private SKUPickerService skuService;
	/**
	 * 初始化SKUPicker页面
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/unlimited/skuPickerList")
	public String init(Model model,HttpServletRequest request,AggSKUEntity sku,String elementId,String isMulti,String elementName) {
		List<Object> lsit = new ArrayList<Object>();
		
		List<Map<String, Object>> attr = skuService.getSKUAttribute(lsit);
		request.setAttribute("attr", attr);
		//查询数据 
		if(sku.getProductName() != null
				&& !sku.getProductName().equals("")){
			List<Map<String, Object>> selectSKU = skuService.selectSKUById(sku);
			request.setAttribute("sku", selectSKU);
			request.setAttribute("one", sku);
		}
		if(sku.getCategoryName() != null
				&& !sku.getCategoryName().equals("")){
			List<Map<String, Object>> selectSKU = skuService.selectSKUById(sku);
			request.setAttribute("sku", selectSKU);
			request.setAttribute("one", sku);
		}
		//填充下拉框
		List<Map<String, Object>> productName = skuService.getProductName(lsit);
		request.setAttribute("productName", productName);
		model.addAttribute("elementId", elementId);
        model.addAttribute("isMulti", isMulti);
        model.addAttribute("elementName", elementName);
		return "productcatalogs/skuPicker";
	}
	
}
