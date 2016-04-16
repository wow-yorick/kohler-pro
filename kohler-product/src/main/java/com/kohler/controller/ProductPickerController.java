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

import com.kohler.entity.AggCategoryEntity;
import com.kohler.service.ProductPickerService;

/**
 * 用于处理Category的相关的请求
 *
 * @author ys
 * @Date 2014年10月16日
 */
@Controller
@RequestMapping(value = "/productpicker")
public class ProductPickerController {
	@Autowired
	private ProductPickerService productService;
	@RequestMapping(value = "/unlimited/init")
	public String init(Model model,HttpServletRequest request,AggCategoryEntity category,String elementId,String isMulti,String elementName) {
		List<Object> lsit = new ArrayList<Object>();
		//查询数据 
		if(category.getProductName() != null
				&& !category.getProductName().equals("")){
			List<Map<String, Object>> selectCategoryById = productService.selectCategoryById(category);
			request.setAttribute("category", selectCategoryById);
			request.setAttribute("serch", category);
		}
		if(category.getProductId() != null
				&& !category.getProductId().equals("")){
			List<Map<String, Object>> selectCategoryById = productService.selectCategoryById(category);
			request.setAttribute("category", selectCategoryById);
			request.setAttribute("proName", category.getCategoryName());
		}
		//填充下拉框
		List<Map<String, Object>> productName = productService.getProductName(lsit);
		request.setAttribute("productName", productName);
		model.addAttribute("elementId", elementId);
        model.addAttribute("isMulti", isMulti);
        model.addAttribute("elementName", elementName);
		return "productcatalogs/productPicker";
	}
	
}
