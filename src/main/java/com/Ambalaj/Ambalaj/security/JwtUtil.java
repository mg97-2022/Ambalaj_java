package com.Ambalaj.Ambalaj.security;

import com.Ambalaj.Ambalaj.exception.CustomException;
import com.Ambalaj.Ambalaj.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String getJwtFromRequest(@NotNull HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) return bearerToken.substring(7);

        return null;
    }

    private @NotNull SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String username) {
        return Jwts.builder().subject(username).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(getSignKey()).compact();
    }

    public String getJwtPayload(String token) {
        return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("JWT token is expired.");
        } catch (UnsupportedJwtException e) {
            throw new UnauthorizedException("JWT token is unsupported.");
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedException("JWT claims string is empty.");
        } catch (Exception e) {
            throw new UnauthorizedException("There is something wrong with this token.");
        }
    }
}
