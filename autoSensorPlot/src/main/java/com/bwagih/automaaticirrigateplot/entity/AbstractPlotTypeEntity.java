package com.bwagih.automaaticirrigateplot.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractPlotTypeEntity extends AbstractBaseEntity {

    @NotNull
    @CreatedBy
    @Column(nullable = false)
    private String createdBy;

    @NotNull
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @LastModifiedBy
    @Column
    private String modifiedBy;

    @LastModifiedDate
    @Column
    private LocalDateTime modificationDate;

    @Column(name="status", columnDefinition="default 'I'")
    private String status;

//    @PrePersist
//    public void onCreate() {
//        if(creationDate == null) {
//            creationDate = LocalDateTime.now();
//        }
//        if(createdBy ==null ){
//            createdBy= "bwagih";
//        }
//    }
//
//    @PreUpdate
//    public void onUpdate() {
//        modificationDate = LocalDateTime.now();
//        modifiedBy="bwagih";
//    }

    public AbstractPlotTypeEntity() {
    }

    public AbstractPlotTypeEntity(Long id) {
        super(id);
    }

    public AbstractPlotTypeEntity(Long id, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modificationDate) {
        super(id);
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modificationDate = modificationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AbstractPlotTypeEntity{" +
                "createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modificationDate=" + modificationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlotTypeEntity)) return false;
        if (!super.equals(o)) return false;
        AbstractPlotTypeEntity that = (AbstractPlotTypeEntity) o;
        return createdBy.equals(that.createdBy) && creationDate.equals(that.creationDate) && modifiedBy.equals(that.modifiedBy) && modificationDate.equals(that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdBy, creationDate, modifiedBy, modificationDate);
    }
}
