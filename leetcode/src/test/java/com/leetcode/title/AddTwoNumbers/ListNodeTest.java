package com.leetcode.title.AddTwoNumbers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListNodeTest {


    /**
     * 2--4--3
     * 5--6--4
     */
    @Test
    public void create_list_node() {
        ListNode listNode = new ListNode(3);
        ListNode listNode1 = new ListNode(4, listNode);
        ListNode listNode2 = new ListNode(2, listNode1);

        int val = listNode2.getVal();
        assertEquals(val, 2);
        int val1 = listNode2.getNext().getVal();
        assertEquals(val1, 4);
        int val2 = listNode2.getNext().getNext().getVal();
        assertEquals(val2, 3);
    }

}