package com.bwagih.automaaticirrigateplot.mapper;

import com.bwagih.automaaticirrigateplot.dto.PlotDto;
import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class TypeMapper extends AbstractMapper<TypeDto, TypeEntity>{

    private ModelMapper mapper;

    private List<TimeEntity> timeEntityList;
    private List<TimeDto> timeDtoList;

    private List<PlotEntity> plotEntityList;
    private List<PlotDto> plotDtoList;

    public TypeMapper() {
        this.mapper = new ModelMapper();
        this.timeEntityList = new ArrayList<>();
        this.timeDtoList = new ArrayList<>();
        this.plotEntityList = new ArrayList<>();
        this.plotDtoList = new ArrayList<>();
    }

    //     List<D> map(List<M> DtoRequests);

    public TypeDto typeMapToDTO(TypeEntity model, TypeDto dto) {
        dto = mapToDTO(model , dto);
        dto.setType(model.getType());

        timeEntityList = model.getTimeEntityList();
        plotEntityList = model.getPlotEntityList();

        timeEntityList.forEach(ob -> {
            timeDtoList.add(this.mapper.map(ob, TimeDto.class));
        });

        plotEntityList.forEach(ob -> {
            plotDtoList.add( this.mapper.map(ob, PlotDto.class));
        });

        dto.setTimeEntityList(timeDtoList);
        dto.setPlotEntityList(plotDtoList);

        return dto;
    }

    public TypeEntity typeMapToModel(TypeDto dto, TypeEntity model) {
        model = mapToModel(model , dto);
        model.setType(dto.getType());


        timeDtoList = dto.getTimeEntityList();
        plotDtoList = dto.getPlotEntityList();

        timeDtoList.forEach(ob -> {
            timeEntityList.add(this.mapper.map(ob, TimeEntity.class));
        });

        plotDtoList.forEach(ob -> {
            plotEntityList.add(this.mapper.map(ob, PlotEntity.class));
        });

        model.setTimeEntityList(timeEntityList);
        model.setPlotEntityList(plotEntityList);

        return model;
    }

}
