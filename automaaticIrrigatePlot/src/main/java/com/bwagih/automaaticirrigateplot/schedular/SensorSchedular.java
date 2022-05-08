package com.bwagih.automaaticirrigateplot.schedular;

import com.bwagih.automaaticirrigateplot.controller.TimeController;
import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.service.TimeService;
import com.bwagih.automaaticirrigateplot.utils.Defines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SensorSchedular {

    static Logger logger = LoggerFactory.getLogger(SensorSchedular.class);

    @Autowired
    TimeService timeService;

    @Scheduled(fixedRate = Defines.ACCESS_TOKEN_VALIDITY_SECONDS)
    public void fixedRateSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Fixed Rate scheduler:: " + strDate);
    }
}
