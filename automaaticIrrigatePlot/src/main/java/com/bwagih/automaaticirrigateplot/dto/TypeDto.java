package com.bwagih.automaaticirrigateplot.dto;


import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TypeDto extends AbstractPlotTypeDto implements Serializable {

    @NotBlank
    private String type;

//    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<TimeDto> timeEntityList = new ArrayList<>();

//    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<PlotDto> plotEntityList = new ArrayList<>();

    public TypeDto() {
    }

    public TypeDto(String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modificationDate, String type, List<TimeDto> timeEntityList, List<PlotDto> plotEntityList) {
        super(createdBy, creationDate, modifiedBy, modificationDate);
        this.type = type;
        this.timeEntityList = timeEntityList;
        this.plotEntityList = plotEntityList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TimeDto> getTimeEntityList() {
        return timeEntityList;
    }

    public void setTimeEntityList(List<TimeDto> timeEntityList) {
        this.timeEntityList = timeEntityList;
    }

    public List<PlotDto> getPlotEntityList() {
        return plotEntityList;
    }

    public void setPlotEntityList(List<PlotDto> plotEntityList) {
        this.plotEntityList = plotEntityList;
    }


}
