package com.bwagih.automaaticirrigateplot.repository;

import com.bwagih.automaaticirrigateplot.entity.TimeEntity;
import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TimeRepository extends JpaRepository<TimeEntity,Long> {

}
