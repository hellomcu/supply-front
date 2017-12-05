package com.supply.front.auth.util;

import java.util.Date;

import com.supply.front.config.ServerConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil
{
	public static String createJwt(final String username)
	{

		// 指定JWT使用的签名算法
		// SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		//
		// // long nowMillis = System.currentTimeMillis();
		// Date now = new Date();

		// We will sign our JWT with our ApiKey secret
		// byte[] apiKeySecretBytes =
		// DatatypeConverter.parseBase64Binary(ServerConfig.JWT_KEY);
		// Key signingKey = new SecretKeySpec(apiKeySecretBytes,
		// signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setSubject(ServerConfig.SYSTEM_NAME).claim("loginUsername", username)
				.setExpiration(new Date(System.currentTimeMillis() + ServerConfig.TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, ServerConfig.JWT_KEY);

		return builder.compact();
	}

	public static Claims parseJWT(String jwt)
	{
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		Claims claims = null;
		try
		{
			claims = Jwts.parser().setSigningKey(ServerConfig.JWT_KEY).parseClaimsJws(jwt).getBody();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return claims;
	}
}
