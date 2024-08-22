package io.github.gameplaybiel.imageliteapi.application.jwt;

import io.github.gameplaybiel.imageliteapi.domain.AcessToken;
import io.github.gameplaybiel.imageliteapi.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final SecretKeyGenerator keyGenerator;

    public AcessToken generateToken(User user){

        var key = keyGenerator.getKey();
        var expirationDate = generateExpiration();
        var claims = generateTokenClaims(user);

        String token = Jwts
                .builder()
                .signWith(key)
                .subject(user.getEmail())
                .expiration(expirationDate)
                .claims(claims)
                .compact();

        return new AcessToken("");
    }

    private Date generateExpiration(){
        var expirationMinute = 60;
        LocalDateTime now = LocalDateTime.now().plusMinutes(expirationMinute);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Map<String, Object> generateTokenClaims(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        return claims;
    }

    public String getEmailFromToken(String tokenJwt){
        try {
            JwtParser build = Jwts.parser()
                    .verifyWith(keyGenerator.getKey())
                    .build();

            Jws<Claims> claims = build.parseSignedClaims(tokenJwt);
            Claims cls = claims.getPayload();
            return cls.getSubject();
        }catch (Exception e){
            throw new InvalidTokenException(e.getMessage());
        }
    }
}
