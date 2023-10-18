package br.edu.ifpe.tads.findmycar.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component

public class JWTUtil {

    @Value("${spring.jwt.secret}")
    private String secret = "superHiperMegaTopSecretMessageAquiHereComAindaMaisCaracteresAmigos";

    @Value("${spring.jwt.expiration}")
    private Long expiration = 2000000l;

    public String generateToken(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("email", email)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // Invalid claims or signing key
            throw new RuntimeException("Failed to create JWT token");
        }
    }

    public String getEmailFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("email").asString();
        } catch (JWTDecodeException exception) {
            // Invalid token or missing 'email' claim
            throw new RuntimeException("Failed to decode JWT token");
        }
    }

    public boolean isTokenValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            // Token verification failed
            return false;
        }
    }
    public static void main(String[] args) {
        JWTUtil jwtUtil = new JWTUtil();

        String token = jwtUtil.generateToken("rennanprysthon");

        String decrypted = jwtUtil.getEmailFromToken(token);

        System.out.println(decrypted);
    }
}
