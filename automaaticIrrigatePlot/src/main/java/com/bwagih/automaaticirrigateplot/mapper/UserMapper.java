package com.bwagih.automaaticirrigateplot.mapper;

import com.bwagih.automaaticirrigateplot.dto.PlotDto;
import com.bwagih.automaaticirrigateplot.dto.UserLoginDto;
import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.entity.Users;

public class UserMapper extends AbstractMapper<PlotDto, PlotEntity>{
    //     List<D> map(List<M> DtoRequests);

    public UserLoginDto userMapToDTO(UserLoginDto dto , Users model) {
        dto.setUserName(model.getUserName());
        dto.setPlainPassword(model.getPassword());
        dto.setEmail(model.getEmail());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEnabled(model.getEnabled());

        return dto;
    }

    public Users userMapToModel(UserLoginDto dto, Users model) {

        model.setUserName(dto.getUserName());
        model.setPassword(dto.getPlainPassword());
        model.setEmail(dto.getEmail());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setEnabled(dto.getEnabled());

        return model;
    }

}
