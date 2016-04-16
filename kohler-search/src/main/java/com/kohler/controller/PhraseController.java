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
import com.kohler.entity.SearchPhraseEntity;
import com.kohler.service.PhraseService;

/**
 * 用于处理phrase的相关的请求
 *
 * @author ys
 * @Date 2014年11月12日
 */
@Controller
@RequestMapping(value = "/searchPhrase")
public class PhraseController extends BasicController{
    @Autowired
    private PhraseService phraseService;
	/**
	 * 初始化页面
	 * @param pager
	 * @param model
	 * @param request
	 * @param folder
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/searchPhraseList")
	public String searchSpellingCheckList(Pager<SearchPhraseEntity> pager,Model model,HttpServletRequest request,
	        SearchPhraseEntity phrase) throws IOException, JAXBException  {
		String uri = request.getRequestURI() + "?";
		if(phrase.getSettingValue() != null && !phrase.getSettingValue().equals("")){
		    uri += "value=" + phrase.getSettingValue() + "&";
		    request.setAttribute("v", phrase.getSettingValue());
		}
//		model.addAttribute("folderId", folderId);
		pager.setUrl(uri);
		//table填充
		Pager<SearchPhraseEntity> phraseList = phraseService.getPharseList(pager, phrase);
		request.setAttribute("list", phraseList);
		return "searchsettings/phraseList";
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
        return "searchsettings/phraseNew";
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
    public Map<String, Object> createSave(HttpServletRequest request,SearchPhraseEntity phrase)
            throws IOException, JAXBException  {
        Map<String, Object> map = new HashMap<String, Object>();
        int createphrase = phraseService.createPharse(phrase);
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
        SearchPhraseEntity phrase = new SearchPhraseEntity();
        for (int i = 0; i < data.length; i++) {
            phrase.setSearchPhraseId(Integer.parseInt(data[i]));
            phraseService.deletePharse(phrase);
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
    public String edit(HttpServletRequest request,int phraseId,String isEdit) throws IOException, JAXBException  {
        List<Object> params = new ArrayList<Object>();
        params.add(phraseId);
        List<SearchPhraseEntity> selectOne = phraseService.selectOne(params);
        if(isEdit.equals("1")){
            request.setAttribute("isEdit",1);
        }
        request.setAttribute("spelling", selectOne.get(0));
        return "searchsettings/phraseEdit";
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
            SearchPhraseEntity phrase) throws IOException, JAXBException  {
        phrase.setIsEnable(true);
        Map<String, Object> result = new HashMap<String, Object>();
        int updateSpeiing = phraseService.updatePharse(phrase);
        if(updateSpeiing > 0){
            result.put("msg","Successful modification！");
        }
        if(updateSpeiing == 0){
            result.put("msg", "cf");
        }
        return result;
    }
}
