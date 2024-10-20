package com.java.coco.bookmarksyntool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author meta
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookMark {

    private String dateAdded;

    private String dateModified;

    private String id;

    private String guid;

    private String name;

    private String type;

    private String url;

    private List<BookMark> children;
}
