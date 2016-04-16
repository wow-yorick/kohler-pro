/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSpellingCheckEntity;
import com.kohler.service.SpellingCheckService;

/**
 * 用于处理folder的相关的请求
 *
 * @author ys
 * @Date 2014年11月10日
 */
@Controller
@RequestMapping(value = "/searchSpellingCheck")
public class SpellingCheckController extends BasicController{
    @Autowired
    private SpellingCheckService spellingService;
	/**
	 * 初始化页面
	 * @param pager
	 * @param model
	 * @param request
	 * @param folder
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/searchSpellingCheckList")
	public String searchSpellingCheckList(Pager<SearchSpellingCheckEntity> pager,Model model,HttpServletRequest request,
	        SearchSpellingCheckEntity spelling) throws IOException, JAXBException  {
		String uri = request.getRequestURI() + "?";
		if(spelling.getSettingValue() != null && !spelling.getSettingValue().equals("")){
		    uri += "value=" + spelling.getSettingValue() + "&";
		    request.setAttribute("v", spelling.getSettingValue());
		}
//		model.addAttribute("folderId", folderId);
		pager.setUrl(uri);
		//table填充
		Pager<SearchSpellingCheckEntity> spellingList = spellingService.getSpellingCheckList(pager, spelling);
		request.setAttribute("list", spellingList);
		return "searchsettings/searchCheckList";
	}
	/**
     * 跳转new页面
     * @param request
     * @param template
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/create")
    public String create(HttpServletRequest request,SearchSpellingCheckEntity spelling)
            throws IOException, JAXBException  {
        return "searchsettings/searchCheckNew";
    }
    /**
     * 数据
     * @param request
     * @param template
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/createSave")
    @ResponseBody
    public Map<String, Object> createSave(HttpServletRequest request,SearchSpellingCheckEntity spelling)
            throws IOException, JAXBException  {
        Map<String, Object> map = new HashMap<String, Object>();
        int createSpeiing = spellingService.createSpeiing(spelling);
        if(createSpeiing > 0){
            map.put("msg", "Saved successfully!");
        }else{
            map.put("msg", "Save failed!");
        }
        if(createSpeiing == 0){
            map.put("msg", "cf");
        }
        return map;
    }
    /**
     * 数据
     * @param request
     * @param template
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(String[] data) throws IOException, JAXBException  {
        Map<String, Object> result = new HashMap<String, Object>();
        SearchSpellingCheckEntity spelling = new SearchSpellingCheckEntity();
        for (int i = 0; i < data.length; i++) {
            spelling.setSearchSpellingCheckId(Integer.parseInt(data[i]));
            spellingService.deleteSpeiing(spelling);
        }
        result.put("msg","Successfully deleted！");
        return result;
    }
    /**
     * 跳转update页面
     * @param request
     * @param template
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request,int spellingId,String isEdit) throws IOException, JAXBException  {
        List<Object> params = new ArrayList<Object>();
        params.add(spellingId);
        List<SearchSpellingCheckEntity> selectOne = spellingService.selectOne(params);
        if(isEdit.equals("1")){
            request.setAttribute("isEdit",1);
        }
        request.setAttribute("spelling", selectOne.get(0));
        return "searchsettings/searchCheckEdit";
    }
    /**
     * 数据
     * @param request
     * @param template
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/editSave")
    @ResponseBody
    public Map<String, Object> editSave(HttpServletRequest request,
            SearchSpellingCheckEntity spelling) throws IOException, JAXBException  {
        spelling.setIsEnable(true);
        Map<String, Object> result = new HashMap<String, Object>();
        int updateSpeiing = spellingService.updateSpeiing(spelling);
        if(updateSpeiing > 0){
            result.put("msg","Successful modification！");
        }
        if(updateSpeiing == 0){
            result.put("msg", "cf");
        }
        return result;
    }
}
