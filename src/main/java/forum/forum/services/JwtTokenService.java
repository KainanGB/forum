package forum.forum.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import forum.forum.entities.UsersEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${api.security.token.secret}")
    private String JWT_SECRET;

    public String generateToken(UsersEntity user){
        try {
            final Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                .withIssuer("API forum")
                .withSubject(user.getEmail())
                .withExpiresAt(generateExpirationTime())
                .withClaim("user_id", user.getUserId())
                .sign(algorithm);
        } catch (
            JWTCreationException exception){
            throw new RuntimeException("failed to generate jwt token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm)
                .withIssuer("API forum")
                .build().verify(token).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("jwt token is invalid or expired", exception);
        }
    }

    private Instant generateExpirationTime () {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
