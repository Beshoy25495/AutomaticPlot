package com.bwagih.automaaticirrigateplot.mapper;

import com.bwagih.automaaticirrigateplot.dto.PlotDto;
import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import org.modelmapper.ModelMapper;

public class PlotMapper extends AbstractMapper<PlotDto, PlotEntity>{
    private ModelMapper mapper;
    TypeEntity typeEntity;
    TypeDto typeDto;

    public PlotMapper() {
        this.mapper = new ModelMapper();
        this.typeEntity = new TypeEntity();
        this.typeDto = new TypeDto();
    }

    //     List<D> map(List<M> DtoRequests);

    public PlotDto plotMapToDTO(PlotEntity model, PlotDto dto) {
        dto = mapToDTO(model , dto);
        dto.setArea(model.getArea());
        dto.setLength(model.getLength());
        dto.setWidth(model.getWidth());

        typeEntity = model.getTypeEntity();
        typeDto = this.mapper.map(typeEntity, TypeDto.class);
        dto.setTypeEntity(typeDto);

        return dto;
    }

    public PlotEntity plotMapToModel(PlotDto dto, PlotEntity model) {
        model = mapToModel(model , dto);
        model.setArea(dto.getArea());
        model.setLength(dto.getLength());
        model.setWidth(dto.getWidth());

        typeDto = dto.getTypeEntity();
        typeEntity = this.mapper.map(typeDto, TypeEntity.class);
        model.setTypeEntity(typeEntity);

        return model;
    }

}
