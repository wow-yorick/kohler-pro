package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.dao.TemplateDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.TemplateEntity;
/**
 * 
 * @author ys
 *
 */
@Repository
public class TemplateDaoImpl extends BaseDaoImpl<TemplateEntity> implements TemplateDao {

	@Override
	public Pager<Map<String, Object>> select(Pager<Map<String, Object>> pager,TemplateEntity template) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer listSql = new StringBuffer("select tp.TEMPLATE_ID as templateId,mt.MASTERDATA_NAME as templateType," +
								" tp.TEMPLATE_NAME as templateName,tp.CREATE_USER as createUser," +
								" tp.CREATE_TIME as createTime from TEMPLATE tp " +
								" left join MASTERDATA mt on tp.TEMPLATE_TYPE = mt.MASTERDATA_METADATA_ID"
								+ " where tp.is_enable = 1 and mt.LANG = 1 ");
		StringBuffer countSql = new StringBuffer("select count(*) from TEMPLATE where is_enable = 1");
		if(template.getTemplateName() != null && !template.getTemplateName().equals("")){
			listSql.append(" and TEMPLATE_NAME like concat(concat('%',?),'%')");
			countSql.append(" and TEMPLATE_NAME like concat(concat('%',?),'%')");
			params.add(template.getTemplateName());
		}
		if(template.getTemplateType() != null && !template.getTemplateType().equals("")){
			listSql.append(" and TEMPLATE_TYPE like concat(concat('%',?),'%')");
			countSql.append(" and TEMPLATE_TYPE like concat(concat('%',?),'%')");
			params.add(template.getTemplateType());
		}
		listSql.append(" ORDER BY tp.CREATE_TIME DESC ");
		listSql.append(" LIMIT ?,?");
		int templateCount = selectCountByCondition(countSql.toString(), params);
		int pageCount = 0;
        if (templateCount % pager.getPageSize() == 0) {
            pageCount = templateCount / pager.getPageSize();
        } else {
            pageCount = templateCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        params.add(index);
        params.add(pager.getPageSize());
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), params);
		pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(templateCount);
        pager.setTotalPage(pageCount);
		return pager;
	}

	@Override
	public TemplateEntity createTemplate(TemplateEntity template) {
		// TODO Auto-generated method stub
		template.setIsEnable(false);
		Integer templateId = insert(template);
		template.setTemplateId(templateId);
		return template;
	}

	@Override
	public int deleteTemplte(TemplateEntity tmplate) {
		// TODO Auto-generated method stub
		return delete(tmplate);
	}

	@Override
	public int updateTemplte(TemplateEntity template) {
		// TODO Auto-generated method stub
		return update(template);
	}

	@Override
	public List<Map<String, Object>> getAllType(List<Object> template) {
		StringBuffer listSql = new StringBuffer("select MASTERDATA_TYPE_ID as typeId,MASTERDATA_TYPE_NAME as typeName " +
									"from MASTERDATA_TYPE where 1=?");
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), template);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllFolder(List<Object> template) {
		StringBuffer listSql = new StringBuffer("select PUBLISH_FOLDER_ID as folderId,PUBLISH_FOLDER_NAME as folderName " +
				"from PUBLISH_FOLDER  where 1=?");
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), template);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectOne(List<Object> template) {
		// TODO Auto-generated method stub
		StringBuffer listSql = new StringBuffer("select tp.TEMPLATE_ID as templateId,tp.TEMPLATE_NAME as tname, tp.REMARK as remark,tp.TEMPLATE_CONTENT as filees," +
						"tp.CREATE_USER as cuser,tp.CREATE_TIME as ctime, tp.MODIFY_TIME as mtime, tp.MODIFY_USER as muser," +
						" mt.MASTERDATA_NAME as typeName,pf.PUBLISH_FOLDER_NAME as folderName,tp.PHYSICAL_NAME as lname, " +
						" tp.TEMPLATE_TYPE as typeId,tp.PUBLISH_FOLDER_ID as folderId," +
						" tp.TEMPLATE_IMAGE as templateImage,tp.GENERATE_URL as generateUrl from TEMPLATE tp  " +
						" LEFT JOIN MASTERDATA mt on mt.MASTERDATA_METADATA_ID = tp.TEMPLATE_TYPE " +
						" left JOIN PUBLISH_FOLDER pf on pf.PUBLISH_FOLDER_ID = tp.PUBLISH_FOLDER_ID where tp.template_id=? and mt.LANG=1");
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), template);
		return list;
	}
	@Override
    public List<Map<String, Object>> selectForName(List<Object> template) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer("select * from TEMPLATE where TEMPLATE_NAME = ?");
        List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), template);
        return list;
    }
	public List<Map<String, Object>> selectForUP(List<Object> template) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer("select * from TEMPLATE where TEMPLATE_NAME = ? AND TEMPLATE_ID <> ?");
        List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), template);
        return list;
    }
}
