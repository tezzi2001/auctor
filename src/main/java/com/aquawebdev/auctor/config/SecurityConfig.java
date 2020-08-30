package com.aquawebdev.auctor.config;

import com.aquawebdev.auctor.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SignService signService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/signUp", "/resetPassword").not().fullyAuthenticated()
                    .antMatchers("/news/**", "/article/**").hasRole("USER")
                    .antMatchers("/", "/profile", "/create").hasRole("USER")
                    .antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/signIn")
                    .failureUrl("/signIn?error=true")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/signOut")
                    .permitAll()
                    .logoutSuccessUrl("/signIn");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(signService).passwordEncoder(bCryptPasswordEncoder);
    }
}