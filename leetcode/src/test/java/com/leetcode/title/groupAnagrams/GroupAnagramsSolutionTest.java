package com.leetcode.title.groupAnagrams;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GroupAnagramsSolutionTest {

    private GroupAnagramsSolution groupAnagramsSolution;

    private GroupAnagramsSolutionA groupAnagramsSolutionA;

    @BeforeEach
    void setUp() {
        groupAnagramsSolution = new GroupAnagramsSolution();
        groupAnagramsSolutionA = new GroupAnagramsSolutionA();
    }

    @Test
    public void test_main_A() {
        String[] test = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> listList = groupAnagramsSolution.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        assertThat(listList.size()).isEqualTo(3);

        assertThat(listList.size()).isEqualTo(3);
    }

    @Test
    public void test_main_1() {
        String[] test = new String[] {""};
        List<List<String>> listList = groupAnagramsSolution.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        assertThat(listList.size()).isEqualTo(1);
    }

    @Test
    public void test_main_2() {
        String[] test = new String[] {"a"};
        List<List<String>> listList = groupAnagramsSolution.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        assertThat(listList.size()).isEqualTo(1);
    }

    @Test
    public void test_main_3() {
        String[] test = new String[] {"ddddddddddg", "dgggggggggg"};
        List<List<String>> listList = groupAnagramsSolution.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        List<List<String>> except = Lists.newArrayList();
        except.add(Lists.newArrayList("dgggggggggg"));
        except.add(Lists.newArrayList("ddddddddddg"));

        assertThat(listList.size()).isEqualTo(2);
        assertThat(listList).isEqualTo(except);
    }

    @Test
    public void test_groupAnagramsSolutionA_A() {
        String[] test = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> listList = groupAnagramsSolutionA.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        assertThat(listList.size()).isEqualTo(3);

        assertThat(listList.size()).isEqualTo(3);
    }

    @Test
    public void test_groupAnagramsSolutionA_1() {
        String[] test = new String[] {""};
        List<List<String>> listList = groupAnagramsSolutionA.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        assertThat(listList.size()).isEqualTo(1);
    }

    @Test
    public void test_groupAnagramsSolutionA_2() {
        String[] test = new String[] {"a"};
        List<List<String>> listList = groupAnagramsSolutionA.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        assertThat(listList.size()).isEqualTo(1);
    }

    @Test
    public void test_groupAnagramsSolutionA_3() {
        String[] test = new String[] {"ddddddddddg", "dgggggggggg"};
        List<List<String>> listList = groupAnagramsSolutionA.groupAnagrams(test);

        for (List<String> stringList : listList) {
            System.out.println(stringList);
        }

        List<List<String>> except = Lists.newArrayList();
        except.add(Lists.newArrayList("dgggggggggg"));
        except.add(Lists.newArrayList("ddddddddddg"));

        assertThat(listList.size()).isEqualTo(2);
        assertThat(listList).isEqualTo(except);
    }

}