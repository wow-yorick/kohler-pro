/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.utils;

import java.util.List;

/**
 * 
 * Split Page Bean 
 *
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
public class Pager<T> {

    private Integer pageSize    = 20;            // 每页显示多少条

    private Integer currentPage = 1;            // 当前页

    private Integer totalRecord;                // 总共多少条记录

    private Integer totalPage;                  // 总共多少页

    private Integer startRow    = 0;

    private String  url;                        // 请求url

    private String  sort        = "createDate"; // 排序字段

    private List<T> datas;                      // 放置具体数据的列表

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the totalRecord
     */
    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     * @param totalRecord the totalRecord to set
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * @return the totalPage
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the startRow
     */
    public Integer getStartRow() {
        return startRow;
    }

    /**
     * @param startRow the startRow to set
     */
    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return the datas
     */
    public List<T> getDatas() {
        return datas;
    }

    /**
     * @param datas the datas to set
     */
    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    /**
     * @return the pageCount
     */
    public Integer getPageCount() {
        
        if (this.getTotalRecord() % this.getPageSize() == 0) {
           return this.getTotalRecord() / this.getPageSize();
        } else {
           return this.getTotalRecord() / this.getPageSize() + 1;
        }
    }


}
