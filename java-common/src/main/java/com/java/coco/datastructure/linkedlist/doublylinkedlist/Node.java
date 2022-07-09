package com.java.coco.datastructure.linkedlist.doublylinkedlist;

/**
 * 双向链表
 * @author HY
 */
public class Node {

    private int key;

    private String value;

    private Node prev;

    private Node next;


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
