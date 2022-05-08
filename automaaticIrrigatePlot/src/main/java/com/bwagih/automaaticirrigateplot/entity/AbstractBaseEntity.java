package com.bwagih.automaaticirrigateplot.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    @Id
    @TableGenerator(name = "de" , pkColumnName = "pk"
            , pkColumnValue = "pkv" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY
            , generator = "de")
    @Column(name = "id")
    private Long id;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBaseEntity)) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
