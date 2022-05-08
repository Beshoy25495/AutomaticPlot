package com.bwagih.automaaticirrigateplot.dto;


import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import com.bwagih.automaaticirrigateplot.model.ResponseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeDto extends ResponseModel {

    @NotBlank
    private String time ;

    private Long id;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<TypeDto> typeIEntityList = new ArrayList<>();

    public TimeDto() {
    }

    public TimeDto(String time, List<TypeDto> typeEntityList) {
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

    public List<TypeDto> getTypeIEntityList() {
        return typeIEntityList;
    }

    public void setTypeIEntityList(List<TypeDto> typeIEntityList) {
        this.typeIEntityList = typeIEntityList;
    }

}
