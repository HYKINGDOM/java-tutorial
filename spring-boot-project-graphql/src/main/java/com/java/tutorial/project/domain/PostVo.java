package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostVo {

    private int postId;
    private String title;
    private String text;
    private String category;
    //    private UserVo userVo;
}
