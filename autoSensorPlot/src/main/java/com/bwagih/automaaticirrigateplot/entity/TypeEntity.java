package com.bwagih.automaaticirrigateplot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private String type;

    @ManyToMany(cascade = CascadeType.ALL , fetch=FetchType.LAZY , targetEntity = TimeEntity.class)
    @JoinTable(name = "type_time" ,
            joinColumns = {
            @JoinColumn(name = "type_id")},
            inverseJoinColumns = {
            @JoinColumn(name = "time_id")
            }
    )
    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<TimeEntity> timeEntityList = new ArrayList<>();


    public TypeEntity() {
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

}
