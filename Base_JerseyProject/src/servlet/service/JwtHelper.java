package servlet.service;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.*;

public class JwtHelper {
	public String getToken(String userId, String username) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
//		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(new PropertiesLibrary().readProp().getProperty("HS256Key"));
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("MIICXAIBAAKBgQCiiNQmUIuI+et66bkkiR22qmq4mC1xvC7+TsyPNpd19H1ufE1XNqVWne0YKE0vGnnO/uY9gJNjmmZSSxPrqiCj+xOJfqusAYOFuybWdF5lkQvIDxQhEBCQThaKqAEYtva6uIh0nT4ky/jPBrbRodIXY5+8IH1htTT4yFhCtYpsOwIDAQABAoGATH8zwli0Y6KkxvPL9LsoJfntQrY05UvZnk1+D4V1IcoRYvfT+tE4Xhz0IyOMuDewztC/koC6Xe2kbOTzGj63H1H25pPcbkaizz8MusiaH5Cqb2N/98er1HAfCb8PpcfUstDq7sqDyJwUY3y5B3Ha/kkTFnV7VvDC+zzVSo/gtjkCQQD+KLEAoijENTuG0Yh5cE48WAylEEbXj7OU2FbEnEQWNATkAMYhxFVaeNvruyeOqvOmIywLpgbcgv2d+auj6PHVAkEAo7Y68ZeGe+rBEmnBkMEn6kbbpFEHGXU7zcWrU5FGqxTN3nEXs1GdUKzP9tFNT8LOFpALtxuOoohu+qjCkMLdzwJBAPdqOVS6hyDqCTanB0ngj+iidjzBaZUyqMj/CQiAbq+GmDQ0pKv+anCWMtB58nop5OIYXyo/x3ImeimtAO+hNrkCQBjlyvsZD1PjNO/G87A/IAHqOtc3l3vQr4Kw8EhqTLQSGukci/0yiVlOnuYrmLVrrAoUaCO15jBjzXUDm2uGp1MCQCKqu+qDLV60ZwfbGrVS9aj98zaa9U4ECkYzewfnm2FQXFoaxKivwKRBdit04Oz4za1kfPtUjWEcMe04trHRPsY=");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		String id = UUID.randomUUID().toString();
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).claim("userId", userId)
				.claim("username", username).signWith(signatureAlgorithm, signingKey);
//	    set time expire.
//		long ttlMillis = 86400000 * 30;
//		long expMillis = nowMillis + ttlMillis;
//		Date exp = new Date(expMillis);
//		builder.setExpiration(exp);
		return builder.compact();
	}

	public String verifyToken(String token) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("MIICXAIBAAKBgQCiiNQmUIuI+et66bkkiR22qmq4mC1xvC7+TsyPNpd19H1ufE1XNqVWne0YKE0vGnnO/uY9gJNjmmZSSxPrqiCj+xOJfqusAYOFuybWdF5lkQvIDxQhEBCQThaKqAEYtva6uIh0nT4ky/jPBrbRodIXY5+8IH1htTT4yFhCtYpsOwIDAQABAoGATH8zwli0Y6KkxvPL9LsoJfntQrY05UvZnk1+D4V1IcoRYvfT+tE4Xhz0IyOMuDewztC/koC6Xe2kbOTzGj63H1H25pPcbkaizz8MusiaH5Cqb2N/98er1HAfCb8PpcfUstDq7sqDyJwUY3y5B3Ha/kkTFnV7VvDC+zzVSo/gtjkCQQD+KLEAoijENTuG0Yh5cE48WAylEEbXj7OU2FbEnEQWNATkAMYhxFVaeNvruyeOqvOmIywLpgbcgv2d+auj6PHVAkEAo7Y68ZeGe+rBEmnBkMEn6kbbpFEHGXU7zcWrU5FGqxTN3nEXs1GdUKzP9tFNT8LOFpALtxuOoohu+qjCkMLdzwJBAPdqOVS6hyDqCTanB0ngj+iidjzBaZUyqMj/CQiAbq+GmDQ0pKv+anCWMtB58nop5OIYXyo/x3ImeimtAO+hNrkCQBjlyvsZD1PjNO/G87A/IAHqOtc3l3vQr4Kw8EhqTLQSGukci/0yiVlOnuYrmLVrrAoUaCO15jBjzXUDm2uGp1MCQCKqu+qDLV60ZwfbGrVS9aj98zaa9U4ECkYzewfnm2FQXFoaxKivwKRBdit04Oz4za1kfPtUjWEcMe04trHRPsY=");
		try {
			Claims claims = Jwts.parser()
		            .setSigningKey(apiKeySecretBytes)
		            .parseClaimsJws(token)
		            .getBody();		
			return claims.get("userId").toString() + claims.get("username").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		JwtHelper jwtLibrary = new JwtHelper();
		String token = jwtLibrary.getToken("123445555", "username1");
		System.out.println(token);
		String result = jwtLibrary.verifyToken(token);
		System.out.println(result);
	}
}