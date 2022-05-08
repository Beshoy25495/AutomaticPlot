package com.bwagih.automaaticirrigateplot.service;

import com.bwagih.automaaticirrigateplot.controller.UserController;
import com.bwagih.automaaticirrigateplot.dto.PlotDto;
import com.bwagih.automaaticirrigateplot.dto.UserLoginDto;
import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.entity.Role;
import com.bwagih.automaaticirrigateplot.entity.Users;
import com.bwagih.automaaticirrigateplot.mapper.UserMapper;
import com.bwagih.automaaticirrigateplot.model.CustomUser;
import com.bwagih.automaaticirrigateplot.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    UserMapper userMapper = new UserMapper();

    static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);


    @Override
    public CustomUser loadUserByUsername(@NotBlank String username) throws UsernameNotFoundException {


        Users user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Invalid username or password.");
        else
            return new CustomUser(
                    user.getUserName(),
                    user.getPassword(),
                    user.isEnabled() == 1,
                    true,
                    true,
                    true,
                    mapToGrantedAuthorities(user.getRoleList())
                    , user.getFirstName()
                    , user.getLastName()
                    , user.getEmail());
//                  return new org.springframework.security.core
//                          .userdetails.User(
//                                  user.getUserName(), user.getPassword()
//                          , mapToGrantedAuthorities(user.getRoleList())
//                  );

    }


    public CustomUser loadUserByEmail(@Email @NotBlank String email) throws UsernameNotFoundException {


        Users user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("Invalid email or password.");
        else
            return new CustomUser(
                    user.getUserName(),
                    user.getPassword(),
                    user.isEnabled() == 1,
                    true,
                    true,
                    true,
                    mapToGrantedAuthorities(user.getRoleList())
                    , user.getFirstName()
                    , user.getLastName()
                    , user.getEmail());
//                  return new org.springframework.security.core
//                          .userdetails.User(
//                                  user.getUserName(), user.getPassword()
//                          , mapToGrantedAuthorities(user.getRoleList())
//                  );

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName().name()))
                .collect(Collectors.toList());

//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//        return grantedAuthorities;
    }


    ///////////////////////////////////
    ///////////////////////////////////

    public UserLoginDto register(UserLoginDto userLoginDto,
                                 Map<String, String> headers, HttpServletRequest request) {
        Users usersEntity = new Users();

        logger.info("call save in userRepository startup");
        try {
            usersEntity = userMapper.userMapToModel(userLoginDto, usersEntity);
            usersEntity = userRepository.save(usersEntity);
            userLoginDto = userMapper.userMapToDTO(userLoginDto, usersEntity);
            userLoginDto.setReplyCode("0");
            userLoginDto.setReplyMessage("Operation Done Successfully");
        } catch (Exception e) {
            logger.error("call save in userRepository has been error : " + e.getMessage());
            userLoginDto = userMapper.userMapToDTO(userLoginDto, usersEntity);
            userLoginDto.setReplyCode("E");
            userLoginDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return userLoginDto;
    }

    public UserLoginDto update(UserLoginDto userLoginDto,
                               Map<String, String> headers, HttpServletRequest request) {
        Users usersEntity = new Users();

        logger.info("call update in userRepository startup");
        try {
            usersEntity = userMapper.userMapToModel(userLoginDto, usersEntity);

            Users plot = userRepository.findByUsername(usersEntity.getUserName());

            if (plot != null) {

                usersEntity = userRepository.save(plot);

                userLoginDto = userMapper.userMapToDTO(userLoginDto, usersEntity);
                userLoginDto.setReplyCode("0");
                userLoginDto.setReplyMessage("Operation Done Successfully");
            } else {
                userLoginDto = userMapper.userMapToDTO(userLoginDto, usersEntity);
                userLoginDto.setReplyCode("-1");
                userLoginDto.setReplyMessage("not found .");
            }

        } catch (Exception e) {
            logger.error("call update in userRepository has been error : " + e.getMessage());
            userLoginDto = userMapper.userMapToDTO(userLoginDto, usersEntity);
            userLoginDto.setReplyCode("E");
            userLoginDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return userLoginDto;
    }

    public UserLoginDto delete(UserLoginDto userLoginDto,
                               Map<String, String> headers, HttpServletRequest request) {

        Users usersEntity = new Users();

        logger.info("call delete in plotRepository startup");
        try {
            usersEntity = userMapper.userMapToModel(userLoginDto, usersEntity);

            Users plot = userRepository.findByUsername(usersEntity.getUserName());

            if (plot != null) {

                usersEntity = userRepository.deleteUsersByUserName(usersEntity.getUserName());

                userLoginDto = new UserLoginDto();
                userLoginDto.setReplyCode("0");
                userLoginDto.setReplyMessage("Operation Done Successfully");
            } else {
                userLoginDto = userMapper.userMapToDTO(userLoginDto, usersEntity);
                userLoginDto.setReplyCode("-1");
                userLoginDto.setReplyMessage("not found");
            }

        } catch (Exception e) {
            logger.error("call delete in userRepository has been error : " + e.getMessage());
            userLoginDto = userMapper.userMapToDTO(userLoginDto, usersEntity);
            userLoginDto.setReplyCode("E");
            userLoginDto.setReplyMessage("Opps! Error Has Occured .");
        }

        return userLoginDto;
    }


}
