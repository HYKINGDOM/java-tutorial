package com.java.coco.bookmarksyntool;

import lombok.Builder;
import lombok.Data;

/**
 * @author meta
 */
@Builder
@Data
public class WebUrl {

    private String urlName;

    private String urlPath;
}
