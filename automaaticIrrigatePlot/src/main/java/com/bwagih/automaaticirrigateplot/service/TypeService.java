package com.bwagih.automaaticirrigateplot.service;

import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import com.bwagih.automaaticirrigateplot.mapper.AbstractMapper;
import com.bwagih.automaaticirrigateplot.mapper.PlotMapper;
import com.bwagih.automaaticirrigateplot.mapper.TypeMapper;
import com.bwagih.automaaticirrigateplot.model.DefaultPageRequest;
import com.bwagih.automaaticirrigateplot.model.FilterModel;
import com.bwagih.automaaticirrigateplot.model.SortModel;
import com.bwagih.automaaticirrigateplot.repository.PlotRepository;
import com.bwagih.automaaticirrigateplot.repository.TypeRepository;
import com.bwagih.automaaticirrigateplot.spec.PlotSpec;
import com.bwagih.automaaticirrigateplot.spec.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeService {

    TypeMapper typeMapper = new TypeMapper();

    @Autowired
    TypeRepository typeRepository;
    
    static Logger logger = LoggerFactory.getLogger(TypeService.class);


    public TypeDto create(TypeDto typeDto, Map<String, String> headers, HttpServletRequest request) {
        TypeEntity typeEntity = new TypeEntity();

        logger.info("call save in typeRepository startup");
        try {
            typeEntity = typeMapper.typeMapToModel(typeDto, typeEntity);
            typeEntity = typeRepository.save(typeEntity);
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("0");
            typeDto.setReplyMessage("Operation Done Successfully");
        } catch (Exception e) {
            logger.error("call save in typeRepository has been error : " + e.getMessage());
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("E");
            typeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return typeDto;
    }

    public TypeDto update(TypeDto typeDto, Map<String, String> headers, HttpServletRequest request) {
        TypeEntity typeEntity = new TypeEntity();

        logger.info("call update in typeRepository startup");
        try {
            typeEntity = typeMapper.typeMapToModel(typeDto, typeEntity);

            Optional<TypeEntity> type = typeRepository.findById(typeEntity.getId());

            if (type.isPresent()) {
                typeEntity = type.get();

                typeEntity = typeRepository.save(typeEntity);

                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("0");
                typeDto.setReplyMessage("Operation Done Successfully");
            } else {
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("-1");
                typeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call update in typeRepository has been error : " + e.getMessage());
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("E");
            typeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return typeDto;
    }

    public TypeDto delete(TypeDto typeDto, Map<String, String> headers, HttpServletRequest request) {
        TypeEntity typeEntity = new TypeEntity();

        logger.info("call delete in typeRepository startup");
        try {
            typeEntity = typeMapper.typeMapToModel(typeDto, typeEntity);

            Optional<TypeEntity> type = typeRepository.findById(typeEntity.getId());

            if (type.isPresent()) {
                typeEntity = type.get();

                typeRepository.deleteById(typeEntity.getId());

                typeDto = new TypeDto();
                typeDto.setReplyCode("0");
                typeDto.setReplyMessage("Operation Done Successfully");
            } else {
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("-1");
                typeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call delete in typeRepository has been error : " + e.getMessage());
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("E");
            typeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return typeDto;
    }

    public TypeDto approve(TypeDto typeDto, Map<String, String> headers, HttpServletRequest request) {
        TypeEntity typeEntity = new TypeEntity();

        logger.info("call approve in typeRepository startup");
        try {
            typeEntity = typeMapper.typeMapToModel(typeDto, typeEntity);

            Optional<TypeEntity> type = typeRepository.findById(typeEntity.getId());

            if (type.isPresent()) {
                typeEntity = type.get();
                typeEntity.setStatus("A");

                typeEntity = typeRepository.save(typeEntity);

                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("0");
                typeDto.setReplyMessage("Operation Done Successfully");
            } else {
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("-1");
                typeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call approve in typeRepository has been error : " + e.getMessage());
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("E");
            typeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return typeDto;
    }

    public TypeDto reject(TypeDto typeDto, Map<String, String> headers, HttpServletRequest request) {
        TypeEntity typeEntity = new TypeEntity();

        logger.info("call reject in typeRepository startup");
        try {
            typeEntity = typeMapper.typeMapToModel(typeDto, typeEntity);

            Optional<TypeEntity> type = typeRepository.findById(typeEntity.getId());

            if (type.isPresent()) {
                typeEntity = type.get();
                typeEntity.setStatus("R");

                typeEntity = typeRepository.save(typeEntity);

                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("0");
                typeDto.setReplyMessage("Operation Done Successfully");
            } else {
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("-1");
                typeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call reject in typeRepository has been error : " + e.getMessage());
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("E");
            typeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return typeDto;
    }

    public TypeDto findById(TypeDto typeDto, Map<String, String> headers, HttpServletRequest request) {
        TypeEntity typeEntity = new TypeEntity();

        logger.info("call findById in typeRepository startup");
        try {
            typeEntity = typeMapper.typeMapToModel(typeDto, typeEntity);

            Optional<TypeEntity> type = typeRepository.findById(typeEntity.getId());

            if (type.isPresent()) {
                typeEntity = type.get();
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("0");
                typeDto.setReplyMessage("Operation Done Successfully");
            } else {
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("-1");
                typeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call findById in typeRepository has been error : " + e.getMessage());
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("E");
            typeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return typeDto;
    }

    public TypeDto findByType(TypeDto typeDto, Map<String, String> headers, HttpServletRequest request) {
        TypeEntity typeEntity = new TypeEntity();

        logger.info("call findById in typeRepository startup");
        try {
            typeEntity = typeMapper.typeMapToModel(typeDto, typeEntity);

            Optional<TypeEntity> type = typeRepository.findByType(typeEntity.getType());

            if (type.isPresent()) {
                typeEntity = type.get();
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("0");
                typeDto.setReplyMessage("Operation Done Successfully");
            } else {
                typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
                typeDto.setReplyCode("-1");
                typeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call findById in typeRepository has been error : " + e.getMessage());
            typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);
            typeDto.setReplyCode("E");
            typeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return typeDto;
    }


    //strat getGridPage
    public DefaultPageRequest<TypeDto, FilterModel> getGridPage(
            DefaultPageRequest<TypeDto, FilterModel> pageRequest
            , Map<String, String> headers, HttpServletRequest request) {

        logger.info("call getGridPage in typeRepository startup");

        Page<TypeEntity> typeEntityPage = null;
        Specification<TypeEntity> typeEntitySpecification = new TypeSpec(pageRequest.getFilterModel());
        try {
            if (pageRequest.getSortModel().isNoSort()) {
                typeEntityPage = typeRepository.findAll(
                        typeEntitySpecification
                        , PageRequest.of(pageRequest.getPageId()
                                , pageRequest.getPageSize()));
            } else {
                typeEntityPage = typeRepository.findAll(
                        typeEntitySpecification
                        , PageRequest.of(pageRequest.getPageId()
                                , pageRequest.getPageSize()
                                , constructSortObject(pageRequest.getSortModel())));
            }
            constructPageRequest(pageRequest, typeEntityPage);

            pageRequest.setReplyCode("0");
            pageRequest.setReplyMessage("Operation Done Successfully");

        } catch (Exception e) {
            logger.error("call getGridPage in typeRepository has been error : " + e.getMessage());
            pageRequest.setReplyCode("E");
            pageRequest.setReplyMessage("Opps! Error Has Occured .");
        }

        return pageRequest;
    }

    private Sort constructSortObject(SortModel sortModel) {
        return Sort.by(Sort.Direction.fromString(sortModel.getSortDescription()), sortModel.getSortField());
    }

    public void constructPageRequest(DefaultPageRequest<TypeDto, FilterModel> pageRequest, Page<TypeEntity> page) {
        pageRequest.setPageId(page.getNumber());
        pageRequest.setPageSize(page.getSize());
        pageRequest.setTotalSize((int) page.getTotalElements());
        pageRequest.setResult(page.getContent().stream().map(this::constructTypeDto).collect(Collectors.toList()));
    }

    public TypeDto constructTypeDto(TypeEntity typeEntity) {
        TypeDto typeDto = new TypeDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        typeDto = typeMapper.typeMapToDTO(typeEntity, typeDto);

        return typeDto;
    }
    //finish getGridPage


}
