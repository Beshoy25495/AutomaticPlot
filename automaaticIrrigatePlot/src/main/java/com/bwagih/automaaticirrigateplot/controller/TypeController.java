package com.bwagih.automaaticirrigateplot.controller;

import com.bwagih.automaaticirrigateplot.commons.AbstractController;
import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.dto.TypeDto;
import com.bwagih.automaaticirrigateplot.model.DefaultPageRequest;
import com.bwagih.automaaticirrigateplot.model.FilterModel;
import com.bwagih.automaaticirrigateplot.service.PlotService;
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
@RequestMapping("/type")
public class TypeController extends AbstractController {

    @Autowired
    TypeService typeService;

    static Logger logger = LoggerFactory.getLogger(TypeController.class);


    @PostMapping("/create")
    public ResponseEntity<TypeDto> create(@RequestBody TypeDto typeDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {

        logger.info("call create in type Service startup");
        typeDto = typeService.create(typeDto, headers, request);
        if (typeDto.getReplyCode().equals("0"))
            return handleResponses(typeDto, HttpStatus.CREATED);

        return handleResponses(typeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    public ResponseEntity<TypeDto> update(@RequestBody TypeDto typeDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call update in type Service startup");
        typeDto = typeService.update(typeDto, headers, request);
        if (typeDto.getReplyCode().equals("0"))
            return handleResponses(typeDto, HttpStatus.OK);
        else if (typeDto.getReplyCode().equals("-1"))
            return handleResponses(typeDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(typeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/delete")
    public ResponseEntity<TypeDto> delete(@RequestBody TypeDto typeDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call delete in type Service startup");
        typeDto = typeService.delete(typeDto, headers, request);
        if (typeDto.getReplyCode().equals("0"))
            return handleResponses(typeDto, HttpStatus.OK);
        else if (typeDto.getReplyCode().equals("-1"))
            return handleResponses(typeDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(typeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/approve")
    public ResponseEntity<TypeDto> approve(@RequestBody TypeDto typeDto, @RequestHeader Map<String, String> headers,
                                           HttpServletRequest request) {
        logger.info("call approve in type Service startup");
        typeDto = typeService.approve(typeDto, headers, request);
        if (typeDto.getReplyCode().equals("0"))
            return handleResponses(typeDto, HttpStatus.OK);
        else if (typeDto.getReplyCode().equals("-1"))
            return handleResponses(typeDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(typeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/reject")
    public ResponseEntity<TypeDto> reject(@RequestBody TypeDto typeDto, @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {
        logger.info("call approve in type Service startup");
        typeDto = typeService.reject(typeDto, headers, request);
        if (typeDto.getReplyCode().equals("0"))
            return handleResponses(typeDto, HttpStatus.OK);
        else if (typeDto.getReplyCode().equals("-1"))
            return handleResponses(typeDto, HttpStatus.NOT_FOUND);
        else
            return handleResponses(typeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/findById")
    public ResponseEntity<TypeDto> findById(@RequestBody TypeDto typeDto, @RequestHeader Map<String, String> headers,
                                            HttpServletRequest request) {
        logger.info("call findById in type Service startup");
        typeDto = typeService.findById(typeDto, headers, request);
        if (typeDto.getReplyCode().equals("0"))
            return handleResponses(typeDto, HttpStatus.OK);
        else if (typeDto.getReplyCode().equals("-1"))
            return handleResponses(typeDto, HttpStatus.NOT_FOUND);
        else
            return handleResponses(typeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/findByType")
    public ResponseEntity<TypeDto> findByType(@RequestBody TypeDto typeDto, @RequestHeader Map<String, String> headers,
                                            HttpServletRequest request) {
        logger.info("call findByType in type Service startup");
        typeDto = typeService.findByType(typeDto, headers, request);
        if (typeDto.getReplyCode().equals("0"))
            return handleResponses(typeDto, HttpStatus.OK);
        else if (typeDto.getReplyCode().equals("-1"))
            return handleResponses(typeDto, HttpStatus.NOT_FOUND);
        else
            return handleResponses(typeDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/getTypePage")
    public ResponseEntity<DefaultPageRequest<TypeDto, FilterModel>> getTypePage(
            @RequestBody DefaultPageRequest<TypeDto , FilterModel> pageRequest
            , @RequestHeader Map<String, String> headers
            , HttpServletRequest request){


        logger.info("call gettypePage in type Service startup");
        pageRequest = typeService.getGridPage(pageRequest, headers, request);
        if (pageRequest.getReplyCode().equals("0"))
            return handleResponses(pageRequest, HttpStatus.OK);

        return handleResponses(pageRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
