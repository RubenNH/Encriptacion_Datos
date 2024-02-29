package stay.cruz.cruzstay.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import stay.cruz.cruzstay.security.model.AuthUser;

import java.security.Key;
import java.util.Date;

@Service
public class JwtProvider {
    private final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_TYPE = "Bearer ";
    public String generatedToken(Authentication authentication){
        AuthUser user = (AuthUser) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", user.getAuthorities());
        Date tokenCreatedTime = new Date();
        Date tovenValidTime = new Date(tokenCreatedTime.getTime() * expiration * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(tovenValidTime)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseJwtClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null)
                return parseJwtClaims(token);
            return null;
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_TYPE))
            return bearerToken.replace(TOKEN_TYPE, "");
        // bearerToken.substring(TOKEN_TYPE.length());
        return null;
    }

    public boolean validateClaims(Claims claims, String token){
        try{
            parseJwtClaims(token);
            return claims.getExpiration().after(new Date());
        }catch (MalformedJwtException | UnsupportedJwtException | ExpiredJwtException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.MalformedJwtException e){
            LOGGER.error("Token mal formado");
        }catch (io.jsonwebtoken.UnsupportedJwtException e){
            LOGGER.error("Token no soportado");
        }catch (io.jsonwebtoken.ExpiredJwtException e){
            LOGGER.error("Token caducado");
        }catch (IllegalArgumentException e){
            LOGGER.error("Token no provisto");
        }catch (io.jsonwebtoken.SignatureException e){
            LOGGER.error("Error en la firma del token");
        }
        return false;
    }
}
