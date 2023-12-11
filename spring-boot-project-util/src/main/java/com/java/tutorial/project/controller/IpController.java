package com.java.tutorial.project.controller;

import com.java.tutorial.project.util.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coderJim
 */
@RestController
@RequestMapping("/demo")
public class IpController {

    @ResponseBody
    @GetMapping("/realAddress")
    public String realAddress(HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        //在线获取
        //       String realAddress = AddressUtils.getRealAddressByIP(ip);
        //离线获取
        String realAddress = IpUtil.getAddr(ip);
        return "您的ip所在地为:"+ realAddress;
    }
}
