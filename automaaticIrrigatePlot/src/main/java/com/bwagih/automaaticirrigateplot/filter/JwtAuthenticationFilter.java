package com.bwagih.automaaticirrigateplot.filter;

import com.bwagih.automaaticirrigateplot.service.MyUserDetailsService;
import com.bwagih.automaaticirrigateplot.utils.Defines;
import com.bwagih.automaaticirrigateplot.utils.FilterExceptionsUtil;
import com.bwagih.automaaticirrigateplot.utils.JwtTokenUtil;
import com.bwagih.automaaticirrigateplot.utils.SecurityUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

//@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter   {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    SecurityUtil securityUtil;


    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,FilterChain chain)
            throws IOException, ServletException {


        final String authHeader = req.getHeader(Defines.HEADER_STRING);
        String username = null;
        String authToken = null;

        if ("OPTIONS".equals(req.getMethod())) {
            // if method type is OPTIONS break
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);

        } else {

            try{
                // if anyRequest().authenticated()
                if (authHeader != null && authHeader.startsWith(Defines.TOKEN_PREFIX)) {

                    authToken = authHeader.replace(Defines.TOKEN_PREFIX,"");
                    username = jwtTokenUtil.getUsernameFromToken(authToken);
                }
                // If request do have a username, and there is no spring security context asociated.
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtTokenUtil.validateToken(authToken, userDetails))
                    {
                        securityUtil.setAuthentication(username);
//                        UsernamePasswordAuthenticationToken authentication =
//                                new UsernamePasswordAuthenticationToken
//                                        (userDetails
//                                                , null
//                                                , Arrays.asList(new SimpleGrantedAuthority("ROLE_SUPERVISOR")));
//
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//
//                        logger.info("authenticated user " + username + ", setting security context");
//
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

                chain.doFilter(req, res);

            } catch(ExpiredJwtException ex) {
                logger.error(ex.getMessage());
                FilterExceptionsUtil.constructResponse(res, HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
            }



        }

    }


}
