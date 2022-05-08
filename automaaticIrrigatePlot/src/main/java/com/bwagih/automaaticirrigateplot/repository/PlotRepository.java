package com.bwagih.automaaticirrigateplot.repository;

import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.entity.Users;
import com.bwagih.automaaticirrigateplot.spec.TypeSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlotRepository extends JpaRepository<PlotEntity,Long> {

    Page<PlotEntity> findAll(Specification<PlotEntity> specification , Pageable pageable);

}
