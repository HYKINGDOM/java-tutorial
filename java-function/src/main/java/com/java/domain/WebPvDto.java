package com.java.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WebPvDto {

    private String type;

    private Integer score;

    private Integer pvCount;


    public Object value;

    public WebPvDto(String type, Integer score, Integer pvCount) {
        this.type = type;
        this.score = score;
        this.pvCount = pvCount;
    }
}
