package com.packtpub.springsecurity.core.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.packtpub.springsecurity.core.authority.CalendarUserAuthorityUtils;
import com.packtpub.springsecurity.domain.AppUsernamePassword;
import com.packtpub.springsecurity.domain.CalendarUser;

public class CalendarUserDetails implements AppUsernamePassword, UserDetails {
    private static final long serialVersionUID = 1L;
    private final CalendarUser calendarUser;

    public CalendarUserDetails(CalendarUser calendarUser) {
        Assert.notNull(calendarUser, "calendarUser is null");
        this.calendarUser = calendarUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return CalendarUserAuthorityUtils.createAuthorities(getCalendarUser());
    }

    @Override
    public String getUsername() {
        return getCalendarUser().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return getCalendarUser().getPassword();
    }

    public CalendarUser getCalendarUser() {
        return calendarUser;
    }

    @Override
    public String getName() {
        return calendarUser.getName();
    }

    @Override
    public String getFirstName() {
        return calendarUser.getFirstName();
    }

    @Override
    public String getLastName() {
        return calendarUser.getLastName();
    }

}
