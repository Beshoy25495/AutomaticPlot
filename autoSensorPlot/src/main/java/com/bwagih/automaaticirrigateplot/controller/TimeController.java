package com.bwagih.automaaticirrigateplot.controller;

import com.bwagih.automaaticirrigateplot.commons.AbstractController;
import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.service.TimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @PostMapping("/findById")
    public TimeDto findById(@RequestBody TimeDto timeDto, @RequestHeader Map<String, String> headers,
                            HttpServletRequest request) {
        logger.info("call findById in time Service startup");
        timeDto = timeService.findById(timeDto, headers, request);

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixInAnnotations(TimeDto.class, TimeDto.class);
        return timeDto;
    }

}
