/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.UserSession;
import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.CategoryAccessoryMetadataEntity;
import com.kohler.entity.CategoryBannerUnitEntity;
import com.kohler.entity.CategorySKUAttrEntity;
import com.kohler.entity.CategorySKUAttrMetadataEntity;
import com.kohler.entity.CategorySKUAttrValuesEntity;
import com.kohler.entity.CategorySKUAttrValuesMetadataEntity;
import com.kohler.entity.CategorySearchKeywordEntity;
import com.kohler.entity.CategorySearchKeywordMetadataEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CategoryAccessoryMetadataPojo;
import com.kohler.entity.extend.CategoryComAttrMetadataPojo;
import com.kohler.entity.extend.CategoryComAttrPojo;
import com.kohler.entity.extend.CategoryMetadataPojo;
import com.kohler.entity.extend.CategoryPojo;
import com.kohler.entity.extend.CategorySKUAttrMetadataPojo;
import com.kohler.entity.extend.CategorySKUAttrValuesPojo;
import com.kohler.entity.extend.CategorySearchKeywordMetadataPojo;
import com.kohler.service.CategoryService;
import com.kohler.service.LocaleService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * 用于处理Category的相关的请求
 * 
 * @author XHY
 * @Date 2014年10月9日
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BasicController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserSession userSession;

	@Autowired
	private LocaleService localeService;

	@Value("#{settings['HeroAreas.dataType.key']}")
	private String heroAreasKey;
	@Value("#{settings['banner.dataType.key']}")
	private String brannerKey;

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryMetadataId
	 * @return
	 * @author Dragon Date 2014年11月14日
	 * @version
	 */
	@RequestMapping(value = "/createCategory")
	public String newCategoryPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newCategoryPage begin");
		}
		SysUserEntity sysUser = userSession.getSysUser();

		CategoryMetadataPojo pareCate = categoryService
				.findById(categoryMetadataId);

		// first insert a data,this.is_enable = 0
		Integer metadataId = categoryService
				.insertCategoryMetadata(pareCate);
		List<TemplateEntity> templates = categoryService
				.getTemplateListByType(MasterDataCodeConstant.TEMPLATE_TYPE_CATEGORY);
		List<LocaleEntity> languages = localeService.getLanguages();

		model.addAttribute("templates", templates);
		model.addAttribute("heroAreasKey", heroAreasKey);
		model.addAttribute("languages", languages);
		model.addAttribute("pareCate", pareCate);
		model.addAttribute("metadataId", metadataId);
		model.addAttribute("category", "category");
		model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newCategoryPage end");
		}
		return "productcatalogs/categoryNew";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月5日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/newComAttrPage", method = RequestMethod.GET)
	public String newComAttrPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newComAttrPage begin");
		}
		SysUserEntity sysUser = userSession.getSysUser();

		List<LocaleEntity> languages = localeService.getLanguages();

		int type = MasterDataCodeConstant.TYPE_COMMON_ATTRIBUTE_INPUT_TYPE;// 所需数据常量
		int lang = 1;// language
		List<Map<String, Object>> typeList = categoryService.getAllType(type,
				lang);
		model.addAttribute("typeList", typeList);

		int fileType = MasterDataCodeConstant.TYPE_FILE_SOURCE;// 所需数据常量
		int flieLang = 1;// language
		List<Map<String, Object>> typleFile = categoryService.getAllType(
				fileType, flieLang);
		model.addAttribute("typleFile", typleFile);

		List<String> dropDownType = categoryService.getDropDownType();

		int masterDataId = MasterDataCodeConstant.FILE_ASSET_IMAGE;
		model.addAttribute("masterDataId", masterDataId);
		model.addAttribute("dropDownType", dropDownType);
		model.addAttribute("languages", languages);
		model.addAttribute("categoryMetadataId", categoryMetadataId);
		model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newComAttrPage end");
		}
		return "productcatalogs/commonAttributeNew";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryComAttrMetadataId
	 * @return
	 * @author XHY Date 2014年11月5日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/editComAttrPage", method = RequestMethod.GET)
	public String editComAttrPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryComAttrMetadataId") Integer categoryComAttrMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editComAttrPage begin");
		}
		CategoryComAttrMetadataPojo categoryComAttrMetadataPojo = categoryService
				.getCateComAttrById(categoryComAttrMetadataId);
		List<LocaleEntity> languages = localeService.getLanguages();

		List<CategoryComAttrPojo> categoryComAttrPojos = categoryService
				.getComAttrByMId(categoryComAttrMetadataId, languages);

		int type = MasterDataCodeConstant.TYPE_COMMON_ATTRIBUTE_INPUT_TYPE;// 所需数据常量
		int lang = 1;// language
		List<Map<String, Object>> typeList = categoryService.getAllType(type,
				lang);
		model.addAttribute("typeList", typeList);

		int fileType = MasterDataCodeConstant.TYPE_FILE_SOURCE;// 所需数据常量
		int flieLang = 1;// language
		List<Map<String, Object>> typleFile = categoryService.getAllType(
				fileType, flieLang);
		model.addAttribute("typleFile", typleFile);

		List<String> dropDownType = categoryService.getDropDownType();

		int masterDataId = MasterDataCodeConstant.FILE_ASSET_IMAGE;
		model.addAttribute("masterDataId", masterDataId);
		model.addAttribute("dropDownType", dropDownType);
		model.addAttribute("languages", languages);
		model.addAttribute("categoryComAttrMetadataPojo",
				categoryComAttrMetadataPojo);

		model.addAttribute("categoryComAttrPojos", categoryComAttrPojos);

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editComAttrPage end");
		}
		return "productcatalogs/commonAttributeEdit";
	}

	/**
	 * 
	 * @param categoryComAttrMetadataId
	 * @return
	 * @author Dragon Date 2014年10月31日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/deleteCateComAttr")
	@ResponseBody
	public Map<String, Object> deleteCateComAttr(
			Integer categoryComAttrMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateComAttr begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		boolean isSuccess = categoryService
				.deleteCateComAttr(categoryComAttrMetadataId);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		}
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateComAttr end");
		}
		return result;
	}
	
	
	/**
	 * @param keyName
	 * @param lang
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月22日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/checkComKeyName")
	@ResponseBody
	public Map<String, Object> checkComKeyName(String keyName,Integer categoryMetadataId, Integer categoryComAttrMetadataId) {
		Map<String, Object> result = new HashMap<String, Object>();

		int isUnique = -1;
		if (categoryComAttrMetadataId == null) {
			isUnique = categoryService.checkComKeyName(keyName, categoryMetadataId);
		} else {
			isUnique = categoryService.checkComKeyNameOutSelf(keyName,
					categoryMetadataId, categoryComAttrMetadataId);
		}

		result.put("flag", isUnique);
		return result;
	}
	

	/**
	 * 
	 * @param categoryComAttr
	 * @return
	 * @author Dragon Date 2014年10月31日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/createCommonAttrSave")
	@ResponseBody
	public Map<String, Object> createCommonAttr(
			CategoryComAttrPojo categoryComAttr) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.createCommonAttr begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		// String msg = "Category create fail!";
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		categoryComAttr.setIsEnable(true);
		Integer categoryComAttrMetadataId = categoryService
				.addCategoryComAttr(categoryComAttr);

		if (categoryComAttrMetadataId != null) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
			result.put("isSuccess", 1);
		}
		CategoryComAttrMetadataPojo addPojo = categoryService
				.getCateComAttrById(categoryComAttrMetadataId);
		result.put("comAttr", addPojo);
		// List<CategoryComAttrMetadataPojo> comAttrList =
		// categoryService.getCateComAttrByCId(categoryComAttr.getCategoryMetadataId());
		// result.put("comAttrList", comAttrList);
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.createCommonAttr end");
		}
		return result;
	}

	/**
	 * 
	 * @param categoryComAttr
	 * @return
	 * @author Dragon Date 2014年10月31日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/editCommonAttrSave")
	@ResponseBody
	public Map<String, Object> editCommonAttr(
			CategoryComAttrPojo categoryComAttr) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.createCommonAttr begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		categoryComAttr.setIsEnable(true);
		Integer updateRow = categoryService
				.editCategoryComAttr(categoryComAttr);

		if (updateRow != null) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
			result.put("isSuccess", 1);
		}
		CategoryComAttrMetadataPojo editPojo = categoryService
				.getCateComAttrById(categoryComAttr
						.getCategoryComAttrMetadataId());
		result.put("comAttr", editPojo);
		// List<CategoryComAttrMetadataPojo> comAttrList =
		// categoryService.getCateComAttrByCId(categoryComAttr.getCategoryMetadataId());
		// result.put("comAttrList", comAttrList);
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.createCommonAttr end");
		}
		return result;
	}

	/**
	 * @param category
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/createCategorySave")
	@ResponseBody
	public Map<String, Object> addCategory(CategoryPojo category) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.addCategory begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		// String msg = "Category create fail!";
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		category.setIsEnable(true);
		Integer isSuccess = categoryService.addCategory(category);
		if (isSuccess != null) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.addCategory end");
		}
		return result;
	}

	/**
	 * @param model
	 * @param isEdit
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/editCategory", method = RequestMethod.GET)
	public String editCategoryPage(Model model, Integer isEdit,
			Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editCategoryPage begin");
		}
		CategoryMetadataPojo parentCategory = categoryService
				.findById(categoryMetadataId);

		// get parent data
		CategoryMetadataPojo parentCatalog = categoryService
				.findById(parentCategory.getParentId());
		// CategoryMetadataEntity parentCategory =
		// categoryService.getCategoryMetadataById(categoryMetadataId);
		List<LocaleEntity> languages = localeService.getLanguages();

		List<CategoryPojo> categoryPojos = categoryService.getCategoryPojoById(
				parentCategory, languages);
		List<TemplateEntity> templates = categoryService
				.getTemplateListByType(MasterDataCodeConstant.TEMPLATE_TYPE_CATEGORY);

		List<CategoryComAttrMetadataPojo> comAttrs = categoryService
				.getCateComAttrByCId(categoryMetadataId);
		List<CategorySKUAttrMetadataPojo> skuAttrs = categoryService
				.getCateSkuAttrByCId(categoryMetadataId);
		List<CategorySearchKeywordMetadataPojo> searchKeys = categoryService
				.getCateSearchKeyByCId(categoryMetadataId);
		List<CategoryAccessoryMetadataPojo> accessorys = categoryService
				.getCateAccessoryByCId(categoryMetadataId);
		model.addAttribute("templates", templates);
		model.addAttribute("isEdit", isEdit);
		model.addAttribute("languages", languages);
		model.addAttribute("comAttrs", comAttrs);
		model.addAttribute("skuAttrs", skuAttrs);
		model.addAttribute("searchKeys", searchKeys);
		model.addAttribute("accessorys", accessorys);
		model.addAttribute("heroAreasKey", heroAreasKey);
		model.addAttribute("categoryPojos", categoryPojos);
		model.addAttribute("isEdit", isEdit);
		model.addAttribute("parentCategory", parentCategory);
		model.addAttribute("parentCatalog", parentCatalog);

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editCategoryPage end");
		}
		return "productcatalogs/categoryEdit";
	}

	/**
	 * @param category
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/editCategorySave")
	@ResponseBody
	public Map<String, Object> editCategory(CategoryPojo category) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editCategory begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);

		boolean isSuccess = categoryService.editCatalog(category);
		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editCategory end");
		}
		return result;
	}

	/**
	 * 
	 * @param categoryName
	 * @param lang
	 * @param categoryMetadataId
	 * @param categoryId
	 * @return
	 * @author XHY Date 2014年10月23日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/checkCategoryName")
	@ResponseBody
	public Map<String, Object> checkCategoryName(String categoryName,
			Integer lang, Integer categoryMetadataId, Integer categoryId) {
		Map<String, Object> result = new HashMap<String, Object>();
		int isUnique = -1;
		if (categoryId == null) {
			isUnique = categoryService.checkCategoryName(categoryName, lang,
					categoryMetadataId);
		} else {
			isUnique = categoryService.checkCategoryNameOutSelf(categoryName,
					lang, categoryMetadataId, categoryId);
		}
		result.put("flag", isUnique);
		return result;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月5日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/newSkuAttrPage", method = RequestMethod.GET)
	public String newSkuAttrPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newSkuAttrPage begin");
		}
		SysUserEntity sysUser = userSession.getSysUser();

		List<LocaleEntity> languages = localeService.getLanguages();
		int masterDataId = MasterDataCodeConstant.FILE_ASSET_IMAGE;

		model.addAttribute("masterDataId", masterDataId);
		model.addAttribute("languages", languages);
		model.addAttribute("categoryMetadataId", categoryMetadataId);
		model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newSkuAttrPage end");
		}
		return "productcatalogs/skuAttributeNew";
	}

	/**
	 * 
	 * @param request
	 * @param categorySKUAttrMetadataEntity
	 * @param skuAttrs
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/saveSkuAttr")
	@ResponseBody
	public Map<String, Object> saveSkuAttr(HttpServletRequest request,
			CategorySKUAttrMetadataEntity categorySKUAttrMetadataEntity,
			@RequestParam(value = "skuAttrs") String skuAttrs,
			@RequestParam(value = "attrValues") String attrValues,
			@RequestParam(value = "imageUrls") Integer[] imageUrls) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveSkuAttr begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		List<CategorySKUAttrEntity> skuAttrList = new ArrayList<CategorySKUAttrEntity>();

		List<CategorySKUAttrValuesPojo> attrValuesList = new ArrayList<CategorySKUAttrValuesPojo>();
		JSONArray attrValuesArray = JSONArray.fromObject(attrValues);

		if (attrValuesArray.size() > 0) {
			for (int i = 0; i < attrValuesArray.size(); i++) {
				if (attrValuesArray.getString(i).toString().contains("[")) {
					attrValuesList.add(JSonUtil.toObject(attrValuesArray
							.getString(i).toString(),
							CategorySKUAttrValuesPojo.class));
				}
			}
		}

		JSONArray skuAttrArray = JSONArray.fromObject(skuAttrs);

		if (skuAttrArray.size() > 0) {
			for (int i = 0; i < skuAttrArray.size(); i++) {
				skuAttrList.add(JSonUtil.toObject(skuAttrArray.getString(i)
						.toString(), CategorySKUAttrEntity.class));
			}
		}
		Integer categorySKUAttrMetadataId = categoryService
				.saveCategorySkuAttrs(categorySKUAttrMetadataEntity,
						skuAttrList, attrValuesList, imageUrls);
		if(categorySKUAttrMetadataId != 0){
			CategorySKUAttrMetadataPojo addPojo = categoryService
					.getSkuAttrById(categorySKUAttrMetadataId);
			result.put("skuAttr", addPojo);
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
		}
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveSkuAttr end.");
		}

		return result;
	}

	/**
	 * 
	 * @param request
	 * @param categorySKUAttrMetadataEntity
	 * @param skuAttrs
	 * @param attrValues
	 * @param imageUrls
	 * @return
	 * @author Dragon Date 2014年11月21日
	 * @version
	 */
	@RequestMapping("/unlimited/editSkuAttr")
	@ResponseBody
	public Map<String, Object> editSkuAttr(HttpServletRequest request,
			CategorySKUAttrMetadataEntity categorySKUAttrMetadataEntity,
			@RequestParam(value = "skuAttrs") String skuAttrs,
			@RequestParam(value = "attrValues") String attrValues,
			@RequestParam(value = "imageUrls") Integer[] imageUrls) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSkuAttr begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);

		List<CategorySKUAttrEntity> skuAttrList = new ArrayList<CategorySKUAttrEntity>();

		JSONArray skuAttrArray = JSONArray.fromObject(skuAttrs);

		List<CategorySKUAttrValuesPojo> attrValuesList = new ArrayList<CategorySKUAttrValuesPojo>();
		JSONArray attrValuesArray = JSONArray.fromObject(attrValues);

		if (attrValuesArray.size() > 0) {
			for (int i = 0; i < attrValuesArray.size(); i++) {
				if (attrValuesArray.getString(i).toString().contains("[")) {
					attrValuesList.add(JSonUtil.toObject(attrValuesArray
							.getString(i).toString(),
							CategorySKUAttrValuesPojo.class));
				}
			}
		}

		if (skuAttrArray.size() > 0) {
			for (int i = 0; i < skuAttrArray.size(); i++) {
				skuAttrList.add(JSonUtil.toObject(skuAttrArray.getString(i)
						.toString(), CategorySKUAttrEntity.class));
			}
		}
		Integer categorySKUAttrMetadataId = categoryService
				.editCategorySkuAttrs(categorySKUAttrMetadataEntity,
						skuAttrList, attrValuesList, imageUrls);

		CategorySKUAttrMetadataPojo editSkuAttr = categoryService
				.getSkuAttrById(categorySKUAttrMetadataId);
		result.put("skuAttr", editSkuAttr);

		msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSkuAttr end.");
		}

		return result;
	}

	/**
	 * 
	 * @param categorySKUAttrMetadataId
	 * @return
	 * @author Dragon Date 2014年11月10日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/deleteCateSkuAttr")
	@ResponseBody
	public Map<String, Object> deleteCateSkuAttr(
			Integer categorySKUAttrMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateSkuAttr begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		boolean isSuccess = categoryService
				.deleteCateSkuAttr(categorySKUAttrMetadataId);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		}
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateSkuAttr end");
		}
		return result;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryMetadataId
	 * @return
	 * @author Dragon Date 2014年11月10日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/newSearchKeyPage", method = RequestMethod.GET)
	public String newSearchKeyPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newSearchKeyPage begin");
		}
		SysUserEntity sysUser = userSession.getSysUser();

		List<LocaleEntity> languages = localeService.getLanguages();

		model.addAttribute("languages", languages);
		model.addAttribute("categoryMetadataId", categoryMetadataId);
		model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newSearchKeyPage end");
		}
		return "productcatalogs/searchKeywordsNew";
	}

	/**
	 * 
	 * @param request
	 * @param categorySearchKeywordMetadata
	 * @param searchKeys
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/saveSearchKey")
	@ResponseBody
	public Map<String, Object> saveSearchKey(HttpServletRequest request,
			CategorySearchKeywordMetadataEntity categorySearchKeywordMetadata,
			@RequestParam(value = "searchKeys") String searchKeys) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveSearchKey begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		List<CategorySearchKeywordEntity> searchKeywords = new ArrayList<CategorySearchKeywordEntity>();

		JSONArray skuAttrArray = JSONArray.fromObject(searchKeys);

		if (skuAttrArray.size() > 0) {
			for (int i = 0; i < skuAttrArray.size(); i++) {
				searchKeywords.add(JSonUtil.toObject(skuAttrArray.getString(i)
						.toString(), CategorySearchKeywordEntity.class));
			}
		}
		Integer categorySearchKeyMetadataId = categoryService
				.saveCategorySearchKeys(categorySearchKeywordMetadata,
						searchKeywords);

		CategorySearchKeywordMetadataPojo addPojo = categoryService
				.getSearchKeyById(categorySearchKeyMetadataId);
		result.put("searchKey", addPojo);

		msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveSearchKey end.");
		}

		return result;
	}

	/**
	 * 
	 * @param categorySearchKeywordMetadataId
	 * @return
	 * @author Dragon Date 2014年11月10日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/deleteCateSearchKey")
	@ResponseBody
	public Map<String, Object> deleteCateSearchKey(
			Integer categorySearchKeywordMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateSearchKey begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		boolean isSuccess = categoryService
				.deleteCateSearchKey(categorySearchKeywordMetadataId);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		}
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateSearchKey end");
		}
		return result;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categorySKUAttrMetadataId
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/editSkuAttrPage")
	public String editSkuAttrPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categorySKUAttrMetadataId") Integer categorySKUAttrMetadataId) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSkuAttrPage begin.");
		}

		// Get CategorySkuAttrMetadataEntity
		CategorySKUAttrMetadataEntity categorySkuAttrMetadata = categoryService
				.getSkuAttrById(categorySKUAttrMetadataId);
		// Get CategorySkuAttrEntity
		CategorySKUAttrEntity skuAttr = new CategorySKUAttrEntity();
		skuAttr.setIsEnable(true);
		skuAttr.setCategorySKUAttrMetadataId(categorySKUAttrMetadataId);
		List<CategorySKUAttrEntity> skuAttrList = categoryService
				.getSKuAttrList(skuAttr);

		// Get LocaleEntity
		List<LocaleEntity> languages = localeService.getLanguages();

		// Get skuAttrValues

		List<CategorySKUAttrValuesMetadataEntity> skuAttrValuesMetaList = categoryService
				.getSKuAttrValuesBySKUId(categorySKUAttrMetadataId);
		List<CategorySKUAttrValuesEntity> skuAttrValuesList = categoryService
				.getSkuAttrValueByMetaList(skuAttrValuesMetaList);

		int skuAttrMetaLength = skuAttrValuesMetaList.size();

		for (int i = 0; i < (10 - skuAttrMetaLength); i++) {
			skuAttrValuesMetaList
					.add(new CategorySKUAttrValuesMetadataEntity());
		}

		Map<String, Object> attrValues = new HashMap<String, Object>();

		List<CategorySKUAttrValuesEntity> splitSkuAttr = new ArrayList<CategorySKUAttrValuesEntity>();

		for (LocaleEntity language : languages) {
			for (CategorySKUAttrValuesEntity attrValue : skuAttrValuesList) {
				if (language.getLocaleId() == attrValue.getLang()) {
					splitSkuAttr.add(attrValue);
				}
			}
			// Completion 10
			int splitSKuAttrLength = splitSkuAttr.size();
			for (int i = 0; i < (10 - splitSKuAttrLength); i++) {
				splitSkuAttr.add(new CategorySKUAttrValuesEntity());
			}
			attrValues.put(language.getLocaleCode(),
					new ArrayList<CategorySKUAttrValuesEntity>(splitSkuAttr));
			splitSkuAttr.clear();
		}
		// Get skuAttrValues^^
		int masterDataId = MasterDataCodeConstant.FILE_ASSET_IMAGE;
		model.addAttribute("masterDataId", masterDataId);
		model.addAttribute("metaList", skuAttrValuesMetaList);
		model.addAttribute("attrValues", attrValues);
		model.addAttribute("skuAttrList", skuAttrList);
		model.addAttribute("languages", languages);
		model.addAttribute("categorySkuAttrMetadata", categorySkuAttrMetadata);

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSkuAttrPage end.");
		}

		return "productcatalogs/skuAttributeEdit";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categorySearchKeywordMetadataId
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/editSearchKeyPage")
	public String editSearchKeyPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categorySearchKeywordMetadataId") Integer categorySearchKeywordMetadataId) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSearchKeyPage begin.");
		}

		// Get CategorySearchKeywordMetadataEntity
		CategorySearchKeywordMetadataEntity categorySearchKeywordMetadata = categoryService
				.getSearchKeyById(categorySearchKeywordMetadataId);
		// Get CategorySearchKeywordEntity
		CategorySearchKeywordEntity searchKeyword = new CategorySearchKeywordEntity();
		searchKeyword.setIsEnable(true);
		searchKeyword
				.setCategorySearchKeywordMetadataId(categorySearchKeywordMetadataId);
		List<CategorySearchKeywordEntity> searchKeywordList = categoryService
				.getSearchKeyList(searchKeyword);

		// Get LocaleEntity
		List<LocaleEntity> languages = localeService.getLanguages();

		model.addAttribute("searchKeywordList", searchKeywordList);
		model.addAttribute("languages", languages);
		model.addAttribute("categorySearchKeywordMetadata",
				categorySearchKeywordMetadata);

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSearchKeyPage end.");
		}

		return "productcatalogs/searchKeywordsEdit";
	}

	/**
	 * 
	 * @param request
	 * @param categorySKUAttrMetadataEntity
	 * @param skuAttrs
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/editSearchKey")
	@ResponseBody
	public Map<String, Object> editSearchKey(HttpServletRequest request,
			CategorySearchKeywordMetadataEntity categorySearchKeywordMetadata,
			@RequestParam(value = "searchKeys") String searchKeys) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSearchKey begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);

		List<CategorySearchKeywordEntity> searchKeywords = new ArrayList<CategorySearchKeywordEntity>();

		JSONArray searchKeywordArray = JSONArray.fromObject(searchKeys);

		if (searchKeywordArray.size() > 0) {
			for (int i = 0; i < searchKeywordArray.size(); i++) {
				searchKeywords.add(JSonUtil.toObject(searchKeywordArray
						.getString(i).toString(),
						CategorySearchKeywordEntity.class));
			}
		}
		Integer categorySearchKeyMetadataId = categoryService
				.editCategorySearchKey(categorySearchKeywordMetadata,
						searchKeywords);

		CategorySearchKeywordMetadataPojo searchKeyPojo = categoryService
				.getSearchKeyById(categorySearchKeyMetadataId);
		result.put("searchKey", searchKeyPojo);

		msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editSearchKey end.");
		}

		return result;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryMetadataId
	 * @return
	 * @author Dragon Date 2014年11月10日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/newAccessoryPage", method = RequestMethod.GET)
	public String newAccessoryPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newAccessoryPage begin");
		}
		SysUserEntity sysUser = userSession.getSysUser();

		List<LocaleEntity> languages = localeService.getLanguages();
		int type = MasterDataCodeConstant.TYPE_ACCESSORY_TYPE;// 所需数据常量
		int lang = 1;// language
		List<Map<String, Object>> typeList = categoryService.getAllType(type,
				lang);
		model.addAttribute("typeList", typeList);
		model.addAttribute("languages", languages);
		model.addAttribute("categoryMetadataId", categoryMetadataId);
		model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newAccessoryPage end");
		}
		return "productcatalogs/accessoryNew";
	}

	/**
	 * 
	 * @param request
	 * @param categoryAccessoryMetadata
	 * @param searchKeys
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/saveAccessory")
	@ResponseBody
	public Map<String, Object> saveAccessory(HttpServletRequest request,
			CategoryAccessoryMetadataEntity categoryAccessoryMetadata,
			@RequestParam(value = "accessorys") String searchKeys) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveAccessory begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		List<CategoryAccessoryEntity> accessories = new ArrayList<CategoryAccessoryEntity>();

		JSONArray accArray = JSONArray.fromObject(searchKeys);

		if (accArray.size() > 0) {
			for (int i = 0; i < accArray.size(); i++) {
				accessories.add(JSonUtil.toObject(accArray.getString(i)
						.toString(), CategoryAccessoryEntity.class));
			}
		}
		Integer categoryAccessoryMetadataId = categoryService.saveAccessors(
				categoryAccessoryMetadata, accessories);

		CategoryAccessoryMetadataPojo addPojo = categoryService
				.getAccessoryById(categoryAccessoryMetadataId);
		result.put("accessory", addPojo);

		msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveAccessory end.");
		}

		return result;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryAccessoryMetadataId
	 * @return
	 * @author Dragon Date 2014年11月11日
	 * @version
	 */
	@RequestMapping("/unlimited/editAccessoryPage")
	public String editAccessoryPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryAccessoryMetadataId") Integer categoryAccessoryMetadataId) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editAccessoryPage begin.");
		}

		// Get CategoryAccessoryMetadataEntity
		CategoryAccessoryMetadataEntity categoryAccessoryMetadata = categoryService
				.getAccessoryById(categoryAccessoryMetadataId);
		// Get CategoryAccessoryEntity
		CategoryAccessoryEntity accessory = new CategoryAccessoryEntity();
		accessory.setIsEnable(true);
		accessory.setCategoryAccessoryMetadataId(categoryAccessoryMetadataId);

		List<CategoryAccessoryEntity> accessoryList = categoryService
				.getAccessoryList(accessory);

		int type = MasterDataCodeConstant.TYPE_ACCESSORY_TYPE;// 所需数据常量
		int lang = 1;// language
		List<Map<String, Object>> typeList = categoryService.getAllType(type,
				lang);
		model.addAttribute("typeList", typeList);
		// Get LocaleEntity
		List<LocaleEntity> languages = localeService.getLanguages();

		model.addAttribute("accessoryList", accessoryList);
		model.addAttribute("languages", languages);
		model.addAttribute("categoryAccessoryMetadata",
				categoryAccessoryMetadata);

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editAccessoryPage end.");
		}

		return "productcatalogs/accessoryEdit";
	}

	/**
	 * 
	 * @param request
	 * @param categoryAccessoryMetadataEntity
	 * @param accessorys
	 * @return
	 * @author Dragon Date 2014年11月14日
	 * @version
	 */
	@RequestMapping("/unlimited/editAccessory")
	@ResponseBody
	public Map<String, Object> editAccessory(HttpServletRequest request,
			CategoryAccessoryMetadataEntity categoryAccessoryMetadataEntity,
			@RequestParam(value = "accessorys") String accessorys) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editAccessory begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);

		List<CategoryAccessoryEntity> searchKeywords = new ArrayList<CategoryAccessoryEntity>();

		JSONArray searchKeywordArray = JSONArray.fromObject(accessorys);

		if (searchKeywordArray.size() > 0) {
			for (int i = 0; i < searchKeywordArray.size(); i++) {
				searchKeywords.add(JSonUtil
						.toObject(searchKeywordArray.getString(i).toString(),
								CategoryAccessoryEntity.class));
			}
		}
		Integer categorySearchKeyMetadataId = categoryService.editAccessorys(
				categoryAccessoryMetadataEntity, searchKeywords);

		CategoryAccessoryMetadataPojo accessoryPojo = categoryService
				.getAccessoryById(categorySearchKeyMetadataId);
		result.put("accessory", accessoryPojo);

		msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editAccessory end.");
		}

		return result;
	}

	/**
	 * 
	 * @param categorySearchKeywordMetadataId
	 * @return
	 * @author Dragon Date 2014年11月10日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/deleteCateAccessory")
	@ResponseBody
	public Map<String, Object> deleteCateAccessory(
			Integer categoryAccessoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateAccessory begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		boolean isSuccess = categoryService
				.deleteCateAccessory(categoryAccessoryMetadataId);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		}
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateAccessory end");
		}
		return result;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月5日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/newBannerUnitPage", method = RequestMethod.GET)
	public String newBannerUnitPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId,
			@RequestParam(value = "lang") Integer lang) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newBannerUnitPage begin");
		}
		// SysUserEntity sysUser = userSession.getSysUser();

		model.addAttribute("lang", lang);
		model.addAttribute("categoryMetadataId", categoryMetadataId);
		model.addAttribute("brannerKey", brannerKey);
		// model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.newBannerUnitPage end");
		}
		return "productcatalogs/bannerUnitNew";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param categoryBannerUnitId
	 * @return
	 * @author Dragon Date 2014年11月12日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/editBannerUnitPage", method = RequestMethod.GET)
	public String editBannerUnitPage(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryBannerUnitId") Integer categoryBannerUnitId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editBannerUnitPage begin");
		}
		CategoryBannerUnitEntity editEntity = categoryService
				.getCategoryBannerById(categoryBannerUnitId);

		model.addAttribute("bannerUnit", editEntity);
		model.addAttribute("brannerKey", brannerKey);
		// model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editBannerUnitPage end");
		}
		return "productcatalogs/bannerUnitEdit";
	}

	/**
	 * 
	 * @param request
	 * @param categoryBannerUnit
	 * @param searchKeys
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/saveBannerUnit")
	@ResponseBody
	public Map<String, Object> saveBannerUnit(HttpServletRequest request,
			CategoryBannerUnitEntity categoryBannerUnit) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveBannerUnit begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		Integer bannerUnitId = categoryService
				.saveBannerUnit(categoryBannerUnit);

		CategoryBannerUnitEntity addPojo = categoryService
				.getCategoryBannerById(bannerUnitId);
		result.put("bannerUnit", addPojo);

		msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.saveBannerUnit end.");
		}

		return result;
	}

	/**
	 * 
	 * @param request
	 * @param categoryBannerUnit
	 * @param searchKeys
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 */
	@RequestMapping("/unlimited/editBannerUnit")
	@ResponseBody
	public Map<String, Object> editBannerUnit(HttpServletRequest request,
			CategoryBannerUnitEntity categoryBannerUnit) {

		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editBannerUnit begin.");
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);

		Integer bannerUnitId = categoryService
				.editBannerUnit(categoryBannerUnit);

		CategoryBannerUnitEntity addPojo = categoryService
				.getCategoryBannerById(bannerUnitId);
		result.put("bannerUnit", addPojo);

		msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		result.put("msg", msg);
		result.put("isSuccess", 1);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.editBannerUnit end.");
		}

		return result;
	}

	/**
	 * 
	 * @param categorySearchKeywordMetadataId
	 * @return
	 * @author Dragon Date 2014年11月10日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/deleteBannerUnit")
	@ResponseBody
	public Map<String, Object> deleteBannerUnit(Integer categoryBannerUnitId) {
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateAccessory begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		boolean isSuccess = categoryService
				.deleteBannerUnitById(categoryBannerUnitId);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		}
		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CategoryController.deleteCateAccessory end");
		}
		return result;
	}

	/**
	 * 
	 * @param model
	 * @param categoryMetadataId
	 * @param lang
	 * @return
	 * @author Dragon Date 2014年11月18日
	 * @version
	 */
	@RequestMapping("/unlimited/getBannerUnitList")
	public String getBannerUnit(
			Model model,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId,
			@RequestParam(value = "lang") Integer lang) {

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.getBannerUnit begin.");
		}

		List<CategoryBannerUnitEntity> bannerUnitList = categoryService
				.getBannerUnitListByCon(categoryMetadataId, lang);

		model.addAttribute("bannerUnitList", bannerUnitList);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.editCADPage end.");
		}

		return "productcatalogs/bannerUnitList";
	}

	/**
	 * @param data
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	/*
	 * @RequestMapping(value = "/deleteCategory")
	 * 
	 * @ResponseBody public Map<String, Object> deleteCategory(Integer[]
	 * categoryMetadataIds) { if (logger.isInfoEnabled()) {
	 * logger.info("CategoryController.deleteCategory begin"); } Map<String,
	 * Object> result = new HashMap<String, Object>();
	 * 
	 * String msg =
	 * PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED
	 * );
	 * 
	 * boolean isSuccess = categoryService.deleteCatalog(categoryMetadataIds);
	 * 
	 * if(isSuccess){ msg =
	 * PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS
	 * ); }
	 * 
	 * result.put("msg", msg); if (logger.isInfoEnabled()) {
	 * logger.info("CategoryController.deleteCategory end"); } return result; }
	 */
}
