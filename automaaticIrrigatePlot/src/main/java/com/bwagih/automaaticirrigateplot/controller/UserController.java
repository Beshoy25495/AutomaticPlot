package com.bwagih.automaaticirrigateplot.controller;

import com.bwagih.automaaticirrigateplot.commons.AbstractController;
import com.bwagih.automaaticirrigateplot.dto.PlotDto;
import com.bwagih.automaaticirrigateplot.dto.UserLoginDto;
import com.bwagih.automaaticirrigateplot.model.CustomPasswordEncoder;
import com.bwagih.automaaticirrigateplot.model.CustomUser;
import com.bwagih.automaaticirrigateplot.model.UserLoginResponse;
import com.bwagih.automaaticirrigateplot.service.MyUserDetailsService;
import com.bwagih.automaaticirrigateplot.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Base64;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController extends AbstractController {

    static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDetailsService userDetailsService; //for jwt
    @Autowired
    private MyUserDetailsService myUserDetailsService;


    // this interface used to make auth , should to create it as a bean to use it
    @Autowired
    private AuthenticationManager authenticationManager; //for jwt

    @Autowired
    private JwtTokenUtil jwtTokenUtil; //for jwt

    CustomPasswordEncoder encoder = new CustomPasswordEncoder();

    @PostMapping({"/", ""})
    public ResponseEntity<UserLoginDto> register(@RequestBody UserLoginDto userLoginDto,
                                                 @RequestHeader Map<String, String> headers,
                                                 HttpServletRequest request) {

        logger.info("call register in user Service startup");
        userLoginDto.setPlainPassword(encoder.encode(userLoginDto.getPlainPassword()));
        userLoginDto = myUserDetailsService.register(userLoginDto, headers, request);
        if (userLoginDto.getReplyCode().equals("0")) {
            final String token = jwtTokenUtil.generateToken(userLoginDto);
            userLoginDto.setPlainPassword("");
            userLoginDto.setToken(token);

            return handleResponses(userLoginDto, HttpStatus.CREATED);
        }
        return handleResponses(userLoginDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    public ResponseEntity<UserLoginDto> update(@RequestBody UserLoginDto userLoginDto,
                                               @RequestHeader Map<String, String> headers,
                                               HttpServletRequest request) {
        logger.info("call update in user Service startup");
        userLoginDto.setPlainPassword(encoder.encode(userLoginDto.getPlainPassword()));
        userLoginDto = myUserDetailsService.update(userLoginDto, headers, request);
        if (userLoginDto.getReplyCode().equals("0"))
            return handleResponses(userLoginDto, HttpStatus.OK);
        else if (userLoginDto.getReplyCode().equals("-1"))
            return handleResponses(userLoginDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(userLoginDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/delete")
    public ResponseEntity<UserLoginDto> delete(@RequestBody UserLoginDto userLoginDto,
                                               @RequestHeader Map<String, String> headers,
                                               HttpServletRequest request) {

        logger.info("call delete in user Service startup");
        userLoginDto = myUserDetailsService.delete(userLoginDto, headers, request);
        if (userLoginDto.getReplyCode().equals("0"))
            return handleResponses(userLoginDto, HttpStatus.OK);
        else if (userLoginDto.getReplyCode().equals("-1"))
            return handleResponses(userLoginDto, HttpStatus.SERVICE_UNAVAILABLE);
        else
            return handleResponses(userLoginDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //////////////////////////////////////////////
    //////////////////////////////////////////////

//    @PreAuthorize("hasAnyRole('SUPERVISOR' , 'TRADER')")
//    @RequestMapping(method = RequestMethod.GET, value = "/getUserData")
//    public ResponseEntity<CustomUser> getUserData(@RequestHeader Map<String, String> headers) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        CustomUser user = (CustomUser) auth.getPrincipal();
//        if (user.getFirstName() != null && user.getLastName() != null)
//            user.setFullName(user.getFirstName().concat(" ").concat(user.getLastName()));
//
//        if (headers.get("authorization") != null) {
//            String userName_pass = headers.get("authorization")
//                    .replace("Basic ", "");
//
//            byte[] decodedBytes = Base64.getDecoder().decode(userName_pass);
//            String decodedString = new String(decodedBytes);
//            user.setPass(decodedString.replace(user.getUsername() + ":", ""));
//        }
//
//        return new ResponseEntity<CustomUser>(user, HttpStatus.OK);
//    }


    //for jwt
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginByUserName(@RequestBody UserLoginDto userObj,
                                          @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request) {

        /*validation for username and password if success return 200 Ok
          (vreified) else return Invalid username or password 401 */
        authenticationManager.authenticate(
                SecurityContextHolder.getContext().getAuthentication()
//                new UsernamePasswordAuthenticationToken(
//                        userObj.getUserName(),
//                        userObj.getPlainPassword()
//                )
        );

        CustomUser userDetails = myUserDetailsService.loadUserByUsername(userObj.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);

        userObj.setToken(token);
        userObj.setFirstName(userDetails.getFirstName());
        userObj.setLastName(userDetails.getLastName());
        userObj.setEmail(userDetails.getEmail());
        userObj.setUserName(userDetails.getUsername());
//        return ResponseEntity.ok(new UserLoginResponse(token));
        return handleResponses(userObj ,  HttpStatus.OK);

    }

    @RequestMapping(value = "/getDataByUsername/{username}", method = RequestMethod.GET)
    public ResponseEntity getDataByUsername(@Valid @PathVariable String username,
                                          @RequestHeader Map<String, String> headers,
                                          HttpServletRequest request){
        UserLoginDto userObj = new UserLoginDto();
        CustomUser userDetails = myUserDetailsService.loadUserByUsername(username);
        userObj.setFirstName(userDetails.getFirstName());
        userObj.setLastName(userDetails.getLastName());
        userObj.setEmail(userDetails.getEmail());
        userObj.setUserName(userDetails.getUsername());
        return handleResponses(userObj ,  HttpStatus.OK);
    }

    @RequestMapping(value = "/loginByEmail", method = RequestMethod.POST)
    public ResponseEntity loginByEmail(@RequestBody UserLoginDto userObj) {
        logger.info("call loginByEmail in userController startup");

        /*validation for username and password if success return 200 Ok
          (vreified) else return Invalid username or password 401 */
        authenticationManager.authenticate(
                SecurityContextHolder.getContext().getAuthentication()
        );

        UserDetails userDetails = myUserDetailsService.loadUserByEmail(userObj.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new UserLoginResponse(token));

    }


}
