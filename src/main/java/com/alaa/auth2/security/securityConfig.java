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
            "/swagger-resources/configuration/security",

    };

    private static final String[] ADMIN_APIS_LIST = {
            "/api/historique" ,
            "/api/historique/{username}",
            "/api/historique/client/{client}",
            "/api/historique/date/{date}" ,
            "/api/role/save" ,
            "/api/users" ,
            "/api/user/save",
            "/api/role/addtouser",
            "/api/reset/pass/{id}" ,
            "/api/user/delete/{id}",

    };
    private static final String[] UTILISATEUR_APIS_LIST = {
            "/api/generate/doc" ,
            "/api/historique/perso/{client}" ,
            "/api/historique/perso" ,
            "/api/historique/perso/date/{date}" ,
            "/uploadFile",
            "/downloadFile/{fileName:.+}"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable() ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;
        http.authorizeRequests().antMatchers(GET, "/login" ).permitAll();
        http.authorizeRequests().antMatchers(AUTH_LIST).permitAll();
        http.authorizeRequests().antMatchers(GET, ADMIN_APIS_LIST).hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET, UTILISATEUR_APIS_LIST).hasAnyAuthority("UTILISATEUR");
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
