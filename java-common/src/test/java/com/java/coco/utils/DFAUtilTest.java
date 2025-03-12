package com.java.coco.utils;

import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.WordTree;
import com.google.common.base.Optional;
import com.java.coco.guava.Test1;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DFAUtilTest {


    @Test
    public void test_demo_01() {
        // 创建 WordTree 对象，用于构建 DFA 模型
        WordTree wordTree = new WordTree();
        // 添加关键字
        List<String> keywords = new ArrayList<>();
        keywords.add("苹果");
        keywords.add("香蕉");
        keywords.add("葡萄");
        wordTree.addWords(keywords);
        // 待查找的文本
        String text = "我喜欢吃苹果和香蕉。dasdadasd我喜欢吃苹果和香蕉,dasdadasd";
        // 查找文本中包含的关键字
        List<FoundWord> foundWords = wordTree.matchAllWords(text);
        for (FoundWord foundWord : foundWords) {
            System.out.println("找到关键字：" + foundWord.getWord() + "，起始位置：" + foundWord.getStartIndex() + "，结束位置：" + foundWord.getEndIndex());
        }
    }
}
