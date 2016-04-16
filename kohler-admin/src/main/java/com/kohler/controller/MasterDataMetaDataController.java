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

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.AggMasterdataMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.MasterdataMetadataEntity;
import com.kohler.service.MasterDataMetaDataService;
import com.kohler.util.JSonUtil;

@Controller
@RequestMapping(value = "/masterdata")
public class MasterDataMetaDataController {
	@Autowired
	private MasterDataMetaDataService masterDataService;

	/**
	 * 初始化页面
	 * 
	 * @param request
	 * @param pager
	 * @param template
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/masterdataList")
	public String init(HttpServletRequest request,
			Pager<Map<String, Object>> pager,
			AggMasterdataMetadataEntity masterData) throws IOException,
			JAXBException {
		String uri = request.getRequestURI() + "?";
		List<Object> typeLists = new ArrayList<Object>();
		typeLists.add(1);
		List<Map<String, Object>> allType = masterDataService
				.getAllType(typeLists);
		request.setAttribute("typeList", allType);
		if (masterData.getMasterdataName() != null) {
			uri += "masterdataName=" + masterData.getMasterdataName() + "&";
		}
		if (masterData.getMasterdataTypeId() != null) {
			uri += "masterdataTypeId=" + masterData.getMasterdataTypeId() + "&";
		}
		pager.setUrl(uri);
		Pager<Map<String, Object>> allMasterdata = masterDataService
				.getAllMasterdata(pager, masterData);
		request.setAttribute("masterData", allMasterdata);
		if (masterData != null)
			request.setAttribute("list", masterData);
		return "admin/masterDataList";
	}

	/**
	 * 初始化新增页面
	 * 
	 * @param request
	 * @param masterData
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/create")
	public String createMasterDataPage(HttpServletRequest request,
			MasterdataMetadataEntity masterData) throws IOException,
			JAXBException {
		// List<Object> typeLists = new ArrayList<Object>();
		List<Object> typeLists = new ArrayList<Object>();
		typeLists.add(1);
		// 填充下拉框
		List<Map<String, Object>> allType = masterDataService
				.getAllType(typeLists);
		request.setAttribute("typeList", allType);
		// 初始化页面id，创建人&时间等数据
		MasterdataMetadataEntity MasterdataMateDetaId = masterDataService
				.createMasterdataMateDeta(masterData);
		request.setAttribute("masterdataMateDeta", MasterdataMateDetaId);
		// 语言列表初始化
		List<Map<String, Object>> allLocale = masterDataService.getAllLocale();
		request.setAttribute("allLocale", allLocale);
		return "admin/masterDataNew";
	}

	/**
	 * 新增数据
	 * 
	 * @param request
	 * @param masterData
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/createSave")
	@ResponseBody
	public Map<String, Object> createMasterDataMeta(HttpServletRequest request,
			MasterdataMetadataEntity masterData) throws IOException,
			JAXBException {
		Map<String, Object> map = new HashMap<String, Object>();
		String masterdataName = request.getParameter("masterdataName");
		List<MasterdataEntity> collectionList = new ArrayList<MasterdataEntity>();

		JSONArray collectionArray = JSONArray.fromObject(masterdataName);

		for (int i = 0; i < collectionArray.size(); i++) {
			collectionList.add(JSonUtil.toObject(collectionArray.get(i)
					.toString(), MasterdataEntity.class));
		}
		String[] mas = new String[2];
		mas[0] = collectionList.get(0).getMasterdataName();
		mas[1] = collectionList.get(1).getMasterdataName();
		int count = masterDataService.updateMasterdataMateDeta(masterData, mas);
		if (count > 0) {
			map.put("msg", "Create success!");
		} else {
			map.put("msg", "Creation failed!");
		}
		return map;
	}

	/**
	 * 根据id查找数据
	 * 
	 * @param request
	 * @param masterDataMetadataId
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/edit")
	public String selectOne(HttpServletRequest request, int masterDataMetadataId,String isEdit)
			throws IOException, JAXBException {
	    // 根据id查询数据
	    List<Object> MasterData = new ArrayList<Object>();
	    MasterData.add(masterDataMetadataId);
	    List<Map<String, Object>> one = masterDataService.selectOne(MasterData);
	    request.setAttribute("one", one);
	    // 获取下拉框数据
	    List<Object> typeLists = new ArrayList<Object>();
	    typeLists.add(1);
	    if(isEdit.equals("1")){
	        List<Map<String, Object>> allType = masterDataService
	                .getAllType(typeLists);
	        request.setAttribute("typeList", allType);
	        request.setAttribute("isEdit", 1);
	        return "admin/masterDataEdit";
	    }
		List<Map<String, Object>> allType = masterDataService
				.getAllType(typeLists);
		request.setAttribute("typeList", allType);
		return "admin/masterDataEdit";
	}

	/**
	 * 修改数据
	 * 
	 * @param request
	 * @param masterDataMetadata
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/editSave")
	@ResponseBody
	public Map<String, Object> updateMasterMetaData(HttpServletRequest request,
			MasterdataMetadataEntity masterDataMetadata) throws IOException,
			JAXBException {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取并处理masterdata数据
		String masterdataName = request.getParameter("masterdataName");
		List<MasterdataEntity> collectionList = new ArrayList<MasterdataEntity>();
		JSONArray collectionArray = JSONArray.fromObject(masterdataName);

		for (int i = 0; i < collectionArray.size(); i++) {
			collectionList.add(JSonUtil.toObject(collectionArray.get(i)
					.toString(), MasterdataEntity.class));
		}
		int count = masterDataService.updateMasterdata(masterDataMetadata,
				collectionList);
		if (count > 0) {
			map.put("msg", "Successful modification!");
		} else {
			map.put("msg", "Modify the failure!");
		}
		return map;
	}

	// 删除数据
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> deleteMasterdataMetadata(String[] data)
			throws IOException, JAXBException {
		Map<String, Object> result = new HashMap<String, Object>();
		MasterdataMetadataEntity masterdataMetadata = new MasterdataMetadataEntity();
		for (int i = 0; i < data.length; i++) {
			masterdataMetadata.setMasterdataMetadataId(Integer
					.parseInt(data[i]));
			masterDataService.deleteMasterdataMateDeta(masterdataMetadata);
		}
		result.put("msg", "Successfully deleted!");
		return result;
	}
}
