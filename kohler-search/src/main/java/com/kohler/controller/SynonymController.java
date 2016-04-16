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
import com.kohler.entity.SearchSynonymEntity;
import com.kohler.service.SynonymService;

/**
 * 用于处理phrase的相关的请求
 *
 * @author ys
 * @Date 2014年11月13日
 */
@Controller
@RequestMapping(value = "/searchSynonym")
public class SynonymController extends BasicController{
    @Autowired
    private SynonymService synonymService;
	/**
	 * 初始化页面
	 * @param pager
	 * @param model
	 * @param request
	 * @param folder
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/searchSynonymList")
	public String searchSpellingCheckList(Pager<SearchSynonymEntity> pager,Model model,HttpServletRequest request,
	        SearchSynonymEntity synonym) throws IOException, JAXBException  {
		String uri = request.getRequestURI() + "?";
		if(synonym.getSettingValue() != null && !synonym.getSettingValue().equals("")){
		    uri += "value=" + synonym.getSettingValue() + "&";
		    request.setAttribute("v", synonym.getSettingValue());
		}
		pager.setUrl(uri);
		//table填充
		Pager<SearchSynonymEntity> phraseList = synonymService.getSynonymList(pager, synonym);
		request.setAttribute("list", phraseList);
		return "searchsettings/synonymList";
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
    public String create() throws IOException, JAXBException  {
        return "searchsettings/synonymNew";
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
    public Map<String, Object> createSave(HttpServletRequest request,SearchSynonymEntity synonym)
            throws IOException, JAXBException  {
        Map<String, Object> map = new HashMap<String, Object>();
        int createphrase = synonymService.createSynonym(synonym);
        if(createphrase > 0){
            map.put("msg", "Saved successfully!");
        }else{
            map.put("msg", "Save failed!");
        }
        if(createphrase == 0){
            map.put("msg", "cf");
        }
        return map;
    }
    /**
     * 删除
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
        SearchSynonymEntity synonym = new SearchSynonymEntity();
        for (int i = 0; i < data.length; i++) {
            synonym.setSearchSynonymId(Integer.parseInt(data[i]));
            synonymService.deleteSynonym(synonym);
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
    public String edit(HttpServletRequest request,int searchSynonymId,String isEdit) throws IOException, JAXBException  {
        List<Object> params = new ArrayList<Object>();
        params.add(searchSynonymId);
        List<SearchSynonymEntity> selectOne = synonymService.selectOne(params);
        if(isEdit.equals("1")){
            request.setAttribute("isEdit",1);
        }
        request.setAttribute("spelling", selectOne.get(0));
        return "searchsettings/synonymEdit";
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
            SearchSynonymEntity synonym) throws IOException, JAXBException  {
        synonym.setIsEnable(true);
        Map<String, Object> result = new HashMap<String, Object>();
        int updateSpeiing = synonymService.updateSynonym(synonym);
        if(updateSpeiing > 0){
            result.put("msg","Successful modification！");
        }
        if(updateSpeiing == 0){
            result.put("msg", "cf");
        }
        return result;
    }
}
