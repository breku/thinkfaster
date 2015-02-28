package com.kcal.configuration.security;

import com.kcal.utils.security.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * User: Breku
 * Date: 2014-09-15
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userService;

    public SecurityConfig() {
    }


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/admin/**").hasRole(RoleName.ROLE_ADMIN.getSimpleName())
                .and().authorizeRequests().antMatchers("/add/**").hasRole(RoleName.ROLE_ADMIN.getSimpleName())
                .and().authorizeRequests().antMatchers("/user/**").hasRole(RoleName.ROLE_USER.getSimpleName())
                .and().authorizeRequests().antMatchers("/food/**").hasRole(RoleName.ROLE_USER.getSimpleName())
                .and().formLogin().loginPage("/login").permitAll()
                .successHandler(authenticationSuccessHandler())
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index");
    }



    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SuccessLoginHandler();
    }
}
