package com.java.tutorial.project.domian;

import lombok.Builder;
import lombok.Data;

import java.util.Date;



@Builder
@Data
public class Account {

    private Long accountId;

    private String accountName;

    private String nickName;

    private String password;

    private String icon;

    private String email;

    private Integer cellphone;

    private Integer accountStatus;

    private String note;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Date loginLastTime;

    private String loginLastIP;

}
