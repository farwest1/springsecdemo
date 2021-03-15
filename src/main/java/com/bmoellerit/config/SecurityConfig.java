package com.bmoellerit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by Bernd on 28.02.2021.
 * <p>
 * Package com.bmoellerit.config
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
protected void configure(HttpSecurity http) throws Exception {
//    http.cors()
//        .and()
//        .authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/hello")
//        .hasAuthority("SCOPE_read")
//        .antMatchers(HttpMethod.POST, "/hello")
//        .hasAuthority("SCOPE_write")
//        .anyRequest()
//        .authenticated()
//        .and()
//        .oauth2ResourceServer()
//        .jwt();
//  }
      http
      .csrf().disable()
      .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/hello/**").authenticated()
        .antMatchers(HttpMethod.PUT, "/hello/**").authenticated()
        .antMatchers(HttpMethod.DELETE, "/hello/**").authenticated()
        .anyRequest().permitAll()
        .and()
      .httpBasic().and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
}
}
