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
import com.kohler.entity.SearchKeywordRedirectionEntity;
import com.kohler.service.KeywordService;

/**
 * 用于处理phrase的相关的请求
 *
 * @author ys
 * @Date 2014年11月12日
 */
@Controller
@RequestMapping(value = "/searchKeywordRedirection")
public class KeywordController extends BasicController{
    @Autowired
    private KeywordService keyService;
	/**
	 * 初始化页面
	 * @param pager
	 * @param model
	 * @param request
	 * @param folder
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/searchKeywordRedirectionList")
	public String searchKeywordList(Pager<SearchKeywordRedirectionEntity> pager,Model model,HttpServletRequest request,
	        SearchKeywordRedirectionEntity keyword) throws IOException, JAXBException  {
		String uri = request.getRequestURI() + "?";
		if(keyword.getKeyword() != null && !keyword.getKeyword().equals("")){
		    uri += "value=" + keyword.getKeyword() + "&";
		    request.setAttribute("v", keyword.getKeyword());
		}
//		model.addAttribute("folderId", folderId);
		pager.setUrl(uri);
		//table填充
		Pager<SearchKeywordRedirectionEntity> phraseList = keyService.getKeywordList(pager, keyword);
		request.setAttribute("list", phraseList);
		return "searchsettings/keywordList";
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
        return "searchsettings/keywordNew";
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
    public Map<String, Object> createSave(HttpServletRequest request,SearchKeywordRedirectionEntity keyword)
            throws IOException, JAXBException  {
        Map<String, Object> map = new HashMap<String, Object>();
        int createphrase = keyService.createKeyword(keyword);
        if(createphrase > 0){
            map.put("msg", "Saved successfully!");
        }else{
            map.put("msg", "Save failed!");
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
        SearchKeywordRedirectionEntity keyword = new SearchKeywordRedirectionEntity();
        for (int i = 0; i < data.length; i++) {
            keyword.setSearchKeywordRedirectionId(Integer.parseInt(data[i]));
            keyService.deleteKeyword(keyword);
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
    public String edit(HttpServletRequest request,int searchKeywordRedirectionId,String isEdit) throws IOException, JAXBException  {
        List<Object> params = new ArrayList<Object>();
        params.add(searchKeywordRedirectionId);
        List<SearchKeywordRedirectionEntity> selectOne = keyService.selectOne(params);
        if(isEdit.equals("1")){
            request.setAttribute("isEdit",1);
        }
        request.setAttribute("spelling", selectOne.get(0));
        return "searchsettings/keywordEdit";
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
            SearchKeywordRedirectionEntity keyword) throws IOException, JAXBException  {
        keyword.setIsEnable(true);
        Map<String, Object> result = new HashMap<String, Object>();
        int updateSpeiing = keyService.updateKeyword(keyword);
        if(updateSpeiing > 0){
            result.put("msg","Successful modification！");
        }
        return result;
    }
}
