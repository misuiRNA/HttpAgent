package oauth.authentication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import oauth.entity.dto.UserInfo;
import oauth.service.UserService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTToken {

    private static final String TOKEN_ID = "0001";
    private static final String TOKEN_SUBJECT = "test_subject";
    private static final String TOKEN_SECRET_KEY = "AAAAABBBBBCCCCCDDDDDEEEEEFFFFFGGGGGHHHHHIIII";
    private static final long TOKEN_DURATION_MILLIS = 5000;
    private static final Map<String, Object> TOKEN_HEADER_MAP = new HashMap<>();
    static {
        TOKEN_HEADER_MAP.put("alg", "HS256");
        TOKEN_HEADER_MAP.put("typ", "JWT");
    }

    // TODO try to remove
    private final UserService userService;

    public JWTToken(UserService userService) {
        this.userService = userService;
    }

    public String encrypt(UserInfo userInfo) {
        final long currentTimeMillis = System.currentTimeMillis();
        final Date now = new Date(currentTimeMillis);
        final Date expiration = new Date(currentTimeMillis + JWTToken.TOKEN_DURATION_MILLIS);
        final Claims claims = buildClaims(userInfo);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParams(TOKEN_HEADER_MAP)
                .setId(TOKEN_ID)
                .setSubject(TOKEN_SUBJECT)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .setClaims(claims);

        return builder.compact();
    }

    private static Claims buildClaims(UserInfo userInfo) {
        Claims claims = Jwts.claims();
        claims.put("name", userInfo.getUserName());
        return claims;
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(TOKEN_SECRET_KEY.getBytes());
    }

    public UserInfo decrypt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String userName = (String)claims.get("name");
        return userService.getUserByName(userName);
    }

}
