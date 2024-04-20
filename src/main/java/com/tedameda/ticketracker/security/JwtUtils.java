package com.tedameda.ticketracker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author TedaMeda
 * @since 4/3/2024
 */

@Slf4j
public class JwtUtils {
    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private static final String ISSUER = "TedaMeda_TicketrackerApp_Server";
    private JwtUtils() {

    }

    public static boolean validateToket(String jwt) {
        return parseToken(jwt) != null;
    }

    private static Claims parseToken(String jwt) {
        JwtParser jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

        try {
            return jwtParser.parseSignedClaims(jwt)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT exception occurred");
        }

        return null;
    }

    public static String getUserEmail(String jwt) {
        var claims = parseToken(jwt);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    public static String generateJWTToken(String userEmail) {
        var curDate = new Date();
        var expirationTimeMillis = curDate.getTime() + (6* 60 * 60 * 1000); // 6 hours in milliseconds
        var expirationDate = new Date(expirationTimeMillis);
        var jwt = Jwts.builder()
                .id(userEmail)
                .issuer(ISSUER)
                .subject(userEmail)
                .signWith(secretKey)
                .issuedAt(curDate)
                .expiration(expirationDate)
                .compact();
        return jwt;
    }
}
