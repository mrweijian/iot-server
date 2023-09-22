package com.iot.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.iot.common.domain.JwtTokenDto;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-23 13:34
 */
public class JWTUtils {

    private static final String JWT_PROPERTIES = "/jwt.properties";

    private static final String privateKey;

    private static final String publicKey;

    private static final String issuser;

    private static final Integer expire;


    static {
        try (InputStream resourceAsStream = JWTUtils.class.getResourceAsStream(JWT_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            privateKey = properties.getProperty("jwt.privatekey");
            publicKey = properties.getProperty("jwt.publickey");
            issuser = properties.getProperty("jwt.issuser");
            expire = Integer.valueOf(properties.getProperty("jwt.expire"));

        } catch (Exception ex) {
            throw new RuntimeException("jwt.properties配置加载失败！");
        }

    }

    public static String createToken(String msg) {
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString().replace("-", ""))
                .withIssuer(issuser)
                .withAudience("")
                .withIssuedAt(new Date())
                .withExpiresAt(getExpireDate())
                .withClaim("body", msg)
                // 签名
                .sign(getAlgorithm());
    }

    public static JwtTokenDto parseToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(getAlgorithm()).build();
        jwtVerifier.verify(token);
        DecodedJWT decodedJWT = JWT.decode(token);
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setJti(decodedJWT.getId());
        jwtTokenDto.setIss(decodedJWT.getIssuer());
        if (decodedJWT.getAudience() != null && decodedJWT.getAudience().size() > 0) {
            jwtTokenDto.setAud(decodedJWT.getAudience().get(0));
        }
        jwtTokenDto.setIat(decodedJWT.getIssuedAt());
        jwtTokenDto.setExp(decodedJWT.getExpiresAt());
        jwtTokenDto.setBody(decodedJWT.getClaim("body").asString());
        return jwtTokenDto;
    }


    private static Algorithm getAlgorithm() {
        return Algorithm.RSA256(getPublicKey(), getPrivateKey());
    }

    private static RSAPublicKey getPublicKey() {
        try {
            // base64编码的私钥
            byte[] decoded = Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8));
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        } catch (Exception ex) {
            throw new RuntimeException("PublicKey解析失败！");
        }
    }

    private static RSAPrivateKey getPrivateKey() {
        try {
            // base64编码的私钥
            byte[] decoded = Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8));
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        } catch (Exception ex) {
            throw new RuntimeException("PrivateKey解析失败！");
        }
    }

    private static Date getExpireDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, expire);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(JWTUtils.createToken("111"));
        System.out.println(JWTUtils.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIiLCJpc3MiOiJpb3QtcGVyc2lvbi1zZXJ2ZXIiLCJleHAiOjE2OTc5NjU3OTIsImJvZHkiOiJ7XCJhY2NvdW50Tm9uRXhwaXJlZFwiOnRydWUsXCJhY2NvdW50Tm9uTG9ja2VkXCI6dHJ1ZSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJlbmFibGVkXCI6dHJ1ZSxcInBhc3N3b3JkXCI6XCIxMjNcIixcInVzZXJuYW1lXCI6XCJ3ZWlqaWFuXCJ9IiwiaWF0IjoxNjkyNzgxNzkyLCJqdGkiOiI4Y2Q0MmM5YjVmMjY0OWZkYmQ5YmQ3Y2RlYzFlNjIzOSJ9.Ak6QmkVU-DOrNMWHCwXDIxNdnvr8u4JHlXHbOn8HqztxJvPtRaXVZy-6mldtqW0Cw0Tv-3OKtmzrnKBZY8gaucXKE5fuVeMxCGO9qhY7MzW5jPBQJYpXgXUhSwWc5PG2iBfq3i18Yptjqox3RjV-vEeBljzeDg81BLqkMrl7Olg"));
    }
}
