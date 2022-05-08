package com.bwagih.automaaticirrigateplot.mapper;

import com.bwagih.automaaticirrigateplot.dto.AbstractPlotTypeDto;
import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.entity.AbstractPlotTypeEntity;
import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeMapper<D extends TimeDto, M extends TimeEntity>{

    private ModelMapper mapper;
    private List<TypeEntity> typeEntityList;
    private List<TypeDto> typeDtoList;

    public TimeMapper() {
        this.mapper = new ModelMapper();
        this.typeEntityList = new ArrayList<>();
        this.typeDtoList = new ArrayList<>();
    }
    //     List<D> map(List<M> DtoRequests);

     public D mapToDTO(M model, D dto) {
        dto.setTime(model.getTime());

         typeEntityList = model.getTypeEntityList();


         typeEntityList.forEach(ob -> {
             typeDtoList.add(this.mapper.map(ob, TypeDto.class));
         });

        dto.setTypeIEntityList(typeDtoList);

        return dto;
    }

     public M mapToModel(D dto, M model) {
         model.setTime(dto.getTime());

         typeDtoList = dto.getTypeIEntityList();

         typeDtoList.forEach(ob -> {
             typeEntityList.add(this.mapper.map(ob, TypeEntity.class));
         });


         model.setTypeEntityList(typeEntityList);

        return model;
    }

}
