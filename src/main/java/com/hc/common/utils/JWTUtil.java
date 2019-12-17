package com.hc.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JWTUtil {

    @Autowired
    private RedisUtil redisUtil;

    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 60 * 2 * 60 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * 生成 token, 5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public String createToken(String username) {
        try {
            String jwtid= UUID.randomUUID().toString();
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            String token = JWT.create()
                    .withClaim("username", username)
                    .withClaim("jwt-id", jwtid)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
            redisUtil.set("JWT-SESSION-"+jwtid,token,EXPIRE_TIME/1000);
            System.out.println("创建token："+token);
            return token;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确
     */
    public  boolean verify(String token, String username) {
        try {
            //1 . 根据token解密，解密出jwt-id , 先从redis中查找出redisToken，匹配是否相同
            String redisToken =  (String) redisUtil.get("JWT-SESSION-" + getJwtIdByToken(token));
            System.out.println("redisToken = " + redisToken);
            if (!redisToken.equals(token)) {
                return false;
            }
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证 token
            DecodedJWT verify = verifier.verify(token);
            Map<String, Claim> claims = verify.getClaims();
            String username1 = claims.get("username").asString();
            if(!username.equals(username1)){
                return false;
            }
            System.out.println("verify = " + username1);
            redisUtil.set("JWT-SESSION-" + getJwtIdByToken(token), redisToken, EXPIRE_TIME / 1000);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 根据Token 获取jwt-id
     */
    public String getJwtIdByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("jwt-id").asString();
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public  String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
