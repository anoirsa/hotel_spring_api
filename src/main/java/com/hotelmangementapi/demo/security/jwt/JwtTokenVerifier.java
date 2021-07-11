package com.hotelmangementapi.demo.security.jwt;

import com.google.common.base.Strings;
import com.hotelmangementapi.demo.service.securityservices.JwtTokenServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final JwtTokenServices jwtTokenServices;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain
                                                filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if(Strings.isNullOrEmpty(authorizationHeader) ||
                !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            // The token surely is not valid
            filterChain.doFilter(request,response);
            return;
        }

        try {
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(),"");




            Claims body = jwtTokenServices.getTokenClaims(token);
            Object refreshId =  body.get("refreshId");
            // Token validation
            // Testing
            System.out.println("Refresh id is  : " + refreshId);
            boolean tokenExist = jwtTokenServices.tokenValidation((String) refreshId);
           // if (tokenExist) throw new IllegalStateException("Token is already used");
            // Testing
            jwtTokenServices.saveTokenToUsed(token);
            System.out.println("Does token exist " + tokenExist);
            String username = body.getSubject();

            var authorities = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,null,simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Invalidate the token
            //  Generate new refreshed token
            String refreshedToken = jwtTokenServices.generateToken(authentication);
            response.addHeader(jwtConfig.getAuthorizationHeader(),refreshedToken);
            filterChain.doFilter(request, response);



        }

        catch (JwtException e) {
            String token = authorizationHeader.replace("Bearer ","");
            throw  new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

    }
}
