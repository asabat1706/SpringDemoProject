package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig{

    @Bean
    public SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/saveMsg").
                ignoringAntMatchers("/h2-console/**").
                and().
                authorizeRequests().
                mvcMatchers("/home").permitAll().
                mvcMatchers("/contact").permitAll().
                mvcMatchers("/courses").permitAll().
                mvcMatchers("/about").permitAll().
                mvcMatchers("/holidays/**").permitAll().
                mvcMatchers("/saveMsg").permitAll().
                mvcMatchers("/login").permitAll().
                mvcMatchers("/dashboard").authenticated().
                mvcMatchers("/displayMessages").hasRole("ADMIN").
                antMatchers("/h2-console/**").permitAll().
                and().
                formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll().
                and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll().
                and().httpBasic();
//      only for h2 console
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager configureUserRoles(){

        UserDetails admin = User.withDefaultPasswordEncoder().
                username("admin").
                password("admin123").
                roles("ADMIN","USER").build();

        UserDetails user = User.withDefaultPasswordEncoder().
                username("user").
                password("user123").
                roles("USER").build();

        return new InMemoryUserDetailsManager(admin,user);
    }

}
