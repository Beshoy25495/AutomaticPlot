package com.bwagih.automaaticirrigateplot.utils;

import com.bwagih.automaaticirrigateplot.model.ErrorDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bwagih
 */
public final class FilterExceptionsUtil {

    public static HttpServletResponse constructResponse(HttpServletResponse response, int httpStatus, String msg)
            throws JsonProcessingException, IOException {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), msg, msg);
        response.setStatus(httpStatus);
        response.getWriter().write(convertObjectToJson(errorDetails));
        return response;

    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
