package com.alaa.auth2.security;

import com.alaa.auth2.filter.CustomAuthenticationFilter;
import com.alaa.auth2.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.http.HttpMethod.GET;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableSwagger2
public class securityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService ;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder ;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder) ;
    }

    private static final String[] AUTH_LIST = {
            // -- swagger ui
            "**/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**" ,
            "/swagger-ui/" ,
            "/swagger-ui" ,
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable() ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;
        http.authorizeRequests().antMatchers(GET, "/login" ).permitAll();
        http.authorizeRequests().antMatchers(AUTH_LIST).permitAll();
        /**
        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("Role_User");
         **/
        http.authorizeRequests().anyRequest().authenticated() ;
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()) ) ;
        http.addFilterBefore(new CustomAuthorizationFilter() , UsernamePasswordAuthenticationFilter.class) ;

        }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean() ;
    }
}
