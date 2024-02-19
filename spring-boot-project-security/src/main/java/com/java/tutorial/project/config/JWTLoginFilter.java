package com.java.tutorial.project.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.tutorial.project.constant.ConstantKey;
import com.java.tutorial.project.result.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义JWT登录过滤器 验证用户名密码正确后，生成一个token，并将token返回给客户端 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。 successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 *
 * @author yihur
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 尝试身份认证(接收并解析用户凭证)
     *
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
        throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 认证成功(用户成功登录后，这个方法会被调用，我们在这个方法里生成token)
     *
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
        Authentication auth) throws IOException, ServletException {
        // builder the token
        String token = null;
        try {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            // 定义存放角色集合的对象
            List roleList = new ArrayList<>();
            for (GrantedAuthority grantedAuthority : authorities) {
                roleList.add(grantedAuthority.getAuthority());
            }

            // 生成token start
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            // 设置签发时间
            calendar.setTime(new Date());
            // 设置过期时间
            calendar.add(Calendar.MINUTE, 5);// 5分钟
            Date time = calendar.getTime();
            token = Jwts.builder().setSubject(auth.getName() + "-" + roleList).setIssuedAt(now)//签发时间
                .setExpiration(time)//过期时间
                .signWith(SignatureAlgorithm.HS512, ConstantKey.SIGNING_KEY) //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
            // 生成token end

            // 登录成功后，返回token到header里面
            /*response.addHeader(ConstantKey.HEADER_KEY, ConstantKey.BEARER + token);*/

            // 登录成功后，返回token到body里面
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put(ConstantKey.HEADER_KEY, ConstantKey.BEARER + token);
            Result result = Result.ok(resultMap);
            response.getWriter().write(JSON.toJSONString(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}