package com.bwagih.automaaticirrigateplot.commons;

import com.bwagih.automaaticirrigateplot.model.CustomUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {

            Object principal = auth.getPrincipal();

            if (principal instanceof CustomUser) {
                return Optional.ofNullable(Optional.of((CustomUser) principal).get().getUsername());
            }
        }
        return Optional.empty();
    }
}
