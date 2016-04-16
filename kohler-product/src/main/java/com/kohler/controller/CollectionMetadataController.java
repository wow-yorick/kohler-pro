package com.kohler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionEntity;
import com.kohler.entity.CollectionMetadataEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.extend.CollectionMetadataPojo;
import com.kohler.service.CollectionMetadataService;
import com.kohler.service.CollectionService;
import com.kohler.service.LocaleService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * 系列相关的请求
 * 
 * @author shefeng
 *
 */
@Controller
@RequestMapping(value = "/collection")
public class CollectionMetadataController extends BasicController{


	@Autowired
	public CollectionMetadataService collectionMetadataService;
	
	@Autowired
	public CollectionService collectionService;
	
	@Autowired
	public LocaleService localeService;

	/**
	 * 获取列表页面数据
     * @param model
     * @param pager
     * @param request
     * @param pojo
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "/collectionList")
	public String collectionList(Model model,
			Pager<Map<String,Object>> pager, HttpServletRequest request,CollectionMetadataPojo pojo) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.collectionList begin");
        }

		String uri = request.getRequestURI() + "?";
		
		String collectionName = pojo.getCollectionName();
		String collectionNameBase = collectionName;
        if (collectionNameBase == null) {
        	collectionNameBase = "";
        }
        if (collectionNameBase.contains("*"))
        {
        	collectionNameBase = collectionNameBase.replace('*', '%');
        }
		if(collectionName != null && !"".equals(collectionName)){
            uri +="collectionName="+collectionName+"&";
        }
		pojo.setCollectionName(collectionNameBase);
		pager = collectionMetadataService.getCollectionsByMap(pager, pojo);
		pager.setUrl(uri);

		model.addAttribute("pager", pager);
		model.addAttribute("collectionName", collectionName);
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.collectionList end");
        }
		return "collection/collectionList";
	}

	/**
	 * 进入数据新增页面
     * @param Model model
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "/create")
	public String newPage(Model model) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.newPage begin");
        }
		List<LocaleEntity> languages = localeService.getLanguages();

		model.addAttribute("languages", languages);
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.newPage end");
        }
		return "collection/collectionNew";
	}
	
	/**
	 * 添加数据
     * @param HttpServletRequest request
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "/createSave")
	@ResponseBody
	public Map<String, Object> addCollection(HttpServletRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.addCollection begin");
        }
		Map<String, Object> result = new HashMap<String, Object>();
		List<CollectionEntity> collectionList = new ArrayList<CollectionEntity>();
		String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
		String seqNo = request.getParameter("seqNo");
		String collections = request.getParameter("collections");

		
		JSONArray collectionArray = JSONArray.fromObject(collections);
		
		for(int i=0;i<collectionArray.size();i++){
		     collectionList.add(JSonUtil.toObject(collectionArray.get(i).toString(), CollectionEntity.class));
		}

		collectionMetadataService.addCollectionMetadata(Integer.valueOf(seqNo),collectionList);
		
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.addCollection end");
        }
		return result;
	}
	
	/**
	 * 获取选择数据，进入修改画面
     * @param Model model
     * @param Integer collectionMetadataId
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "/edit")
	public String editPage(Model model , Integer collectionMetadataId) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.editPage begin");
        }
		//获取语言种类
		List<LocaleEntity> languages = localeService.getLanguages();
		//获取主数据collectionMetadata
		CollectionMetadataEntity collectionMetadata = new CollectionMetadataEntity();
		collectionMetadata.setIsEnable(true);
		collectionMetadata.setCollectionMetadataId(collectionMetadataId);
		collectionMetadata = collectionMetadataService.getCollectionMetadataById(collectionMetadata);
		//获取从数据collection
		CollectionEntity collection = new CollectionEntity();
		collection.setIsEnable(true);
		collection.setCollectionMetadataId(collectionMetadataId);
		List<CollectionEntity> collections = collectionService.getCollectionByCod(collection);
		model.addAttribute("languages", languages);
		model.addAttribute("collectionMetadata", collectionMetadata);
		model.addAttribute("collections", collections);
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.editPage end");
        }
		return "collection/collectionEdit";
	}
	
	/**
	 * 修改数据
     * @param model
     * @param pager
     * @param request
     * @param pojo
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "editSave")
	@ResponseBody
	public Map<String, Object> editCollection(HttpServletRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.editCollection begin");
        }
		Map<String, Object> result = new HashMap<String, Object>();
		List<CollectionEntity> collectionList = new ArrayList<CollectionEntity>();
		String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		String seqNo = request.getParameter("seqNo");
		String collectionMetadataId = request.getParameter("collectionMetadataId");
		String collections = request.getParameter("collections");

		
		JSONArray collectionArray = JSONArray.fromObject(collections);
		
		for(int i=0;i<collectionArray.size();i++){
		     collectionList.add(JSonUtil.toObject(collectionArray.get(i).toString(), CollectionEntity.class));
		}

		collectionMetadataService.editCollectionMetadata(Integer.valueOf(collectionMetadataId),Integer.valueOf(seqNo),collectionList);
		
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.editCollection end");
        }
		return result;
	}
	
	/**
	 * 获取所选数据显示其明细画面
     * @param Model model
     * @param Integer collectionMetadataId
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "/view")
	public String viewPage(Model model , Integer collectionMetadataId) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.viewPage begin");
        }
		//获取语言种类
		List<LocaleEntity> languages = localeService.getLanguages();
		//获取主数据collectionMetadata
		CollectionMetadataEntity collectionMetadata = new CollectionMetadataEntity();
		collectionMetadata.setIsEnable(true);
		collectionMetadata.setCollectionMetadataId(collectionMetadataId);
		collectionMetadata = collectionMetadataService.getCollectionMetadataById(collectionMetadata);
		//获取从数据collection
		CollectionEntity collection = new CollectionEntity();
		collection.setIsEnable(true);
		collection.setCollectionMetadataId(collectionMetadataId);
		List<CollectionEntity> collections = collectionService.getCollectionByCod(collection);
		model.addAttribute("languages", languages);
		model.addAttribute("collectionMetadata", collectionMetadata);
		model.addAttribute("collections", collections);
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.viewPage end");
        }
		return "collection/collectionView";
	}
	
	/**
	 * 删除所选数据
     * @param request
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> deleteCollection(HttpServletRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.deleteCollection begin");
        }
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		String collectionMetadataIds = request.getParameter("collectionMetadataIds");

		collectionMetadataService.deleteCollectionMetadata(collectionMetadataIds);
		
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.deleteCollection end");
        }
		return result;
	}
	
	
	/**
	 * 修改数据
     * @param String collectionName
     * @param String localeId
     * @param String collectionId
     * @return
     * @author XHY Date 2014年10月29日
     * @version
     */
	@RequestMapping(value = "/unlimited/uniquenessVerification")
	@ResponseBody
	public boolean uniquenessVerification(String collectionName,String localeId,String collectionId) {
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.uniquenessVerification begin");
        }
		boolean result;
		Integer i = collectionService.existCollectionName(collectionName, localeId, collectionId);
		if (i > 0)
		{
			result = false;
		}
		else
		{
			result = true;
		}
		if (logger.isInfoEnabled()) {
            logger.info("CollectionMetadataController.uniquenessVerification end");
        }
		return result;
	}
}
