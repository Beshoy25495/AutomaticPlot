package com.bwagih.automaaticirrigateplot.mapper;

import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import org.springframework.stereotype.Component;

@Component
public class TimeMapper<D extends TimeDto, M extends TimeEntity> {

//     List<D> map(List<M> DtoRequests);

    public D mapToDTO(M model, D dto) {
        dto.setTime(model.getTime());
        dto.setId(model.getId());
        dto.setTypeIEntityList(model.getTypeEntityList());

        return dto;
    }

    public M mapToModel(D dto, M model) {
        model.setTime(dto.getTime());
        model.setId(dto.getId());
        model.setTypeEntityList(dto.getTypeIEntityList());

        return model;
    }

}
