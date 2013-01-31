package com.packtpub.springsecurity.service;

import static com.packtpub.springsecurity.core.authority.CalendarUserAuthorityUtils.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.packtpub.springsecurity.core.userdetails.CalendarUserDetails;
import com.packtpub.springsecurity.domain.CalendarUser;

@Component
public class SpringSecurityUserContext implements UserContext {

    @Override
    public CalendarUser getCurrentUser() {
        Authentication auth = securityContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        CalendarUserDetails principal = (CalendarUserDetails) auth.getPrincipal();
        return principal.getCalendarUser();
    }

    private SecurityContext securityContext() {
        return SecurityContextHolder.getContext();
    }

    @Override
    public void setCurrentUser(CalendarUser user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        securityContext().setAuthentication(newAuthentication(user));
    }

    private UsernamePasswordAuthenticationToken newAuthentication(CalendarUser user) {
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), createAuthorities(user));
    }

}
