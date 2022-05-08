package com.bwagih.automaaticirrigateplot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "types")
public class TypeEntity extends AbstractPlotTypeEntity {

    @Column(nullable = false, unique = true)
    private String type;

    @ManyToMany(cascade = CascadeType.ALL , fetch=FetchType.LAZY , targetEntity = TimeEntity.class)
    @JoinTable(name = "type_time" ,
            joinColumns = {
            @JoinColumn(name = "type_id")},
            inverseJoinColumns = {
            @JoinColumn(name = "time_id")
            }
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    List<TimeEntity> timeEntityList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL
            , fetch=FetchType.LAZY
            , targetEntity = PlotEntity.class
            , mappedBy = "typeEntity")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<PlotEntity> plotEntityList = new ArrayList<>();

    public TypeEntity() {
    }

    public TypeEntity(Long id, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modificationDate, String type, List<TimeEntity> timeEntityList, List<PlotEntity> plotEntityList) {
        super(id, createdBy, creationDate, modifiedBy, modificationDate);
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

    public List<TimeEntity> getTimeEntityList() {
        return timeEntityList;
    }

    public void setTimeEntityList(List<TimeEntity> timeEntityList) {
        this.timeEntityList = timeEntityList;
    }

    public List<PlotEntity> getPlotEntityList() {
        return plotEntityList;
    }

    public void setPlotEntityList(List<PlotEntity> plotEntityList) {
        this.plotEntityList = plotEntityList;
    }

    @Override
    public String toString() {
        return "TypeEntity{" +
                "type='" + type + '\'' +
                ", timeEntityList=" + timeEntityList +
                ", plotEntityList=" + plotEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeEntity)) return false;
        if (!super.equals(o)) return false;
        TypeEntity that = (TypeEntity) o;
        return type.equals(that.type) && timeEntityList.equals(that.timeEntityList) && plotEntityList.equals(that.plotEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, timeEntityList, plotEntityList);
    }
}
