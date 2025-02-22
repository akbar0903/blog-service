package com.akbar.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // 签名密钥
    private static final String SIGN_KEY = "akbar-blog";
    // 令牌有效期（单位：毫秒）
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7 天

    // 生成令牌
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)  // 自定义 claims
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .compact();
    }

    // 解析令牌
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
