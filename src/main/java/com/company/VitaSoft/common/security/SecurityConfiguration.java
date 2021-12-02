package com.company.VitaSoft.common.security;

import com.company.VitaSoft.common.encoding.PasswordEncoderNoEncoding;
import com.company.VitaSoft.common.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
      return new PasswordEncoderNoEncoding();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/users/").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/users/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/*").hasAnyAuthority("ADMIN")
                .antMatchers("/tasks/submitted").hasAnyAuthority("OPERATOR")
                .antMatchers("/tasks/submitted/*").hasAnyAuthority("OPERATOR")
                .antMatchers("/tasks/my").hasAnyAuthority("USER")
                .antMatchers("/tasks/my/*").hasAnyAuthority("USER")
                .antMatchers("/tasks/new").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl())
        ;
    }

}