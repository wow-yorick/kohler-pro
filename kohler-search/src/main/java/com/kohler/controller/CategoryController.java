///**
// * Copyright (C) 2000-2006 
//Kohler Company. All Rights \
//Reserved.
// */
//package com.kohler.controller;
//
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.solr.client.solrj.SolrServerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.kohler.service.SolrService;
//
///**
// * category controller
// * 
// * @author zqq
// * @Date 10/9/2014
// */
//@Controller
//@RequestMapping(value = "/category")
//public class CategoryController {
//
//    @Autowired
//    public SolrService    solrService;
//
//    @RequestMapping(value = "/list")
//    public Map<String,Object> list(String collectionName,String SEOTitle,String sort,Integer page,
//            Integer perNum,Boolean isFirst) throws SolrServerException {
//        Integer start = perNum*(page-1);
//        Integer end = perNum*page-1;
//        Map<String,Object> resultMap = new HashMap<String, Object>();
//        Map<String,Object> records = solrService.searchByCondition(collectionName, SEOTitle, start, end);
//        resultMap.put("record", records);
//        if(isFirst) {
//            List<Map<String, Object>> attrs = solrService.getAttributes(collectionName);
//            resultMap.put("attrs", attrs);
//        }
//        return resultMap;
//    }
//}
