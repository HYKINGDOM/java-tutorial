package com.java.coco.bookmarksyntool;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.java.coco.utils.ReadFromFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author HY
 */
public class BookMarkUtils {

    private List<WebUrl> webUrlList = new ArrayList<>();

    private List<BookMark> bookMarkList = new ArrayList<>();

    public List<WebUrl> BookMarkReadUtil(String filePath) {

        ReadFromFile readFromFile = new ReadFromFile();
        String fileByChars = readFromFile.readFileByCharsToMulti(filePath);
        fileByChars = fileByChars.replaceAll("date_added", "dateAdded").replaceAll("date_modified", "dateModified");

        JSONObject jsonObject = JSONUtil.parseObj(fileByChars);

        Map<String, Object> roots = JSONUtil.parseObj(jsonObject.get("roots").toString());

        Map<String, Object> other = JSONUtil.parseObj(roots.get("other").toString());

        List<Object> otherChildren = (List<Object>) JSONUtil.parse(other.get("children"));

        convertJsonToBean(otherChildren);

        Map<String, Object> bookmarkBar = JSONUtil.parseObj(roots.get("bookmark_bar").toString());

        List<Object> bookmarkBarChildren = (List<Object>) JSONUtil.parse(bookmarkBar.get("children"));

        convertJsonToBean(bookmarkBarChildren);

        for (BookMark bookMark : bookMarkList) {
            boolMarkConvertWebUrl(bookMark);
        }
        repetitionType();
        return webUrlList;
    }

    private void convertJsonToBean(List<Object> children) {
        for (Object child : children) {
            BookMark bookMark = convertObjectToBookMark(child);
            if (bookMark.getChildren() != null) {
                loopChekBookMark(bookMark.getChildren());
            } else {
                bookMarkList.add(bookMark);
            }
        }
    }

    private BookMark convertObjectToBookMark(Object object) {
        return JSONUtil.toBean(object.toString(), BookMark.class);
    }


    public BookMark loopChekBookMark(List<BookMark> bookMark) {
        if (bookMark == null) {
            return null;
        } else {
            bookMarkList.addAll(bookMark.stream().filter(e -> e.getChildren() == null).collect(Collectors.toList()));
            bookMark.stream().filter(e -> e.getChildren() != null).forEach(e -> loopChekBookMark(e.getChildren()));
        }
        return null;
    }


    private void boolMarkConvertWebUrl(BookMark bookMark) {
        WebUrl build = WebUrl.builder().urlName(bookMark.getName()).urlPath(bookMark.getUrl()).build();
        webUrlList.add(build);
    }


    public void repetitionType() {
        System.out.println("=================================================");

        Map<String, List<WebUrl>> urlPathMap = webUrlList.stream().collect(Collectors.groupingBy(WebUrl::getUrlPath));
        for (Map.Entry<String, List<WebUrl>> stringListEntry : urlPathMap.entrySet()) {
            if (stringListEntry.getValue().size() > 1) {
                System.out.println("重复URL： " + stringListEntry.getValue().stream().map(WebUrl::getUrlName).collect(Collectors.toList()));
            }
        }

        System.out.println("--------------------------------------------------");

        Map<String, List<WebUrl>> urlNameMap = webUrlList.stream().collect(Collectors.groupingBy(WebUrl::getUrlName));
        for (Map.Entry<String, List<WebUrl>> stringListEntry : urlNameMap.entrySet()) {
            if (stringListEntry.getValue().size() > 1) {
                System.out.println("重复书签名： " + stringListEntry.getValue().stream().map(WebUrl::getUrlPath).collect(Collectors.toList()));
            }
        }

        System.out.println("=================================================");

    }
}
