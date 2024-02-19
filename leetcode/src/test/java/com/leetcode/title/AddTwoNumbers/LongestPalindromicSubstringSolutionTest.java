package com.leetcode.title.AddTwoNumbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class LongestPalindromicSubstringSolutionTest {

    @Test
    public void print_list_node_data() {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode();
        ListNode listNode2 = new ListNode();
        ListNode listNode = solution.addTwoNumbers(listNode1, listNode2);
        assertNull(listNode);

    }
}
