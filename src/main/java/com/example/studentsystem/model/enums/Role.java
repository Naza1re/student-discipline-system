package com.example.studentsystem.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,ROLE_ADMIN,ROLE_TEACHER;

    @Override
    public String getAuthority() {
        return name();
    }
}
