package com.bwagih.automaaticirrigateplot.model;

import java.io.Serializable;

public class SortModel implements Serializable {
    String sortField;
    String sortDescription;
    boolean noSort;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

    public boolean isNoSort() {
        return noSort;
    }

    public void setNoSort(boolean noSort) {
        this.noSort = noSort;
    }
}
