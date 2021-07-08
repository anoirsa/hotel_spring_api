package com.hotelmangementapi.demo.security;

import com.hotelmangementapi.demo.security.jwt.JwtConfig;
import com.hotelmangementapi.demo.security.jwt.JwtTokenVerifier;
import com.hotelmangementapi.demo.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.hotelmangementapi.demo.service.securityservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JwtConfig.class)

@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers(HttpMethod.GET,"/api/v1/rooms").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/rooms").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/user/management").permitAll()
                .anyRequest()
                .authenticated();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
