package com.bwagih.automaaticirrigateplot.commons;

import com.bwagih.automaaticirrigateplot.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

public abstract class AbstractController {

    public AbstractController() {
    }

    //HttpStatus.CREATED , HttpStatus.OK
    public <T extends ResponseModel> ResponseEntity<T> handleResponses(T data , HttpStatus status) {
        return ResponseEntity.status(status).contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)).body(data);
    }

}
