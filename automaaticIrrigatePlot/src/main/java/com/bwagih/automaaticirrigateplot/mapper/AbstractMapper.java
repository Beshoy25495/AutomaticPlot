package com.bwagih.automaaticirrigateplot.mapper;

import com.bwagih.automaaticirrigateplot.dto.AbstractPlotTypeDto;
import com.bwagih.automaaticirrigateplot.entity.AbstractPlotTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class AbstractMapper<D extends AbstractPlotTypeDto, M extends AbstractPlotTypeEntity> {

//     List<D> map(List<M> DtoRequests);

    public D mapToDTO(M model, D dto) {
        dto.setCreatedBy(model.getCreatedBy());
        dto.setCreationDate(model.getCreationDate());
        dto.setModifiedBy(model.getModifiedBy());
        dto.setModificationDate(model.getModificationDate());
//        dto.setStatus(model.getStatus());

        return dto;
    }

    public M mapToModel(M model, D dto) {
        model.setId(dto.getId());
        model.setCreatedBy(dto.getCreatedBy());
        model.setCreationDate(dto.getCreationDate());
        model.setModifiedBy(dto.getModifiedBy());
        model.setModificationDate(dto.getModificationDate());
//        model.setStatus(dto.getStatus());

        return model;
    }

}
