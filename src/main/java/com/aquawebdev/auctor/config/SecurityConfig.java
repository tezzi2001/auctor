package com.aquawebdev.auctor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/signUp").not().fullyAuthenticated()
                    .antMatchers("/news/**", "/article/**").hasRole("USER")
                    .antMatchers("/profile", "/create").hasRole("USER")
                    .antMatchers("/", "/resetPassword").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .successForwardUrl("/article")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/signOut")
                    .permitAll()
                    .logoutSuccessUrl("/");


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}