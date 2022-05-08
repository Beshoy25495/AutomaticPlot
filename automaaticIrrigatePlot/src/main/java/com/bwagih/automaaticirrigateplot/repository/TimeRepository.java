package com.bwagih.automaaticirrigateplot.repository;

import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import com.bwagih.automaaticirrigateplot.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface TimeRepository extends JpaRepository<TimeEntity,Long> {

    List<TimeEntity> findAllByTime(LocalTime time);

    Page<Users> findAll(Specification specification , Pageable pageable);
}
