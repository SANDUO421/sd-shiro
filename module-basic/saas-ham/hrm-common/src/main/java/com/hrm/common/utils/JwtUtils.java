package com.hrm.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

/**
 * @author 三多
 * @Time 2020/3/24
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.config")
@Configuration
public class JwtUtils {
    /**签名私钥*/
    private String key;
    /**签名的失效时间*/
    private Long ttl;
    /**签名的失效时间*/
    private String secretKey;

    /**
     * 解析token获取claims
     */
    public  Claims parseJwt(String token) {
        Jws<Claims> parseClaimsJws = Jwts.parser()
                //私钥
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        Claims claims = parseClaimsJws.getBody();
        return claims;
    }

    /**
     * 创建token
     *      id：登录用户id
     *      subject：登录用户名
     *  步骤：
     *      1. 设置失效时间
     *      2. 创建jwtBuilder
     *      3. 根据map 设置claims
     *      4.创建token
     */
    public String createJwt(String id,String name,Map<String,Object> params) {
        //1. 设置失效时间
        Long  now  = System.currentTimeMillis();
        long  exp = now + ttl;
        //4.创建token
        return Jwts.builder().setId(id)
                //2. 创建jwtBuilder
                .setSubject(name)
                //签发时间
                .setIssuedAt(new Date())
                .setExpiration(new Date(exp))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                //3. 根据map 设置claims
                .addClaims(params)
                .compact();
    }


}
