package com.bwagih.automaaticirrigateplot.utils;


import java.io.Serializable;
import java.util.Optional;

import com.bwagih.automaaticirrigateplot.exception.BusinessException;
import com.bwagih.automaaticirrigateplot.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author bwagih
 * for jwt security to set && get Authentication in SecurityContext
 */
@Component
public class SecurityUtil implements Serializable {

    @Autowired
    private UserDetailsService userDetailsService;


    public void setAuthentication(String username) {
        CustomUser customUser = (CustomUser) this.userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                customUser, null, customUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


    }

    public Optional<CustomUser> getAuthentication() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {

            Object principal = auth.getPrincipal();

            if (principal instanceof CustomUser)
            {
                return Optional.of((CustomUser) principal);
            }
        }

        return Optional.empty();
    }


    public CustomUser findAuthunticatedUser() {


        Optional<CustomUser> currentCustomUser = getAuthentication();
        if (!currentCustomUser.isPresent()) {

            throw new BusinessException("NOT_AUTHENTICATED_USER");

        }
        CustomUser customUser = currentCustomUser.get();

        return customUser;


    }
}
