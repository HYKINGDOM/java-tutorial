package com.java.tutorial.project.config;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

/**
 * Security配置
 * @author yihur
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * 注入Redis连接工厂
     */
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/login")
                .anyRequest();
    }


    /**
     *     @Override
     *     protected void configure(HttpSecurity http) throws Exception {
     *         LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer = http.cors().and().csrf().disable()
     *                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
     *                 .authorizeRequests()
     *                 .antMatchers(AUTH_WHITELIST).permitAll()
     *                 .anyRequest().authenticated()  // 所有请求需要身份认证
     *                 .and()
     *                 .exceptionHandling()
     *                 .authenticationEntryPoint(
     *                         new Http401AuthenticationEntryPoint("Basic realm=\"MyApp\""))
     *                 .and()
     * //                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler) // 自定义访问失败处理器
     * //                .and()
     *                 .addFilter(new JWTLoginFilter(authenticationManager()))
     *                 .addFilter(new JWTAuthenticationFilter(authenticationManager()))
     *                 .logout() // 默认注销行为为logout，可以通过下面的方式来修改
     *                 .logoutUrl("/logout")
     *                 .logoutSuccessUrl("/login")// 设置注销成功后跳转页面，默认是跳转到登录页面;
     * //                .logoutSuccessHandler(customLogoutSuccessHandler)
     *                 .permitAll();
     *     }
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .disable()
                .csrf()
                .disable();
        return httpSecurity.build();
    }

    /**
     * 初始化密码编码器，指定编码与校验规则，用MD5加密密码
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Security官方推荐的BCryptPasswordEncoder加密与校验类
        // 密钥的迭代次数（默认为10）
        //return new BCryptPasswordEncoder(10);

        return new PasswordEncoder() {
            /**
             * 加密
             * @param rawPassword 原始密码
             * @return
             */
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtil.md5Hex(rawPassword.toString());
            }

            /**
             * 校验密码
             * @param rawPassword       原始密码
             * @param encodedPassword   加密密码
             * @return
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return DigestUtil.md5Hex(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
}