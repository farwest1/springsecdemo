package com.bmoellerit.springsecdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Bernd on 05.04.2021.
 * <p>
 * Package com.bmoellerit.springsecdemo.config
 */
@Profile("secure")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    LOGGER.info("Configure Security");
//    http
//        .authorizeRequests()
//          .antMatchers("/**")
//            .hasRole("Test");
    http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .oauth2Login();
  }
}
