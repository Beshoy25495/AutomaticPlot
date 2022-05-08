package com.bwagih.automaaticirrigateplot.entity;

import com.bwagih.automaaticirrigateplot.utils.Defines;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "plots")
public class PlotEntity extends AbstractPlotTypeEntity {

    @Column(nullable = false,columnDefinition = "Decimal(20,2) default '0.00'")
    @DecimalMin(value = "0.1", inclusive = false)
    @DecimalMax(value = "1000000", inclusive = false)
    private BigDecimal area;

    @Column
    @Min(value = 1 , message = Defines.MIN_VALUE_MSG)
    private Double length;

    @Column
    @Min(value = 1 , message = Defines.MIN_VALUE_MSG)
    private Double width;


    @ManyToOne(cascade=CascadeType.ALL , fetch=FetchType.LAZY)
    @JoinColumn(name = "type_entity_id" , unique = false , nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private TypeEntity typeEntity = new TypeEntity();

    public PlotEntity() {
    }

    public PlotEntity(Long id, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modificationDate, BigDecimal area, Double length, Double width, TypeEntity typeEntity) {
        super(id, createdBy, creationDate, modifiedBy, modificationDate);
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

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    @Override
    public String toString() {
        return "PlotEntity{" +
                "area=" + area +
                ", length=" + length +
                ", width=" + width +
                ", typeEntity=" + typeEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlotEntity)) return false;
        if (!super.equals(o)) return false;
        PlotEntity that = (PlotEntity) o;
        return area.equals(that.area) && length.equals(that.length) && width.equals(that.width) && typeEntity.equals(that.typeEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), area, length, width, typeEntity);
    }
}
