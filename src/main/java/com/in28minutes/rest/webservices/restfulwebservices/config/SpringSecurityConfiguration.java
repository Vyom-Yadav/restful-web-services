package com.in28minutes.rest.webservices.restfulwebservices.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // This CookieCsrfTokenRepository adds saves csrf token to a header named XSRF-TOKEN and
        // reads from a header called X-XSRF-TOKEN, the web controller has to manually read this and
        // inject this in the request or form for our application to be safe.
    }
}
