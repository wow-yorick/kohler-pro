/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.SectionSQL;
import com.kohler.dao.PageDao;
import com.kohler.dao.SectionDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.PageEntity;
import com.kohler.entity.SectionEntity;
import com.kohler.service.SectionService;

/**
 * Section Interface Impl
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
@Service
public class SectionServiceImpl implements SectionService {

	// sectionDao Interface
	@Autowired
	private SectionDao sectionDao;

	// SysLogDao Interface
	@Autowired
	private SysLogDao sysLogDao;

	// PageDao Interface
	@Autowired
	private PageDao pageDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getSectionWithMap() {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("pId", null);
		root.put("name", "ROOT");
		root.put("url", "sectionList.action?sectionId=0");
		root.put("target", "_self");
		List<Map<String, Object>> sectionTree = sectionDao.selectSections();
		sectionTree.add(root);
		return sectionTree;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pager<Map<String, Object>> getSectionPageById(
			Pager<Map<String, Object>> pager, Integer sectionId) {
		List<Object> params = new ArrayList<Object>();
		int pageCount = 0;
		int startRow = (pager.getCurrentPage() - 1) * pager.getPageSize();

		// Count All Data
		params.add(sectionId);
		params.add(sectionId);
		int totalCount = sectionDao.selectCountByCondition(
				SectionSQL.SELECT_SECTION_PAGE_COUNT_BY_ID, params);

		params.add(startRow);
		params.add(pager.getPageSize());

		List<Map<String, Object>> list = sectionDao.selectByConditionWithMap(
				SectionSQL.SELECT_SECTION_PAGE_BY_ID, params);

		pager.setStartRow(startRow);
		pager.setDatas(list);
		pager.setTotalRecord(totalCount);
		pager.setTotalPage(pageCount);
		return pager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public SectionEntity addSection(SectionEntity section) {
		// 插入section
		Integer sectionId = sectionDao.insert(section);
		section.setSectionId(sectionId);

		// 插入日志
		sysLogDao.insertLogForInsert(sectionId, "Section");

		return section;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer editSection(SectionEntity section) {
		SectionEntity newsection = sectionDao
				.selectById(section.getSectionId());
		newsection.setSectionName(section.getSectionName());
		newsection.setPublishFolderId(section.getPublishFolderId());
		newsection.setSite(section.getSite());
		newsection.setIsEnable(true);
		Integer row = sectionDao.update(newsection);
		// 插入日志
		sysLogDao.insertLogForUpdate(section.getSectionId(), "Section");
		return row;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectionEntity getSectionById(Integer sectionId) {
		return sectionDao.selectById(sectionId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteSection(List<Map<String, Object>> list) {
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> temp = list.get(i);
			String type = (String) temp.get("type");
			Integer id = (Integer) temp.get("id");
			if ("Section".equals(type)) {
				// 删除section中sectionId=？以及他的子节点的记录
				deleteSelfAndChild(id);
			} else {
				// 删除Page表中相关记录
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageId(id);
				pageDao.delete(pageEntity);
			}
		}
	}

	/**
	 * @param id
	 * @author sana Date 2014年12月4日
	 * @version
	 */
	private void deleteSelfAndChild(Integer id) {
		// 获取section
		SectionEntity section = sectionDao.selectById(id);
		if (section != null) {
			// 删除自身
			sectionDao.delete(section);
			// 插入日志
			sysLogDao.insertLogForDelete(section.getSectionId(), "Section");

			section = new SectionEntity();
			section.setParentId(id);
			// 检索自己的子
			List<SectionEntity> childrens = sectionDao
					.selectByCondition(section);
			for (SectionEntity children : childrens) {
				// 删除子
				// 递归删除子孙
				deleteSelfAndChild(children.getSectionId());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkSectionName(String sectionName, String orgSectionName) {
		List<Map<String, Object>> list = null;
		if (orgSectionName == null || "".equals(orgSectionName)) {
			list = sectionDao.selectSectionByName(sectionName);
		} else {
			list = sectionDao.selectSectionByNameExceptSelf(sectionName,
					orgSectionName);
		}
		if (list == null) {
			return 0;
		}
		return list.size();
	}

}
