/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CategoryPojo;

/**
 * Class Function Description
 * 
 * @author XHY
 * @Date 2014年11月4日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml" })
public class CatalogServiceTest {

	@Autowired
	private CatalogService catalogService;

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getCategoryWithMap()}.
	 */
	@Test
	public void testGetCategoryWithMap() {

		List<Map<String, Object>> categoryTree = catalogService
				.getCategoryWithMap();
		for (Map<String, Object> category : categoryTree) {
			assertEquals(category, null);
		}

	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getCategoryPageById(com.kohler.dao.utils.Pager, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetCategoryPageById() {
		Pager p = new Pager();
		Integer categoryId = 1;
		Pager<Map<String, Object>> pager = catalogService.getCategoryPageById(
				p, categoryId);
		assertEquals(pager, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#addCatalog(com.kohler.entity.extend.CategoryPojo)}
	 * .
	 */
	@Test
	public void testAddCatalog() {
		CategoryPojo pojo = new CategoryPojo();
		Integer success = catalogService.addCatalog(pojo);
		assertEquals(success, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getCategoryById(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetCategoryById() {
		Integer categoryId = 2;
		CategoryEntity catalog = catalogService.getCategoryById(categoryId);
		assertEquals(catalog, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getCategoryByCategoryMetadataId(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetCategoryByCategoryMetadataId() {
		Integer categoryMetadataId = 2;
		CategoryEntity category = catalogService
				.getCategoryByCategoryMetadataId(categoryMetadataId);
		assertEquals(category, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#checkCategoryIsTop(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testCheckCategoryIsTop() {
		Integer categoryMetadataId = 2;
		Boolean istop = catalogService.checkCategoryIsTop(categoryMetadataId);
		assertEquals(istop, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#checkCategoryIsBottom(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testCheckCategoryIsBottom() {
		Integer categoryMetadataId = 2;
		Boolean isBottom = catalogService
				.checkCategoryIsBottom(categoryMetadataId);
		assertEquals(isBottom, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getProductByCateId(com.kohler.dao.utils.Pager, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetProductByCateId() {
		Pager p = new Pager();
		Integer categoryId = 2;
		Pager<Map<String, Object>> pager = catalogService.getProductByCateId(p,
				categoryId);
		assertEquals(pager, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#findById(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindById() {
		Integer categoryMetadataId = 2;
		CategoryMetadataEntity cm = catalogService.findById(categoryMetadataId);
		assertEquals(cm, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getCategoryMetadataById(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetCategoryMetadataById() {
		Integer categoryMetadataId = 2;
		CategoryMetadataEntity cm = catalogService
				.getCategoryMetadataById(categoryMetadataId);
		assertEquals(cm, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getCategorysByCategoryMetadataId(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetCategorysByCategoryMetadataId() {
		Integer categoryMetadataId = 2;
		List<CategoryEntity> list = catalogService
				.getCategorysByCategoryMetadataId(categoryMetadataId);
		for (CategoryEntity entity : list) {
			assertEquals(entity, null);
		}
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#editCatalog(com.kohler.entity.extend.CategoryPojo)}
	 * .
	 */
	@Test
	public void testEditCatalog() {
		CategoryPojo pojo = new CategoryPojo();
		boolean success = catalogService.editCatalog(pojo);
		assertEquals(success, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#deleteCatalog(java.lang.Integer[])}
	 * .
	 */
	@Test
	public void testDeleteCatalog() {
		Integer[] categoryMetadataIds = { 1, 2 };
		boolean success = catalogService.deleteCatalog(categoryMetadataIds);
		assertEquals(success, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#deleteProduct(java.lang.Integer[])}
	 * .
	 */
	@Test
	public void testDeleteProduct() {
		Integer[] productMetadataIds = { 1, 2 };
		boolean success = catalogService.deleteProduct(productMetadataIds);
		assertEquals(success, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getCategoryPojoById(com.kohler.entity.CategoryMetadataEntity, java.util.List)}
	 * .
	 */
	@Test
	public void testGetCategoryPojoById() {
		CategoryMetadataEntity pareCategory = new CategoryMetadataEntity();
		List<LocaleEntity> languages = new ArrayList<LocaleEntity>();
		List<CategoryPojo> pojos = catalogService.getCategoryPojoById(
				pareCategory, languages);
		for (CategoryPojo pojo : pojos) {
			assertEquals(pojo, null);
		}
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#checkCatalogName(java.lang.String, java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testCheckCatalogName() {
		String catalogName = "test";
		Integer lang = 1;
		Integer categoryMetadataId = 2;
		int count = catalogService.checkCatalogName(catalogName, lang,
				categoryMetadataId);
		assertEquals(count, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#checkCatalogNameOutSelf(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testCheckCatalogNameOutSelf() {
		String catalogName = "test";
		Integer lang = 1;
		Integer categoryMetadataId = 2;
		Integer categoryId = 2;
		int count = catalogService.checkCatalogNameOutSelf(catalogName, lang,
				categoryMetadataId, categoryId);
		assertEquals(count, null);
	}

	/**
	 * Test method for
	 * {@link com.kohler.service.impl.CatalogServiceImpl#getTemplateListByType(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetTemplateListByType() {
		Integer templateType = 1001001;
		List<TemplateEntity> templates = catalogService
				.getTemplateListByType(templateType);
		for (TemplateEntity template : templates) {
			assertEquals(template, null);
		}
	}

}
