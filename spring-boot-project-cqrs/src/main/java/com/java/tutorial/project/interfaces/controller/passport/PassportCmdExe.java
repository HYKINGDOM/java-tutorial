package com.java.tutorial.project.interfaces.controller.passport;

import com.java.tutorial.project.application.service.passport.PassportCmdSvc;
import com.java.tutorial.project.common.kits.KV;
import com.java.tutorial.project.domain.businessobject.passport.LoginBO;
import com.java.tutorial.project.domain.businessobject.passport.PhoneCheckBO;
import com.java.tutorial.project.domain.businessobject.passport.PhoneSubmitBO;
import com.java.tutorial.project.domain.businessobject.passport.RefreshTokenBO;
import com.java.tutorial.project.domain.businessobject.passport.RegisterBO;
import com.java.tutorial.project.domain.businessobject.passport.ResetPasswordBO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * APP用户登录注册
 *
 *
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/cmd/passport")
public class PassportCmdExe {

    private final PassportCmdSvc service;

    /**
     * 注册
     */
    @PostMapping("/register")
    public KV register(@Valid @RequestBody RegisterBO bo) {
        return service.register(bo);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public KV login(@Valid @RequestBody LoginBO bo) {
        return service.login(bo);
    }

    /**
     * 刷新token
     */
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public KV refresh(@Valid @RequestBody RefreshTokenBO bo) {
        return service.refresh(bo.getRefreshToken());
    }

    /**
     * 修改密码
     *
     * @param bo 新密码
     * @return
     */
    @PostMapping("/reset-pwd")
    public void resetPwd(@Valid @RequestBody ResetPasswordBO bo) {
        // resetPasswordService.execute(assembler.resetPwdAssembler(dto));
    }

    /**
     * 更换手机号时，验证原手机号
     *
     * @param bo 原手机号
     * @return
     */
    @PostMapping("/phone-check")
    public void phoneCheck(@Valid @RequestBody PhoneCheckBO bo) {
        // phoneCheckService.execute(assembler.phoneCheckAssembler(dto));
    }

    /**
     * 更换手机号，提交
     *
     * @param bo 新手机号
     * @return
     */
    @PostMapping("/phone-submit")
    public void phoneSubmit(@Valid @RequestBody PhoneSubmitBO bo) {
        // phoneSubmitService.execute(assembler.phoneSubmitAssembler(dto));
    }

    /**
     * 注销账号
     *
     * @param id ID
     * @return
     */
    @DeleteMapping("/cancel")
    public void cancel(@NotNull(message = "ID不能为空") Long id) {
        System.err.println(id);
    }

}
