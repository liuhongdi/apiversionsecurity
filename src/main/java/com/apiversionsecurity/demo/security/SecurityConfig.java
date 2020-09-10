package com.apiversionsecurity.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
 public class SecurityConfig extends WebSecurityConfigurerAdapter {

     @Override
     protected void configure(HttpSecurity http) throws Exception {
                  //login和logout
                  http.formLogin()
                        .defaultSuccessUrl("/v2/home/home")
                        .failureUrl("/login-error.html")
                        .permitAll()
                        .and()
                        .logout();

                  //匹配的页面，符合限制才可访问
                  http.authorizeRequests()
                 .antMatchers("/v*/home/**").hasAnyRole("ADMIN","DEV")
                 .antMatchers("/v*/goods/**").hasAnyRole("ADMIN","USER");
                  //剩下的页面，允许访问
                 http.authorizeRequests().anyRequest().permitAll();
     }

     @Autowired
     public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         //添加两个账号用来做测试
         auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                 .withUser("lhdadmin")
                 .password(new BCryptPasswordEncoder().encode("123456"))
                 .roles("ADMIN","USER")
                 .and()
                 .withUser("lhduser")
                 .password(new BCryptPasswordEncoder().encode("123456"))
                 .roles("USER");
     }
 }