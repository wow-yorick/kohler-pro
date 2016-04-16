package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.TemplateDao;
import com.kohler.entity.TemplateEntity;
import com.kohler.service.ProductTemplateService;
/**
 * Class Function Description
 * TemplateService
 * @author zhangtingting
 * @Date 2014年9月28日
 */
@Service
public class ProductTemplateServiceImpl implements ProductTemplateService {
    
    @Autowired
    private TemplateDao templateDao;

    @Override
    public List<TemplateEntity> getAllTemplateList() {
        TemplateEntity template=new TemplateEntity();
        template.setIsEnable(true);
        return templateDao.selectByCondition(template);
    }

}
