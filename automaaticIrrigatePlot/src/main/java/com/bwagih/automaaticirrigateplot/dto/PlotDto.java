package com.bwagih.automaaticirrigateplot.dto;

import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class PlotDto extends AbstractPlotTypeDto {

    @NotBlank
    @DecimalMin(value = "0.1", inclusive = false)
    @DecimalMax(value = "1000000", inclusive = false)
    private BigDecimal area;

    @Min(value = 1 , message = "min value 1")
    private Double length;

    @Min(value = 1 , message = "min value 1")
    private Double width;

    @NotBlank
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    private TypeDto typeEntity = new TypeDto();

    public PlotDto() {
    }

    public PlotDto(String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modificationDate) {
        super(createdBy, creationDate, modifiedBy, modificationDate);
    }

    public PlotDto(String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modificationDate, BigDecimal area, Double length, Double width, TypeDto typeEntity) {
        super(createdBy, creationDate, modifiedBy, modificationDate);
        this.area = area;
        this.length = length;
        this.width = width;
        this.typeEntity = typeEntity;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public TypeDto getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeDto typeEntity) {
        this.typeEntity = typeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlotDto)) return false;
        if (!super.equals(o)) return false;
        PlotDto plotDto = (PlotDto) o;
        return area.equals(plotDto.area) && length.equals(plotDto.length) && width.equals(plotDto.width) && typeEntity.equals(plotDto.typeEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), area, length, width, typeEntity);
    }
}
