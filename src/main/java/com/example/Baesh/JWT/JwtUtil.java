package com.example.Baesh.JWT;



import com.example.Baesh.Service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtil {
    private static final String SECRET_KEY="ADSDLJklajwdfdsgrbdasffwfsfasfasaasALKSjLAKulaksEncaseWUNBRNsad";
    private static final long EXPIRATION_TIME = 21600000;

    //JWT 토큰 생성
    public String generateToken(String username){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    //JWT 토큰 검증
    public String extractUsername(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e){
            System.out.println("Invalid JWT: " + token);
            throw e;
        }
    }

    public boolean validateToken (String token, UserDetails userDetails){
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    public String getUserName(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName ="";

            if(authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                userName = userDetails.getUsername();
            }
            return userName;
        }catch(Exception e) {
            throw  new RuntimeException("인증된 사용자 정보 없음",e);
        }
    }
}
