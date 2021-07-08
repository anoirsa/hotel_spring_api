package com.hotelmangementapi.demo.security.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.crypto.SecretKey;


@Getter
@Setter
@ToString
@ConfigurationProperties("application.jwt")
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;


    public SecretKey getSecretKeyBytes() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
