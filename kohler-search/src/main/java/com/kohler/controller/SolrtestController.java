/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kohler.service.SolrService;

/**
 * solr test controller
 * 
 * @author zqq
 * @Date 10/9/2014
 */
@Controller
@RequestMapping(value = "/solrtest")
public class SolrtestController extends BasicController{

    @Autowired
    public SolrService    solrService;


    @RequestMapping(value = "/unlimited/import")
    public void importAll() throws Exception {
        solrService.importAllIndex();
//        solrService.importDeltaIndex();
    }
}
