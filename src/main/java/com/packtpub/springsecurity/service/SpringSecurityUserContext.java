package com.packtpub.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.packtpub.springsecurity.domain.CalendarUser;

@Component
public class SpringSecurityUserContext implements UserContext {

    private final CalendarService calendarService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SpringSecurityUserContext(CalendarService calendarService, UserDetailsService userDetailsService) {
        Assert.notNull(userDetailsService);
        Assert.notNull(calendarService);
        this.calendarService = calendarService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public CalendarUser getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }
        String email = authentication.getName();
        if (email == null) {
            return null;
        }
        CalendarUser result = calendarService.findUserByEmail(email);
        if (result == null) {
            throw new IllegalStateException(
                    "Spring Security is not in synch with CalendarUsers. Could not find user with email " + email);
        }
        return result;
    }

    @Override
    public void setCurrentUser(CalendarUser user) {
        throw new UnsupportedOperationException();
    }

}
