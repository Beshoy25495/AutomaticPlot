package com.bwagih.automaaticirrigateplot.model;

import java.io.Serializable;

public class FilterModel implements Serializable {
    private String searchBy;
    private String containChars;
    private String typeOfOperator;
    private Double area;

    public FilterModel() {
    }

    public FilterModel(String searchBy, String containChars, String typeOfOperator) {
        this.searchBy = searchBy;
        this.containChars = containChars;
        this.typeOfOperator = typeOfOperator;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getContainChars() {
        return containChars;
    }

    public void setContainChars(String containChars) {
        this.containChars = containChars;
    }

    public String getTypeOfOperator() {
        return typeOfOperator;
    }

    public void setTypeOfOperator(String typeOfOperator) {
        this.typeOfOperator = typeOfOperator;
    }

    @Override
    public String toString() {
        return "FilterModel{" +
                "searchBy='" + searchBy + '\'' +
                ", containChars='" + containChars + '\'' +
                ", typeOfOperator='" + typeOfOperator + '\'' +
                '}';
    }
}
