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
import com.kohler.entity.SearchStopWordEntity;
import com.kohler.service.StopWordService;

/**
 * 用于处理phrase的相关的请求
 *
 * @author ys
 * @Date 2014年11月13日
 */
@Controller
@RequestMapping(value = "/searchStopword")
public class StopWordController extends BasicController{
    @Autowired
    private StopWordService stopService;
	/**
	 * 初始化页面
	 * @param pager
	 * @param model
	 * @param request
	 * @param folder
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/searchStopwordList")
	public String searchSpellingCheckList(Pager<SearchStopWordEntity> pager,Model model,HttpServletRequest request,
	        SearchStopWordEntity stopWord) throws IOException, JAXBException  {
		String uri = request.getRequestURI() + "?";
		if(stopWord.getSettingValue() != null && !stopWord.getSettingValue().equals("")){
		    uri += "value=" + stopWord.getSettingValue() + "&";
		    request.setAttribute("v", stopWord.getSettingValue());
		}
		pager.setUrl(uri);
		//table填充
		Pager<SearchStopWordEntity> phraseList = stopService.getStopWordList(pager, stopWord);
		request.setAttribute("list", phraseList);
		return "searchsettings/stopWordList";
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
        return "searchsettings/stopWordNew";
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
    public Map<String, Object> createSave(HttpServletRequest request,SearchStopWordEntity stopWord)
            throws IOException, JAXBException  {
        Map<String, Object> map = new HashMap<String, Object>();
        int createphrase = stopService.createStopWord(stopWord);
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
        SearchStopWordEntity stopWord = new SearchStopWordEntity();
        for (int i = 0; i < data.length; i++) {
            stopWord.setSearchStopWordId(Integer.parseInt(data[i]));
            stopService.deleteStopWord(stopWord);
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
    public String edit(HttpServletRequest request,int searchStopWordId,String isEdit) throws IOException, JAXBException  {
        List<Object> params = new ArrayList<Object>();
        params.add(searchStopWordId);
        List<SearchStopWordEntity> selectOne = stopService.selectOne(params);
        if(isEdit.equals("1")){
            request.setAttribute("isEdit",1);
        }
        request.setAttribute("spelling", selectOne.get(0));
        return "searchsettings/stopWordEdit";
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
            SearchStopWordEntity stopWord) throws IOException, JAXBException  {
        stopWord.setIsEnable(true);
        Map<String, Object> result = new HashMap<String, Object>();
        int updateSpeiing = stopService.updateStopWord(stopWord);
        if(updateSpeiing > 0){
            result.put("msg","Successful modification！");
        }
        if(updateSpeiing == 0){
            result.put("msg", "cf");
        }
        return result;
    }
}
