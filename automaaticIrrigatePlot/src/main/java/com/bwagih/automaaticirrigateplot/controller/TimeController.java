package com.bwagih.automaaticirrigateplot.controller;

import com.bwagih.automaaticirrigateplot.commons.AbstractController;
import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.service.TimeService;
import com.bwagih.automaaticirrigateplot.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/time")
public class TimeController extends AbstractController {

    @Autowired
    TimeService timeService;

    static Logger logger = LoggerFactory.getLogger(TimeController.class);


    @PostMapping("/create")
    public ResponseEntity<TimeDto> create(@RequestBody TimeDto timeDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {

        logger.info("call create in time Service startup");
        timeDto = timeService.create(timeDto, headers, request);
        if (timeDto.getReplyCode().equals("0"))
            return handleResponses(timeDto, HttpStatus.CREATED);

        return handleResponses(timeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    public ResponseEntity<TimeDto> update(@RequestBody TimeDto timeDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call update in time Service startup");
        timeDto = timeService.update(timeDto, headers, request);
        if (timeDto.getReplyCode().equals("0"))
            return handleResponses(timeDto, HttpStatus.OK);
        else if (timeDto.getReplyCode().equals("-1"))
            return handleResponses(timeDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(timeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/delete")
    public ResponseEntity<TimeDto> delete(@RequestBody TimeDto timeDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call delete in time Service startup");
        timeDto = timeService.delete(timeDto, headers, request);
        if (timeDto.getReplyCode().equals("0"))
            return handleResponses(timeDto, HttpStatus.OK);
        else if (timeDto.getReplyCode().equals("-1"))
            return handleResponses(timeDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(timeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/findTimeByType")
    public ResponseEntity<TimeDto> findTimeByType(@RequestBody TimeDto timeDto, @RequestHeader Map<String, String> headers,
                                                  HttpServletRequest request) {
        logger.info("call findTimeByType in time Service startup");

        timeDto = timeService.findTimeByType(timeDto, headers, request);
        if (timeDto.getReplyCode().equals("0"))
            return handleResponses(timeDto, HttpStatus.OK);
        else if (timeDto.getReplyCode().equals("-1"))
            return handleResponses(timeDto, HttpStatus.NOT_FOUND);
        else
            return handleResponses(timeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
