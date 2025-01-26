package com.java.tutorial.project.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author kscs
 */
@Data
@AllArgsConstructor
public class ExportTable {

    private String id;
    private String style;
    private int rowCount;
    private List<ExportColumn> fields;
    private String fileName;
    private List<List<String>> head;
    private Set<Integer> mergeIndex;

    @Data
    public static class ExportColumn {

        private long columnNum;
        private String columnName;
        private boolean isShow;
        private Font font;

        public ExportColumn() {
        }

        @Data
        @AllArgsConstructor
        public static class Font {
            private String fontName;
            private int fontSize;
            private String fixed;
            private boolean isShow;

            private String fontFamily;
            private String fontWeight;
            private String color;
            private String background;
            private String align;
            private String width;
        }
    }
}

