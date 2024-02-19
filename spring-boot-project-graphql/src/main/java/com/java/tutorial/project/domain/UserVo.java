package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private int userId;
    private String userName;
    private String realName;
    private String email;
    private List<PostVo> postVos;

}
