package library;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.*;
import model.bean.User;

public class JwtLibrary {
	public String getToken(String userId, String username) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(new PropertiesLibrary().readProp().getProperty("HS256Key"));
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		String id = UUID.randomUUID().toString();
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).claim("userId", userId)
				.claim("username", username).signWith(signatureAlgorithm, signingKey);
//	    set time expire.
		long ttlMillis = 86400000 * 30;
		long expMillis = nowMillis + ttlMillis;
		Date exp = new Date(expMillis);
		builder.setExpiration(exp);
		return builder.compact();
	}

	public User verifyToken(String token) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(new PropertiesLibrary().readProp().getProperty("HS256Key"));
		try {
			Claims claims = Jwts.parser()
		            .setSigningKey(apiKeySecretBytes)
		            .parseClaimsJws(token)
		            .getBody();		
			User user = new User(claims.get("userId").toString(), claims.get("username").toString());
			if (user.getId().equals("")) {
				return null;
			} else {
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		JwtLibrary jwtLibrary = new JwtLibrary();
		String token = jwtLibrary.getToken("123445555", "username1");
		System.out.println(token);

		User user = jwtLibrary.verifyToken(token);
		System.out.println(user.getId());
		System.out.println(user.getUsername());
	}
}
