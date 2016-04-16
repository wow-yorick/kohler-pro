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

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.PublishLogEntity;
import com.kohler.entity.extend.PublishLogPojo;
import com.kohler.service.ContentDataService;
import com.kohler.service.PublishLogService;
import com.kohler.util.PropertiesUtils;

/**
 * 用于
 * 
 * @author wuyun
 * 
 */
@Controller
@RequestMapping(value = "/manualPublish")
public class PublishLogController extends BasicController {

	@Autowired
	private PublishLogService publishLogService;
	
	@Autowired
	private ContentDataService contentDataService;
	

	

	@RequestMapping(value = "/manualPublishList")
	public String manualPublishList(Model model,
			Pager<Map<String, Object>> pager, HttpServletRequest request,
			PublishLogPojo pojo) {
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.manualPublishList begin");
		}

		String uri = request.getRequestURI() + "?";

		String beginDate = pojo.getBeginDate();
		String beginDateBase = beginDate;
		String endDate = pojo.getEndDate();
		String endDateBase = endDate;
		if (beginDateBase == null) {
			beginDateBase = "";
		}
		if (beginDateBase.contains("*")) {
			beginDateBase = beginDateBase.replace('*', '%');
		}
		if (beginDate != null && !"".equals(beginDate)) {
			uri += "beginDate=" + beginDate + "&";
		}
		if (endDateBase == null) {
			endDateBase = "";
		}
		if (endDateBase.contains("*")) {
			endDateBase = endDateBase.replace('*', '%');
		}
		if (endDate != null && !"".equals(endDate)) {
			uri += "endDate=" + endDate + "&";
		}

		pojo.setBeginDate(beginDateBase);
		pojo.setEndDate(endDateBase);
		pager = publishLogService.getPublishLogByMap(pager, pojo);
		pager.setUrl(uri);

		int type = MasterDataCodeConstant.TYPE_PUBLISH_STATUS;// type
		int lang = 1;// language
		List<Map<String, Object>> typeList = publishLogService.getAllType(type,
				lang);
		model.addAttribute("typeList", typeList);
		model.addAttribute("pager", pager);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		if (pojo.getPublishStatus() != null) {
			model.addAttribute("status", pojo.getPublishStatus());
		}
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.manualPublishList end");
		}
		return "admin/publishLogList";
	}

	/**
	 * 获取选择数据，进入修改画面
	 * 
	 * @param Model
	 *            model
	 * @param Integer
	 *            collectionMetadataId
	 * @return
	 * @author wy Date 2014年10月29日
	 * @version
	 */
	@RequestMapping(value = "/view")
	public String editPage(Model model,Integer isEdit, Integer publishLogId) {
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.editPage begin");
		}
		// 获取主数据publishLog
		PublishLogEntity publishLog = new PublishLogEntity();
		publishLog.setIsEnable(true);
		publishLog.setPublishLogId(publishLogId);
		publishLog = publishLogService.getPublishLogById(publishLog);
		
		model.addAttribute("publishLog", publishLog);
		model.addAttribute("isEdit", isEdit);
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.editPage end");
		}
		return "admin/manualPublishlayerbox";
	}

	/**
	 * 
	 * @param publishLogId
	 * @return
	 * @author XHY
	 * Date 2014年11月28日
	 * @version
	 */
	@RequestMapping(value = "/lock")
	@ResponseBody
	public Map<String, Object> lockOpenPublish(Integer publishLogId) {
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.lockOpenPublish begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();

		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_PUBLISH_LOCK_FAILED);

		boolean isSuccess = publishLogService.lockOpenPlishById(publishLogId);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_PUBLISH_LOCK_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.lockOpenPublish end");
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param publishLogId
	 * @return
	 * @author XHY
	 * Date 2014年11月28日
	 * @version
	 */
	@RequestMapping(value = "/unlock")
	@ResponseBody
	public Map<String, Object> unlockOpenPublish(Integer publishLogId) {
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.lockOpenPublish begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_PUBLISH_UNLOCK_FAILED);
		
		boolean isSuccess = publishLogService.unlockPlishById(publishLogId);
		
		//Map<String, Object> SuffixStr = contentDataService.backupData();
		
		
		
		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_PUBLISH_UNLOCK_SUCCESS);
		}
		
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.lockOpenPublish end");
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param publishLogId
	 * @return
	 * @author XHY
	 * Date 2014年11月28日
	 * @version
	 */
	@RequestMapping(value = "/rollback")
	@ResponseBody
	public Map<String, Object> rollback(Integer publishLogId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.rollback begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_PUBLISH_UNLOCK_FAILED);
		
		boolean isSuccess = publishLogService.rollBackById(publishLogId);
		
		
		
		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_PUBLISH_UNLOCK_SUCCESS);
		}
		
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.rollback end");
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param publishLogId
	 * @return
	 * @author Dragon
	 * Date 2014年12月2日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/checkpublish")
	@ResponseBody
	public Map<String, Object> checkPublish(Integer publishLogId) {
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.checkPublish begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		int check = publishLogService.checkpublishById(publishLogId);
		
		result.put("check", check);
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.checkPublish end");
		}
		return result;
	}

	/**
	 * 发布日至 Manual Publish 修改
	 * 
	 * @param jsonEmity
	 * @author wy Date 2014年10月23日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/editSave")
	@ResponseBody
	public Map<String, Object> editSave(HttpServletRequest request) {
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.editPublishLog begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
		String publishLogId = request.getParameter("publishLogId");

		String remark = request.getParameter("remark");
		
		boolean isSuccess = publishLogService.editPublishLog(Integer.valueOf(publishLogId), remark);
		
		if(isSuccess){
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("PublishLogController.editPublishLog end");
		}
		return result;
	}

}
