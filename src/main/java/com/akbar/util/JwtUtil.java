package com.akbar.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // 生成令牌
    public static String generateJwt(String secretKey, Long ttlMillis, Map<String, Object> claims) {
        long expMills = System.currentTimeMillis() + ttlMillis;
        Date expirationTime = new Date(expMills);


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(expirationTime)
                .compact();
    }

    // 解析令牌
    public static Claims parseJwt(String secretKey,String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
