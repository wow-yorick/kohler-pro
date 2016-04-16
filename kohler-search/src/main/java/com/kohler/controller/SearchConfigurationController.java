/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.SearchConfigurationService;

/**
 * 用于处理phrase的相关的请求
 *
 * @author YS
 * @Date 2014年11月17日
 */
@Controller
@RequestMapping(value = "/searchConfigration")//didyoumean
public class SearchConfigurationController extends BasicController{
    @Autowired
    private SearchConfigurationService conService;
	/**
	 * 初始化页面
	 * @param pager
	 * @param model
	 * @param request
	 * @param folder
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/searchConfigrationList")//unlimited/didyoumeanList
	public String searchKeywordList() throws IOException, JAXBException  {
	    return "searchsettings/searchConfigurationList";
	}
    /**
     * 数据
     * @param request
     * @param template
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/unlimited/editSave")
    @ResponseBody
    public Map<String, Object> editSave(HttpServletRequest request,
            MasterdataEntity entity) throws IOException, JAXBException  {
        int count = conService.updateConfiuraction(entity);
        Map<String, Object> map = new HashMap<String, Object>();
        if(count > 0){
            map.put("msg","Successful modification！");
        }else{
            map.put("msg","lost！");
        }
        return map;
    }
}
