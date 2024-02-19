package com.java.coco.datastructure.linkedlist.singlylinkedlist;

/**
 * 单链表
 *
 * @author HY
 */
public class Node {

    private final int key;
    private String value;
    private Node next;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "key=" + key + ", value='" + value + '\'' + '}';
    }
}
