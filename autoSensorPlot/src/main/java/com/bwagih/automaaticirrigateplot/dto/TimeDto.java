package com.bwagih.automaaticirrigateplot.dto;


import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import com.bwagih.automaaticirrigateplot.model.ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class TimeDto extends ResponseModel {

    @NotBlank
    @JsonProperty
    private String time ;

    private Long id;

    @JsonProperty
    List<TypeEntity> typeIEntityList = new ArrayList<>();

    public TimeDto() {
        this.time = "00:00:00";
    }

    public TimeDto(String time, List<TypeEntity> typeEntityList) {
        this.time = time;
        this.typeIEntityList = typeEntityList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TypeEntity> getTypeIEntityList() {
        return typeIEntityList;
    }

    public void setTypeIEntityList(List<TypeEntity> typeIEntityList) {
        this.typeIEntityList = typeIEntityList;
    }

    @Override
    public String toString() {
        return "TimeDto{" +
                "time=" + time +
                ", typeEntityList=" + typeIEntityList +
                '}';
    }
}
