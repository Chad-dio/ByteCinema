package org.chad.bytecinema.utils;

import io.jsonwebtoken.*;
import org.chad.bytecinema.domain.po.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private KeyPair keyPair;

    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpiration; // 以毫秒为单位

    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExpiration; // 以毫秒为单位

    @PostConstruct
    public void init() throws Exception {
        // 加载 RSA 密钥对
        ClassPathResource privateResource = new ClassPathResource("private.pem");
        PrivateKey privateKey = loadPrivateKey(privateResource.getInputStream());

        ClassPathResource publicResource = new ClassPathResource("public.pem");
        PublicKey publicKey = loadPublicKey(publicResource.getInputStream());

        this.keyPair = new KeyPair(publicKey, privateKey);
    }

    // 生成 AccessToken
    public String generateAccessToken(User userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    // 生成 RefreshToken
    public String generateRefreshToken(User userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    // 从令牌中获取用户名
    public String getEmailFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // 验证令牌
    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 令牌无效
            return false;
        }
    }

    // 获取 Claims
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 加载私钥
    private PrivateKey loadPrivateKey(InputStream inputStream) throws Exception {
        String privateKeyContent = new String(inputStream.readAllBytes())
                .replaceAll("\\n", "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] decoded = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    // 加载公钥
    private PublicKey loadPublicKey(InputStream inputStream) throws Exception {
        String publicKeyContent = new String(inputStream.readAllBytes())
                .replaceAll("\\n", "")
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "");
        byte[] decoded = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(keySpec);
    }
}
