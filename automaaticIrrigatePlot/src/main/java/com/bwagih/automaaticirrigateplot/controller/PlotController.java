package com.bwagih.automaaticirrigateplot.controller;

import com.bwagih.automaaticirrigateplot.commons.AbstractController;
import com.bwagih.automaaticirrigateplot.config.ApplicationStartupRunner;
import com.bwagih.automaaticirrigateplot.dto.PlotDto;
import com.bwagih.automaaticirrigateplot.model.DefaultPageRequest;
import com.bwagih.automaaticirrigateplot.model.FilterModel;
import com.bwagih.automaaticirrigateplot.service.PlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/plot")
public class PlotController extends AbstractController {
    static Logger logger = LoggerFactory.getLogger(PlotController.class);

    @Autowired
    PlotService plotService;

    @PostMapping("/create")
    public ResponseEntity<PlotDto> create(@RequestBody PlotDto plotDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {

        logger.info("call create in plot Service startup");
        plotDto = plotService.create(plotDto, headers, request);
        if (plotDto.getReplyCode().equals("0"))
            return handleResponses(plotDto, HttpStatus.CREATED);

        return handleResponses(plotDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    public ResponseEntity<PlotDto> update(@RequestBody PlotDto plotDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call update in plot Service startup");
        plotDto = plotService.update(plotDto, headers, request);
        if (plotDto.getReplyCode().equals("0"))
            return handleResponses(plotDto, HttpStatus.OK);
        else if (plotDto.getReplyCode().equals("-1"))
            return handleResponses(plotDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(plotDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/delete")
    public ResponseEntity<PlotDto> delete(@RequestBody PlotDto plotDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call delete in plot Service startup");
        plotDto = plotService.delete(plotDto, headers, request);
        if (plotDto.getReplyCode().equals("0"))
            return handleResponses(plotDto, HttpStatus.OK);
        else if (plotDto.getReplyCode().equals("-1"))
            return handleResponses(plotDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(plotDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/approve")
    public ResponseEntity<PlotDto> approve(@RequestBody PlotDto plotDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call approve in plot Service startup");
        plotDto = plotService.approve(plotDto, headers, request);
        if (plotDto.getReplyCode().equals("0"))
            return handleResponses(plotDto, HttpStatus.OK);
        else if (plotDto.getReplyCode().equals("-1"))
            return handleResponses(plotDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(plotDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/reject")
    public ResponseEntity<PlotDto> reject(@RequestBody PlotDto plotDto, @RequestHeader Map<String, String> headers,
                                           HttpServletRequest request) {
        logger.info("call approve in plot Service startup");
        plotDto = plotService.reject(plotDto, headers, request);
        if (plotDto.getReplyCode().equals("0"))
            return handleResponses(plotDto, HttpStatus.OK);
        else if (plotDto.getReplyCode().equals("-1"))
            return handleResponses(plotDto, HttpStatus.NOT_FOUND);
        else
            return handleResponses(plotDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/findById")
    public ResponseEntity<PlotDto> findById(@RequestBody PlotDto plotDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call findById in plot Service startup");
        plotDto = plotService.findById(plotDto, headers, request);
        if (plotDto.getReplyCode().equals("0"))
            return handleResponses(plotDto, HttpStatus.OK);
        else if (plotDto.getReplyCode().equals("-1"))
            return handleResponses(plotDto, HttpStatus.NOT_FOUND);
        else
            return handleResponses(plotDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/getPlotPage")
    public ResponseEntity<DefaultPageRequest<PlotDto, FilterModel>> getGridPage(
            @RequestBody DefaultPageRequest<PlotDto , FilterModel> pageRequest
                    , @RequestHeader Map<String, String> headers
                    , HttpServletRequest request){


        logger.info("call getPlotPage in plot Service startup");
        pageRequest = plotService.getGridPage(pageRequest, headers, request);
        if (pageRequest.getReplyCode().equals("0"))
            return handleResponses(pageRequest, HttpStatus.OK);

        return handleResponses(pageRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
