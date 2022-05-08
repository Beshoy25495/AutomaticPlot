package com.bwagih.automaaticirrigateplot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "times")
public class TimeEntity extends AbstractBaseEntity {

    @Column(nullable = false)
    private String time ;

    @ManyToMany(cascade = CascadeType.ALL , fetch=FetchType.LAZY, targetEntity = TypeEntity.class)
    @JoinTable(name = "type_time" ,
            joinColumns = {
                    @JoinColumn(name = "time_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "type_id")
            }
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<TypeEntity> typeEntityList = new ArrayList<>();

    public TimeEntity() {
    }

    public TimeEntity(Long id, String time, List<TypeEntity> typeEntityList) {
        super(id);
        this.time = time;
        this.typeEntityList = typeEntityList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<TypeEntity> getTypeEntityList() {
        return typeEntityList;
    }

    public void setTypeEntityList(List<TypeEntity> typeEntityList) {
        this.typeEntityList = typeEntityList;
    }

    @Override
    public String toString() {
        return "TimeEntity{" +
                "time=" + time +
                ", typeEntityList=" + typeEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeEntity)) return false;
        if (!super.equals(o)) return false;
        TimeEntity that = (TimeEntity) o;
        return time.equals(that.time) && typeEntityList.equals(that.typeEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), time, typeEntityList);
    }
}
