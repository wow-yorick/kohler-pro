/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.bean.PublishConf;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.dao.utils.UserSession;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CategoryMetadataPojo;
import com.kohler.entity.extend.CategoryPojo;
import com.kohler.service.CatalogService;
import com.kohler.service.LocaleService;
import com.kohler.service.PublishHtmlForInterfaceService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * 用于处理Catalog,及公共的相关的请求
 * 
 * @author XHY
 * @Date 2014年10月9日
 */
@Controller
@RequestMapping(value = "/catalog")
public class CatalogController extends BasicController {

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private UserSession userSession;

	@Autowired
	private LocaleService localeService;

	@Autowired
	private PublishHtmlForInterfaceService publishHtmlForInterfaceService;

	@Value("#{settings['HeroAreas.dataType.key']}")
	private String heroAreasKey;
	@Value("#{settings['banner.dataType.key']}")
	private String brannerKey;

	/**
	 * @param model
	 * @param pager
	 * @param categoryId
	 * @param request
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/catalogList")
	public String getCategoryList(Model model,
			Pager<Map<String, Object>> pager, Integer categoryId,
			HttpServletRequest request) {

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.getCategoryList begin");
		}

		String uri = request.getRequestURI() + "?";
		if (categoryId == null) {
			categoryId = 0;
		}
		uri += "categoryId=" + categoryId + "&";

		List<Map<String, Object>> categoryTree = catalogService
				.getCategoryWithMap();
		// check the catalog is top and create product is show ?
		boolean isTop = catalogService.checkCategoryIsTop(categoryId);

		if (isTop || categoryId == 0) {
			model.addAttribute("check", "0");// top
		} else {
			model.addAttribute("check", "2");
			boolean isBottom = catalogService.checkCategoryIsBottom(categoryId);
			if (isBottom) {
				model.addAttribute("check", "1");// if bottom find this.product

				pager = catalogService.getProductByCateId(pager, categoryId);

				pager.setUrl(uri);
				model.addAttribute("categoryId", categoryId);
				model.addAttribute("type", "product");
				model.addAttribute("pager", pager);
				model.addAttribute("categoryTree",
						JSonUtil.toJSonString(categoryTree));

				if (logger.isInfoEnabled()) {
					logger.info("CatalogController.getCategoryList end");
				}
				return "productcatalogs/categoryList";
			}
		}

		pager = catalogService.getCategoryPageById(pager, categoryId);

		pager.setUrl(uri);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("pager", pager);
		model.addAttribute("categoryTree", JSonUtil.toJSonString(categoryTree));

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.getCategoryList end");
		}
		return "productcatalogs/categoryList";
	}

	/**
	 * @param model
	 * @param category
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/createCatalog")
	public String createCatalog(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.createCatalog begin");
		}
		SysUserEntity sysUser = userSession.getSysUser();
		CategoryMetadataEntity pareCate = new CategoryMetadataEntity();
		if (categoryMetadataId != 0) {
			pareCate = catalogService.findById(categoryMetadataId);
		} else {
			pareCate.setCategoryMetadataId(0);
			pareCate.setLevel(0);
		}

		List<LocaleEntity> languages = localeService.getLanguages();
		// List<TemplateEntity>
		// templates=catalogService.getTemplateListByType(MasterDataCodeConstant.TEMPLATE_TYPE_CATALOG);
		List<TemplateEntity> templates = catalogService
				.getTemplateListByType(MasterDataCodeConstant.TEMPLATE_TYPE_CATALOG);
		model.addAttribute("languages", languages);
		model.addAttribute("templates", templates);
		model.addAttribute("brannerKey", brannerKey);
		model.addAttribute("heroAreasKey", heroAreasKey);
		model.addAttribute("pareCate", pareCate);
		model.addAttribute("catalog", "catalog");
		model.addAttribute("sysUser", sysUser);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.createCatalog end");
		}
		return "productcatalogs/catalogNew";
	}

	/**
	 * @param category
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/createCatalogSave")
	@ResponseBody
	public Map<String, Object> addCatalog(CategoryPojo category) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.addCatalog begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

		category.setIsEnable(true);
		Integer isSuccess = catalogService.addCatalog(category);
		if (isSuccess != null) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.addCatalog end");
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
	@RequestMapping(value = "/editCatalog")
	public String editcatalogPage(Model model,Integer isEdit,
			Integer categoryMetadataId) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.editcatalogPage begin");
		}
		CategoryMetadataEntity parentCategory = catalogService
				.getCategoryMetadataById(categoryMetadataId);
		List<LocaleEntity> languages = localeService.getLanguages();

		List<CategoryPojo> categoryPojos = catalogService.getCategoryPojoById(
				parentCategory, languages);

		List<CategoryMetadataPojo> childrenCate = catalogService
				.findByPId(categoryMetadataId);
		// List<TemplateEntity>
		// templates=catalogService.getTemplateListByType(MasterDataCodeConstant.TEMPLATE_TYPE_CATALOG);
		List<TemplateEntity> templates = catalogService
				.getTemplateListByType(MasterDataCodeConstant.TEMPLATE_TYPE_CATEGORY);
		model.addAttribute("templates", templates);
		model.addAttribute("brannerKey", brannerKey);
		model.addAttribute("heroAreasKey", heroAreasKey);
		model.addAttribute("languages", languages);
		model.addAttribute("childrenCate", childrenCate);

		model.addAttribute("categoryPojos", categoryPojos);
		model.addAttribute("isEdit", isEdit);
		model.addAttribute("parentCategory", parentCategory);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.editcatalogPage end");
		}
		return "productcatalogs/catalogEdit";
	}

	/**
	 * @param model
	 * @param isEdit
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/previewTransfer")
	public String preview(Model model, Integer categoryMetadataId, String type) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.preview begin");
		}

		model.addAttribute("categoryMetadataId", categoryMetadataId);
		model.addAttribute("type", type);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.preview end");
		}
		return "productcatalogs/catalogPreviewBlank";
	}

	/**
	 * 
	 * @param model
	 * @param isEdit
	 * @param categoryMetadataId
	 * @return
	 * @author Dragon Date 2014年11月25日
	 * @version
	 */
	@RequestMapping(value = "/previewCatalog", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> previewCatalog(Integer categoryMetadataId,
			String type) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.previewCatalog begin");
		}
		

