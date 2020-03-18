package com.module.authority.app.security.utils;

import com.module.common.security.domain.PreSecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author 三多
 * @Time 2020/3/2
 */
@Slf4j
@Component
public class JwtUtil {
    /**用户名称*/
    private static final String USERNAME = Claims.SUBJECT;
    /**用户Id*/
    private static final String USER_ID ="userId";
    /**创建时间*/
    private static final String CREATED = "created";
    /**权限列表*/
    private static final String AUTHORITIES = "authorities";
    /**密钥*/
    private static final String SECRET = "secret";
    /**过期时间：有效期1小时*/
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String authTokenStart;

    /**
     * 生成token
     * @param userDetail
     * @return
     */
    public static  String generateToken(PreSecurityUser userDetail){
        Map<String,Object> claims = new HashMap<>(3);
        claims.putIfAbsent(USER_ID,userDetail.getUserId());
        claims.putIfAbsent(USERNAME,userDetail.getUsername());
        claims.putIfAbsent(CREATED, new Date());
        claims.putIfAbsent(AUTHORITIES, userDetail.getAuthorities());
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     * @param claims  数据声明
     * @return  令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    /**
     * 从令牌中获取用户名
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     *  从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        }catch (Exception ex){
            claims = null;
        }
        return claims;

    }

    /**
     * 根据请求令牌获取登录认证信息
     * @param request
     * @return 用户名
     */
    public  PreSecurityUser getUserFromToken(HttpServletRequest request){
        // 获取请求携带的令牌
        String token = getToken(request);
        if(StringUtils.isNotEmpty(token)){
            Claims claims = getClaimsFromToken(token);
            if(Objects.isNull(claims)){
                return null;
            }
            String username = claims.getSubject();
            if(StringUtils.isBlank(username)){
                return null;
            }
            if(isTokenExpired(token)){
                return null;
            }
            // 解析对应的权限以及用户id
            Object authors = claims.get(AUTHORITIES);
            Integer userId = (Integer)claims.get(USER_ID);
            Set<String> perms = new HashSet<>();
            if(authors instanceof List){
                for (Object author : (List) authors) {
                    perms.add(((Map)author).get("authority").toString());
                }
            }
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms.toArray(new String[0]));
            if(validateToken(token, username)){
                //未把密码放到jwt
                return new PreSecurityUser(null,userId,username,"",authorities);
            }
        }
        return null;
    }

    /**
     * 验证令牌
     * @param token
     * @param username
     * @return
     */
    private static boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    /**
     * 判断令牌是否失效
     * @param token 令牌
     * @return boolean
     */
    private static boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        }catch (Exception ex){
            return false;
        }
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    private  String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if(StringUtils.isNotEmpty(token)){
            token =token.substring(authTokenStart.length());
        }
        return  token;
    }

    /**
     * 刷新令牌
     * @param token
     * @return
     */
    public static String refreshToken(String token){
        String refreshedToken;
        try {
            Claims claims =getClaimsFromToken(token);
            claims.put(CREATED,new Date());
            refreshedToken = generateToken(claims);
        }catch (Exception e){
            refreshedToken = null;
        }
        return refreshedToken;

    }


}
