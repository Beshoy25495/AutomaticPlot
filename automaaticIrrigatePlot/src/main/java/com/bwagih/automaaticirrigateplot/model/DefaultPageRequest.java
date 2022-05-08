package com.bwagih.automaaticirrigateplot.model;

import java.util.ArrayList;
import java.util.List;

public class DefaultPageRequest<T extends ResponseModel, X extends FilterModel> extends ResponseModel{
    Integer pageId;
    Integer pageSize;
    Integer totalSize;
    X filterModel;
    List<T> result ;
    SortModel sortModel;

    public DefaultPageRequest() {
        this.pageId = 0;
        this.pageSize = 10;
        this.totalSize = 1;
        this.result = new ArrayList<>();
        this.sortModel = new SortModel();
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public X getFilterModel() {
        return filterModel;
    }

    public void setFilterModel(X filterModel) {
        this.filterModel = filterModel;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public SortModel getSortModel() {
        return sortModel;
    }

    public void setSortModel(SortModel sortModel) {
        this.sortModel = sortModel;
    }

}
