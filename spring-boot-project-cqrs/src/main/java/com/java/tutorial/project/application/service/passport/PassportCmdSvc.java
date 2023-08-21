package com.java.tutorial.project.application.service.passport;

import java.util.concurrent.TimeUnit;

import com.java.tutorial.project.common.constant.TokenConstants;
import com.java.tutorial.project.common.exception.SystemException;
import com.java.tutorial.project.common.exception.TokenException;
import com.java.tutorial.project.common.kits.KV;
import com.java.tutorial.project.common.kits.ULIDKit;
import com.java.tutorial.project.common.redis.RedisCache;
import com.java.tutorial.project.domain.businessobject.passport.LoginBO;
import com.java.tutorial.project.domain.businessobject.passport.RegisterBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * APP用户登录注册
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PassportCmdSvc {

    // private final UserMapper mapper;
    private final RedisCache<Object> cache;

    /**
     * 注册
     *
     * @param bo 注册请求数据
     * @return token信息
     */
    public KV register(RegisterBO bo) {
        // Date now = new Date();
        // UserPO po = assembler.registerAs(dto);
        // mapper.insertTemplate(po);
        // Long userId = po.getId();
        // po = new UserPO();
        // po.setId(userId);
        // po.setCreateBy(userId);
        // po.setCreateAt(now);
        // po.setUpdateBy(userId);
        // po.setUpdateAt(now);
        // mapper.updateTemplateById(po);
        Long userId = 0L;
        KV kv = KV.by("accessToken", createAccessToken(userId)).set("refreshToken", createRefreshToken(userId));

        return kv;
    }

    /**
     * 登录
     *
     * @param bo 登录请求数据
     * @return token信息
     */
    public KV login(LoginBO bo) {
        // UserPO execute = null;
        if ("1".equals(bo.getType())) {
            // execute = mapper.execute(dtoAssembler.loginAssembler(dto));
        } else if ("2".equals(bo.getType())) {
            // execute = codeLoginService.execute(dtoAssembler.loginAssembler(dto));
        } else {
            throw new SystemException("不支持当前登录方式");
        }
        Long userId = 0L;
        KV kv = KV.by("accessToken", createAccessToken(userId)).set("refreshToken", createRefreshToken(userId));

        return kv;
    }

    /**
     * 刷新访问Token
     *
     * @param refreshToken 刷新token
     * @return token信息
     */
    public KV refresh(String refreshToken) {
        String userKey = String.format(TokenConstants.APP_REFRESH_TOKEN, refreshToken);
        Long userId = (Long)cache.getValue(userKey);
        if (userId == null) {
            throw new TokenException();
        }
        KV kv = KV.by("accessToken", createAccessToken(userId)).set("refreshToken", createRefreshToken(userId));
        cache.unlink(userKey);

        return kv;
    }

    /**
     * 创建访问令牌
     *
     * @param userId 用户ID
     * @return 令牌
     */
    private String createAccessToken(Long userId) {
        String ulid = ULIDKit.createToken(userId);
        String userKey = String.format(TokenConstants.APP_ACCESS_TOKEN, ulid);
        cache.putValue(userKey, userId, 30, TimeUnit.MINUTES);

        return ulid;
    }

    /**
     * 创建刷新令牌
     *
     * @param userId 用户ID
     * @return 令牌
     */
    private String createRefreshToken(Long userId) {
        String ulid = ULIDKit.createToken(userId);
        String userKey = String.format(TokenConstants.APP_REFRESH_TOKEN, ulid);
        cache.putValue(userKey, userId, 3, TimeUnit.DAYS);

        return ulid;
    }

}
