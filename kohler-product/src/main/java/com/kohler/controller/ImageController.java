/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.entity.FileAssetEntity;
import com.kohler.service.ImagePickerService;


/**
 * Class Function Description
 * ImageController
 * @author YS
 * @Date 2014年10月27日
 */
@Controller
@RequestMapping("/image")
public class ImageController extends BasicController{
    @Autowired
    private ImagePickerService fileDao;
    
	@RequestMapping(value = "/unlimited/imagepicker")
	public String init(Model model,HttpServletRequest request,FileAssetEntity file,String elementId,String isMulti,String elementName) {
        if(file.getFileType() != null){
            request.setAttribute("fileType", file.getFileType());
        }
	    
		//查询数据 
		if(file.getFileAssetName() != null
				&& !file.getFileAssetName().equals("")){
			List<FileAssetEntity> fileLsit = fileDao.selectFileByName(file);
			request.setAttribute("fileLsit", fileLsit);
			request.setAttribute("file", file);
		}
		model.addAttribute("elementId", elementId);
		model.addAttribute("isMulti", isMulti);
		model.addAttribute("elementName", elementName);
		return "productcatalogs/imagePicker";
	}
	
	
	/**
     * search Datatype
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/unlimited/searchImgContent")
    @ResponseBody
    public Map<String,Object> searchContent(HttpServletRequest request,String fileAssetIds) {
        Map<String,Object> map = new HashMap<String, Object>();
        List<FileAssetEntity> fileLsit = fileDao.selectFileById(fileAssetIds);
        map.put("asset", fileLsit);
        return map;
    }
}
    
