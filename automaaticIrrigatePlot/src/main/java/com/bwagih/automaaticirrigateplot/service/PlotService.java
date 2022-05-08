package com.bwagih.automaaticirrigateplot.service;

import com.bwagih.automaaticirrigateplot.controller.PlotController;
import com.bwagih.automaaticirrigateplot.dto.PlotDto;
import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.mapper.AbstractMapper;
import com.bwagih.automaaticirrigateplot.mapper.PlotMapper;
import com.bwagih.automaaticirrigateplot.model.DefaultPageRequest;
import com.bwagih.automaaticirrigateplot.model.FilterModel;
import com.bwagih.automaaticirrigateplot.model.SortModel;
import com.bwagih.automaaticirrigateplot.repository.PlotRepository;
import com.bwagih.automaaticirrigateplot.spec.PlotSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlotService {

    PlotMapper plotMapper = new PlotMapper();
    static Logger logger = LoggerFactory.getLogger(PlotService.class);
    @Autowired
    PlotRepository plotRepository;


    public PlotDto create(PlotDto plotDto, Map<String, String> headers, HttpServletRequest request) {
        PlotEntity plotEntity = new PlotEntity();

        logger.info("call save in plotRepository startup");
        try {
            plotEntity = plotMapper.plotMapToModel(plotDto, plotEntity);
            plotEntity = plotRepository.save(plotEntity);
            plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
            plotDto.setReplyCode("0");
            plotDto.setReplyMessage("Operation Done Successfully");
        } catch (Exception e) {
            logger.error("call save in plotRepository has been error : " + e.getMessage());
            plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
            plotDto.setReplyCode("E");
            plotDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return plotDto;
    }

    public PlotDto update(PlotDto plotDto, Map<String, String> headers, HttpServletRequest request) {
        PlotEntity plotEntity = new PlotEntity();

        logger.info("call update in plotRepository startup");
        try {
            plotEntity = plotMapper.plotMapToModel(plotDto, plotEntity);

            Optional<PlotEntity> plot = plotRepository.findById(plotEntity.getId());

            if (plot.isPresent()) {
                plotEntity = plot.get();

                plotEntity = plotRepository.save(plotEntity);

                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("0");
                plotDto.setReplyMessage("Operation Done Successfully");
            } else {
                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("-1");
                plotDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call update in plotRepository has been error : " + e.getMessage());
            plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
            plotDto.setReplyCode("E");
            plotDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return plotDto;
    }

    public PlotDto delete(PlotDto plotDto, Map<String, String> headers, HttpServletRequest request) {
        PlotEntity plotEntity = new PlotEntity();

        logger.info("call delete in plotRepository startup");
        try {
            plotEntity = plotMapper.plotMapToModel(plotDto, plotEntity);

            Optional<PlotEntity> plot = plotRepository.findById(plotEntity.getId());

            if (plot.isPresent()) {
                plotEntity = plot.get();

                plotRepository.deleteById(plotEntity.getId());

                plotDto = new PlotDto();
                plotDto.setReplyCode("0");
                plotDto.setReplyMessage("Operation Done Successfully");
            } else {
                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("-1");
                plotDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call delete in plotRepository has been error : " + e.getMessage());
            plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
            plotDto.setReplyCode("E");
            plotDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return plotDto;
    }

    public PlotDto approve(PlotDto plotDto, Map<String, String> headers, HttpServletRequest request) {
        PlotEntity plotEntity = new PlotEntity();

        logger.info("call approve in plotRepository startup");
        try {
            plotEntity = plotMapper.plotMapToModel(plotDto, plotEntity);

            Optional<PlotEntity> plot = plotRepository.findById(plotEntity.getId());

            if (plot.isPresent()) {
                plotEntity = plot.get();
                plotEntity.setStatus("A");

                plotEntity = plotRepository.save(plotEntity);

                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("0");
                plotDto.setReplyMessage("Operation Done Successfully");
            } else {
                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("-1");
                plotDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call approve in plotRepository has been error : " + e.getMessage());
            plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
            plotDto.setReplyCode("E");
            plotDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return plotDto;
    }

    public PlotDto reject(PlotDto plotDto, Map<String, String> headers, HttpServletRequest request) {
        PlotEntity plotEntity = new PlotEntity();

        logger.info("call reject in plotRepository startup");
        try {
            plotEntity = plotMapper.plotMapToModel(plotDto, plotEntity);

            Optional<PlotEntity> plot = plotRepository.findById(plotEntity.getId());

            if (plot.isPresent()) {
                plotEntity = plot.get();
                plotEntity.setStatus("R");

                plotEntity = plotRepository.save(plotEntity);

                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("0");
                plotDto.setReplyMessage("Operation Done Successfully");
            } else {
                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("-1");
                plotDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call reject in plotRepository has been error : " + e.getMessage());
            plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
            plotDto.setReplyCode("E");
            plotDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return plotDto;
    }

    public PlotDto findById(PlotDto plotDto, Map<String, String> headers, HttpServletRequest request) {
        PlotEntity plotEntity = new PlotEntity();

        logger.info("call findById in plotRepository startup");
        try {
            plotEntity = plotMapper.plotMapToModel(plotDto, plotEntity);

            Optional<PlotEntity> plot = plotRepository.findById(plotEntity.getId());

            if (plot.isPresent()) {
                plotEntity = plot.get();
                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("0");
                plotDto.setReplyMessage("Operation Done Successfully");
            } else {
                plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
                plotDto.setReplyCode("-1");
                plotDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call findById in plotRepository has been error : " + e.getMessage());
            plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);
            plotDto.setReplyCode("E");
            plotDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return plotDto;
    }

    //strat getGridPage
    public DefaultPageRequest<PlotDto, FilterModel> getGridPage(
            DefaultPageRequest<PlotDto, FilterModel> pageRequest
            , Map<String, String> headers, HttpServletRequest request) {

        logger.info("call getGridPage in plotRepository startup");

        Page<PlotEntity> plotEntityPage = null;
        Specification<PlotEntity> plotEntitySpecification = new PlotSpec(pageRequest.getFilterModel());
        try {
            if (pageRequest.getSortModel().isNoSort()) {
                plotEntityPage = plotRepository.findAll(
                        plotEntitySpecification
                        , PageRequest.of(pageRequest.getPageId()
                                , pageRequest.getPageSize()));
            } else {
                plotEntityPage = plotRepository.findAll(
                        plotEntitySpecification
                        , PageRequest.of(pageRequest.getPageId()
                                , pageRequest.getPageSize()
                                , constructSortObject(pageRequest.getSortModel())));
            }
            constructPageRequest(pageRequest, plotEntityPage);

            pageRequest.setReplyCode("0");
            pageRequest.setReplyMessage("Operation Done Successfully");

        } catch (Exception e) {
            logger.error("call getGridPage in plotRepository has been error : " + e.getMessage());
            pageRequest.setReplyCode("E");
            pageRequest.setReplyMessage("Opps! Error Has Occured .");
        }

        return pageRequest;
    }

    private Sort constructSortObject(SortModel sortModel) {
        return Sort.by(Sort.Direction.fromString(sortModel.getSortDescription()), sortModel.getSortField());
    }

    public void constructPageRequest(DefaultPageRequest<PlotDto, FilterModel> pageRequest, Page<PlotEntity> page) {
        pageRequest.setPageId(page.getNumber());
        pageRequest.setPageSize(page.getSize());
        pageRequest.setTotalSize((int) page.getTotalElements());
        pageRequest.setResult(page.getContent().stream().map(this::constructPlotDto).collect(Collectors.toList()));
    }

    public PlotDto constructPlotDto(PlotEntity plotEntity) {
        PlotDto plotDto = new PlotDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        plotDto = plotMapper.plotMapToDTO(plotEntity, plotDto);

        return plotDto;
    }
    //finish getGridPage


}
