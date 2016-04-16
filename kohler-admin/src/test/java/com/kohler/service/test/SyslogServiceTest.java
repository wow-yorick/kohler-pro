package com.kohler.service.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;






import com.kohler.dao.utils.Pager;

import com.kohler.service.SysLogService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class SyslogServiceTest {
    /**
     * @throws java.lang.Exception
     */
    @Autowired
    public SysLogService SyslogService;

    
    
    @Test
    public void testGetbyRoleList() {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("sdate", "2014-10-01");
        map.put("edate", "2014-10-31");
        Pager pager = new Pager();
        pager.setCurrentPage(1);
        pager.setPageSize(10);
        pager.setUrl("/");
        Pager<Map<String, Object>> page = SyslogService.GetbyRoleList(pager, map);
       
        Assert.assertNotNull(page);
    }

    @Test
    public void testDeleteSyslogs() {
        
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("type", "1");
        map.put("ids","1,2,3");
        map.put("sdate", "2014-10-01");
        map.put("edate", "2014-10-31");
        Integer i=SyslogService.deleteSyslogs(map);
        Assert.assertNotNull(i);
    }

}
