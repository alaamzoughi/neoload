package com.alaa.auth2.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Api
@RequiredArgsConstructor


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {



    @Autowired
    private final AuthenticationManager authenticationManager ;

    @Override
    public Authentication attemptAuthentication(@RequestBody  HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username") ;
        String password = request.getParameter("password") ;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username , password) ;
        return authenticationManager.authenticate(authenticationToken) ;


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal() ;
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()) ;
        String access_token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date((System.currentTimeMillis() + 10 * 60  * 100000)))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles" , user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm) ;

        String refresh_token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date((System.currentTimeMillis() + 30 * 60  * 1000)))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm) ;
        Map<String , String> tokens = new HashMap<>() ;
        tokens.put("access_token" , access_token);
        tokens.put("refresh_token" , refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream() , tokens);


    }
}
