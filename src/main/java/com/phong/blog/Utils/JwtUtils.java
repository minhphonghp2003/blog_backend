package com.phong.blog.Utils;
import com.phong.blog.User.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtils {

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Value("${jwt.secret}")
    private String secret;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractId(String token) {
        return extractClaim(token, Claims::getId);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User u) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = Jwts.claims();
        claims.put("sub",u.getCredential().getUsername());
        claims.put("roles",u.getRoles());
        claims.put("jti",u.getId());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(key).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .compact();
    }


    public Boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }
}
