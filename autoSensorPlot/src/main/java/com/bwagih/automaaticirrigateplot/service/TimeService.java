package com.bwagih.automaaticirrigateplot.service;

import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import com.bwagih.automaaticirrigateplot.mapper.TimeMapper;
import com.bwagih.automaaticirrigateplot.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Service
public class TimeService {

    @Autowired
    TimeMapper<TimeDto , TimeEntity> timeMapper = new TimeMapper<>();

    @Autowired
    TimeRepository timeRepository;

    static Logger logger = LoggerFactory.getLogger(TimeService.class);

    public TimeDto findById(TimeDto timeDto, Map<String, String> headers, HttpServletRequest request) {
        TimeEntity timeEntity = new TimeEntity();

        logger.info("call findById in timeRepository startup");
        try {
            timeEntity = timeMapper.mapToModel(timeDto, timeEntity);

            Optional<TimeEntity> type = timeRepository.findById(timeEntity.getId());

            if (type.isPresent()) {
                timeEntity = type.get();
                timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
                timeDto.setReplyCode("0");
                timeDto.setReplyMessage("Operation Done Successfully");
            } else {
                timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
                timeDto.setReplyCode("-1");
                timeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call findById in timeRepository has been error : " + e.getMessage());
            timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
            timeDto.setReplyCode("E");
            timeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return timeDto;
    }



}
