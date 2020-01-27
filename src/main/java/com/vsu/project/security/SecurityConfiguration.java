package com.vsu.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsu.project.entity.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private UserDetailsService userDetailsService;
    private ObjectMapper objectMapper;

    @Autowired
    SecurityConfiguration(UserDetailsService userDetailsService, ObjectMapper objectMapper){
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/login", "/logout")
                    .permitAll()
                .antMatchers("/admin")
                    .hasRole(UserRole.Administrator.name())
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
        ;

        // Config for Login Form
        http
                .authorizeRequests()
                .and()
                .formLogin()//
                    // Submit URL of login page.
                    .loginProcessingUrl("/j_spring_security_check") // Submit URL
                    .loginPage("/login")//
                    .defaultSuccessUrl("/")//
                    .failureUrl("/login?error=true")//
                    .usernameParameter("username")//
                    .passwordParameter("password")
                // Config for Logout Page
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
