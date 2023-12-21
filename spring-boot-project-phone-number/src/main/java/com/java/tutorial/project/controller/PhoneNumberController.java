package com.java.tutorial.project.controller;

import com.java.coco.common.Result;
import com.java.tutorial.project.util.PhoneToRegionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author meta
 */
@RequestMapping("/demo")
@RestController
public class PhoneNumberController {

    @GetMapping("/getPhoneAffiliationInfo.do/{phone}")
    public Result<?> getPhoneAffiliationInfo(@PathVariable("phone") String phone){
        return Result.success(PhoneToRegionUtil.getPhoneAffiliationInfo(phone));
    }

}
