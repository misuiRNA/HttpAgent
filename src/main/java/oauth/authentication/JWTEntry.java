package oauth.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Setter;

import java.util.Date;

@Setter
public class JWTEntry {
    private static final long TOKEN_DURATION_MILLIS = 60 * 1000;

    private String userName;
    private String roleName;

    private String tokenId;
    private String tokenSubject;
    private long tokenDurationMillis;

    public JWTEntry() {
        tokenDurationMillis = TOKEN_DURATION_MILLIS;
    }

    public String getUserName() {
        return userName;
    }

    public Claims claims() {
        final long currentTimeMillis = System.currentTimeMillis();

        Claims claims = Jwts.claims();
        claims.setId(tokenId)
              .setSubject(tokenSubject)
              .setIssuedAt(new Date(currentTimeMillis))
              .setExpiration(new Date(currentTimeMillis + tokenDurationMillis));

        claims.put("name", userName);
        claims.put("role", roleName);
        // TODO set more claims or fill with json object 'user'
        return claims;
    }

    public void fill(Claims claims) {
        setTokenId(claims.getId());
        setTokenSubject(claims.getSubject());

        setUserName(claims.get("name", String.class));
        setRoleName(claims.get("role", String.class));
    }

}
