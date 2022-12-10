package devracom.ananke.ananke.config.security.jwt;

import devracom.ananke.ananke.User.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt-secret}")
    private String jwtSecret;
    @Value("${jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;
    private final UserService userService;

    public JwtTokenProvider(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(Authentication authentication) {
        String user = authentication.getName();
        Long userId = userService.getUser(user).getId();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .claim("userId", String.valueOf(userId))
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.valueOf(claims.get("userId").toString());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new SignatureException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new MalformedJwtException("Invalid JWT token");
        }
    }
}