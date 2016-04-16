package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.TemplateDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.TemplateEntity;
import com.kohler.service.TemplateService;
/**
 * 
 * @author ys
 *
 */
@Service
public class TemplateServiceImpl implements TemplateService{
	@Autowired
	private TemplateDao templateDao;
	@Autowired
    private MasterDataCodeDao masterDao;
	//查询数据
	@Override
	public Pager<Map<String, Object>> getAllTemplate(Pager<Map<String, Object>> pager,TemplateEntity template) {
		// TODO Auto-generated method stub
		return templateDao.select(pager, template);
	}
	//增加数据 
	@Override
	public TemplateEntity createTemplate(TemplateEntity template) {
		// TODO Auto-generated method stub
		return templateDao.createTemplate(template);
	}
	//删除数据
	@Override
	public int deleteTemplte(TemplateEntity tmplate) {
		// TODO Auto-generated method stub
		return templateDao.deleteTemplte(tmplate);
	}
	//修改数据
	@Override
	public int updateTemplte(TemplateEntity template) {
		// TODO Auto-generated method stub
	    List<Object> MasterData = new ArrayList<Object>();
        MasterData.add(template.getTemplateName());
        MasterData.add(template.getTemplateId());
        List<Map<String, Object>> selectForUP = templateDao.selectForUP(MasterData);
        if(selectForUP.size() > 0)
            return 0;
        else
            return templateDao.updateTemplte(template);
	}
	@Override
	public List<Map<String, Object>> getAllType(List<Object> template) {
		// TODO Auto-generated method stub
	    List<Object> MasterData = new ArrayList<Object>();
        MasterData.add(MasterDataCodeConstant.TYPE_TEMPLATE_TYPE);//所需数据常量
        MasterData.add(1);//所需数据语言
        return masterDao.getAllTypeByMasterData(MasterData);
	}
	@Override
	public List<Map<String, Object>> getAllFolder(List<Object> template) {
		// TODO Auto-generated method stub
		return templateDao.getAllFolder(template);
	}
	@Override
	public List<Map<String, Object>> selectOne(List<Object> template) {
		// TODO Auto-generated method stub
		return templateDao.selectOne(template);
	}
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selecForName(List<Object> template) {
        // TODO Auto-generated method stub
        return templateDao.selectForName(template);
    }

}
