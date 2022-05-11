package oauth.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    public String encrypt(JWTEntry entry) {
        JwtBuilder builder = Jwts.builder();

        builder.setHeaderParams(getHeaderMap())
               .signWith(getSecretKey(), SignatureAlgorithm.HS256)
               .setClaims(entry.claims());

        return builder.compact();
    }

    public JWTEntry decrypt(String token) {
        JwtParserBuilder builder = Jwts.parserBuilder();
        builder.setSigningKey(getSecretKey());
        JwtParser parser =  builder.build();

        Jws<Claims> jws = parser.parseClaimsJws(token);

        JWTEntry entry = new JWTEntry();
        entry.fill(jws.getBody());
        return entry;
    }

    public void check(String token) {
        JwtParserBuilder builder = Jwts.parserBuilder();
        builder.setSigningKey(getSecretKey());
        JwtParser parser =  builder.build();

        Jws<Claims> jws = parser.parseClaimsJws(token);

        Claims claims = jws.getBody();
        if (claims.get("name") == null) {
            throw new BadCredentialsException("invalid token");
        }
    }

    private Map<String, Object> getHeaderMap() {
        final Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
        return headerMap;
    }

    private Key getSecretKey() {
        final String secretKey = "AAAAABBBBBCCCCCDDDDDEEEEEFFFFFGGGGGHHHHHIIII";
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
