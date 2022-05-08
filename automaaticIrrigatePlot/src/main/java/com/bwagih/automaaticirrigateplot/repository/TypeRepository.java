package com.bwagih.automaaticirrigateplot.repository;

import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import com.bwagih.automaaticirrigateplot.entity.Users;
import com.bwagih.automaaticirrigateplot.spec.TypeSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity,Long> {

    Long countByType(String type);

    Optional<TypeEntity> findByType(String type);

    Page<TypeEntity> findAll(Specification<TypeEntity> specification , Pageable pageable);

}