		PublishConf publishConf = new PublishConf();
		publishConf.setDataId(categoryMetadataId);
		publishConf.setLang(CommonConstants.LOCALE_CN);
		publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
		publishConf.setIsPreview(true);
		int templateType = 0;
		if ("catalog".equals(type)) {
			templateType = CommonConstants.TEMPLATE_TYPE_CATALOG;
		} else if ("category".equals(type)) {
			templateType = CommonConstants.TEMPLATE_TYPE_CATEGORY;
		} else if ("product".equals(type)) {
			publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PRODUCT_SKU_JSFILE);
			publishHtmlForRun(publishConf);
			
			templateType = CommonConstants.TEMPLATE_TYPE_PRODUCT;
		}
		publishConf.setTemplateType(templateType);

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.previewCatalog end");
		}
		return publishHtmlForRun(publishConf);

	}

	/**
	 * @param result
	 * @param publishConf
	 * @return
	 * @author XHY
	 * Date 2014年12月23日
	 * @version
	 */
	@SuppressWarnings("finally")
	private Map<String, Object> publishHtmlForRun(PublishConf publishConf) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = publishHtmlForInterfaceService.run(publishConf);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	/**
	 * @param catalogName
	 * @param lang
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月22日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/checkCatalogName")
	@ResponseBody
	public Map<String, Object> checkCatalogName(String catalogName,
			Integer lang, Integer categoryMetadataId, Integer categoryId) {
		Map<String, Object> result = new HashMap<String, Object>();

		int isUnique = -1;
		if (categoryId == null) {
			isUnique = catalogService.checkCatalogName(catalogName, lang,
					categoryMetadataId);
		} else {
			isUnique = catalogService.checkCatalogNameOutSelf(catalogName,
					lang, categoryMetadataId, categoryId);
		}

		result.put("flag", isUnique);
		return result;
	}

	/**
	 * @param category
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/editCatalogSave")
	@ResponseBody
	public Map<String, Object> editcatalog(CategoryPojo category) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.editcatalog begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);

		boolean isSuccess = catalogService.editCatalog(category);
		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.editcatalogPage end");
		}
		return result;
	}

	/**
	 * 
	 * @param categoryMetadataIds
	 * @return
	 * @author Dragon Date 2014年11月14日
	 * @version
	 */
	@RequestMapping(value = "/deleteCatalog")
	@ResponseBody
	public Map<String, Object> deleteCatalog(Integer[] categoryMetadataIds) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.deleteCatalog begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();

		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		boolean isSuccess = catalogService.deleteCatalog(categoryMetadataIds);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.deleteCatalog end");
		}
		return result;
	}

	/**
	 * @param data
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/deleteCategory")
	@ResponseBody
	public Map<String, Object> deleteCategory(Integer[] categoryMetadataIds) {
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.deleteCategory begin");
		}
		Map<String, Object> result = new HashMap<String, Object>();

		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		boolean isSuccess = catalogService.deleteCatalog(categoryMetadataIds);

		if (isSuccess) {
			msg = PropertiesUtils
					.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
		}

		result.put("msg", msg);
		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.deleteCategory end");
		}
		return result;
	}

	/**
	 * @param data
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	@RequestMapping(value = "/deleteProduct")
	@ResponseBody
	public Map<String, Object> deleteProduct(Integer[] productMetadataIds) {

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.deleteProduct begin");
		}

		String error = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = PropertiesUtils
				.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);

		boolean isSuccess = catalogService.deleteProduct(productMetadataIds);

		result.put("msg", error);
		if (isSuccess) {
			result.put("msg", msg);
		}

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.deleteProduct end");
		}
		return result;
	}

	/**
	 * ProductPart list Page
	 * 
	 * @param model
	 *            model
	 * @param productMetadataId
	 *            productMetadataId
	 * @return
	 * @author ztt Date 2014年10月22日
	 * @version
	 */
	@RequestMapping("/unlimited/getBannerUnit")
	public String getBannerUnit(
			Model model,
			@RequestParam(value = "catalogMetadataId") Integer catalogMetadataId,
			@RequestParam(value = "categoryMetadataId") Integer categoryMetadataId,
			@RequestParam(value = "lang") Integer lang,
			@RequestParam(value = "langCode") String langCode) {

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.getBannerUnit begin.");
		}

		Map<String,Object> bannerUnit = catalogService.getBannerUnitByCon(
				catalogMetadataId, categoryMetadataId, lang);

		model.addAttribute("bannerUnitValue", bannerUnit.get("fieldValues"));
		model.addAttribute("bannerUnitValueId", bannerUnit.get("fieldValueIds"));
		model.addAttribute("categoryMetadataId", categoryMetadataId);
		model.addAttribute("langCode", langCode);

		if (logger.isInfoEnabled()) {
			logger.info("CatalogController.getBannerUnit end.");
		}

		return "productcatalogs/bannerUnitInput";
	}
}
