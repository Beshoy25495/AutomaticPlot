package com.bwagih.automaaticirrigateplot.service;

import com.bwagih.automaaticirrigateplot.dto.TimeDto;
import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import com.bwagih.automaaticirrigateplot.mapper.TimeMapper;
import com.bwagih.automaaticirrigateplot.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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


    public TimeDto create(TimeDto timeDto, Map<String, String> headers, HttpServletRequest request) {
        TimeEntity timeEntity = new TimeEntity();

        logger.info("call save in timeRepository startup");
        try {
            timeEntity = timeMapper.mapToModel(timeDto, timeEntity);
            timeEntity = timeRepository.save(timeEntity);
            timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
            timeDto.setReplyCode("0");
            timeDto.setReplyMessage("Operation Done Successfully");
        } catch (Exception e) {
            logger.error("call save in timeRepository has been error : " + e.getMessage());
            timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
            timeDto.setReplyCode("E");
            timeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return timeDto;
    }

    public TimeDto update(TimeDto timeDto, Map<String, String> headers, HttpServletRequest request) {
        TimeEntity timeEntity = new TimeEntity();

        logger.info("call update in timeRepository startup");
        try {
            timeEntity = timeMapper.mapToModel(timeDto, timeEntity);

            Optional<TimeEntity> type = timeRepository.findById(timeEntity.getId());

            if (type.isPresent()) {
                timeEntity = type.get();

                timeEntity = timeRepository.save(timeEntity);

                timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
                timeDto.setReplyCode("0");
                timeDto.setReplyMessage("Operation Done Successfully");
            } else {
                timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
                timeDto.setReplyCode("-1");
                timeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call update in timeRepository has been error : " + e.getMessage());
            timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
            timeDto.setReplyCode("E");
            timeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return timeDto;
    }

    public TimeDto delete(TimeDto timeDto, Map<String, String> headers, HttpServletRequest request) {
        TimeEntity timeEntity = new TimeEntity();

        logger.info("call delete in timeRepository startup");
        try {
            timeEntity = timeMapper.mapToModel(timeDto, timeEntity);

            Optional<TimeEntity> type = timeRepository.findById(timeEntity.getId());

            if (type.isPresent()) {
                timeEntity = type.get();

                timeRepository.deleteById(timeEntity.getId());

                timeDto = new TimeDto();
                timeDto.setReplyCode("0");
                timeDto.setReplyMessage("Operation Done Successfully");
            } else {
                timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
                timeDto.setReplyCode("-1");
                timeDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call delete in timeRepository has been error : " + e.getMessage());
            timeDto = timeMapper.mapToDTO(timeEntity, timeDto);
            timeDto.setReplyCode("E");
            timeDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return timeDto;
    }

    public TimeDto findTimeByType(TimeDto timeDto, Map<String, String> headers, HttpServletRequest request) {
        String fullUrl = "http://localhost:8081/AUTOPLOTSENSOR/time/findById";
        ResponseEntity<TimeDto> fullResponse = null;
        MultiValueMap<String, String> header = new HttpHeaders();
        headers.forEach(header::add);

        fullResponse = new RestTemplate().exchange(
                fullUrl
                , HttpMethod.POST
                , new HttpEntity<>(timeDto, header)
                ,TimeDto.class);

        return fullResponse.getBody();
    }
}
