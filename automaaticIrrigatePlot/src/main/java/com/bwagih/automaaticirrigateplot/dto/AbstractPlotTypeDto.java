package com.bwagih.automaaticirrigateplot.dto;

import com.bwagih.automaaticirrigateplot.model.ResponseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractPlotTypeDto extends ResponseModel{

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private LocalDateTime creationDate;

    @JsonIgnore
    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modificationDate;

    private String status;
    private Long id;

    public AbstractPlotTypeDto() {
    }

    public AbstractPlotTypeDto(String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modificationDate) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPlotTypeDto that = (AbstractPlotTypeDto) o;
        return createdBy.equals(that.createdBy) && creationDate.equals(that.creationDate) && modifiedBy.equals(that.modifiedBy) && modificationDate.equals(that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, creationDate, modifiedBy, modificationDate);
    }

}
