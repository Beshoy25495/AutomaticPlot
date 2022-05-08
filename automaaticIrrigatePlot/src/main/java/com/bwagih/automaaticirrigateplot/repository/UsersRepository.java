package com.bwagih.automaaticirrigateplot.repository;

import com.bwagih.automaaticirrigateplot.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, String> {


    @Query("SELECT u FROM Users u WHERE UPPER(TRIM(u.email))=UPPER(TRIM(:email))")
    public Users findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Users u WHERE UPPER(TRIM(u.userName))=UPPER(TRIM(:username))")
    public Users findByUsername(@Param("username") String username);

    public Users deleteUsersByUserName(String username);

    Page<Users> findAll(Specification specification , Pageable pageable);

}
