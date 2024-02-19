package com.java.validate.controller;

import com.java.validate.domain.CustomForm;
import com.java.validate.domain.Insert;
import com.java.validate.domain.PhoneForm;
import com.java.validate.domain.Update;
import com.java.validate.domain.UserForm;
import com.java.validate.util.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/phone_info")
public class PhoneController extends ResultVo {

    @GetMapping("/test")
    public String phoneInfoTest01() {
        return "true";
    }

    /**
     * 获取电话号码信息
     */
    @GetMapping("/test01/{phone}")
    public String phoneInfoTest01(@PathVariable("phone") String phone) {
        // 验证电话号码是否有效
        String pattern = "^[1][3,4,5,7,8][0-9]{9}$";
        boolean isValid = Pattern.matches(pattern, phone);
        if (isValid) {
            // 执行相应逻辑
            return phone;
        } else {
            // 返回错误信息
            return "手机号码无效";
        }
    }

    /**
     * 获取电话号码信息
     */
    @GetMapping("/test02/{phone:^[1][3,4,5,7,8][0-9]{9}$}")
    public String phoneInfoTest02(@PathVariable("phone") String phone) {
        return phone;
    }

    /**
     * 获取电话号码信息
     */
    @GetMapping("/test03")
    public ResultVo phoneInfoTest03(@RequestBody PhoneForm phoneForm) {
        return ResultVo.success(phoneForm);
    }

    @PostMapping("/test04/customTest")
    public ResultVo customTest(@RequestBody @Validated CustomForm form) {
        return ResultVo.success(form.getPhone());
    }

    /**
     * 添加用户
     */
    @PostMapping("/addUser")
    public ResultVo addUser(@RequestBody @Validated({Insert.class}) UserForm form) {
        // 选择对应的分组进行校验
        return ResultVo.success(form);
    }

    /**
     * 更新用户
     */
    @PostMapping("/updateUser")
    public ResultVo updateUser(@RequestBody @Validated({Update.class}) UserForm form) {
        // 选择对应的分组进行校验
        return ResultVo.success(form);
    }

}
