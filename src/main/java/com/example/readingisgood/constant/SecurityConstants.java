package com.example.readingisgood.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityConstants {

    public static final long EXPIRATION_TIME_IN_MILLIS = 3600000;

    public final String BEARER_TOKEN_PREFIX = "Bearer ";
    public final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public final String SECRET_KEY = "SECRET";

    public final String ADMIN_USERNAME = "admin";
}
