package br.edu.ifpe.tads.findmycar.infra.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component

public class JWTUtil {

    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {
        Date today = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
            .subject(username)
            .expiration(expirationDate) //a java.util.Date
            .issuedAt(today)
            .compact();
    }

    public boolean tokenValido(String token) {
        SecretKey testKey = Jwts.SIG.HS512.key().build();
        Jwt<?,?> jwt;
        try {
            jwt = Jwts.parser()
                    .decryptWith(testKey) // <---- or a Password from Keys.password(charArray)

                    .build()
                    .parseEncryptedClaims(token);

            System.out.println((jwt.getPayload()));
        } catch (JwtException ex) {
            return false;
        }

        return false;
    }
}
