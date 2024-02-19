package com.java.coco.datastructure.linkedlist.doublylinkedlist;

/**
 * 双向链表
 *
 * @author HY
 */
public class DoublyLinkedList {

    /**
     * first节点是双链表的头部，即第一个节点
     */
    private Node first = null;

    /**
     * tail节点是双链表的尾部，即最后一个节点
     */
    private Node last = null;

    /**
     * 从尾部添加
     *
     * @param node
     * @return
     */
    public DoublyLinkedList addToTail(Node node) {

        if (last == null) {
            first = node;
        } else {
            last.setNext(node);
            node.setPrev(last);
        }
        last = node;
        return this;
    }

    /**
     * 从头部添加
     *
     * @param node
     * @return
     */
    public DoublyLinkedList DoublyLinkedList(Node node) {
        if (first == null) {
            last = node;
        } else {
            first.setPrev(node);
            node.setNext(first);
        }
        first = node;
        return this;
    }

    /**
     * 按照顺序添加
     *
     * @param node
     * @return
     */
    public DoublyLinkedList addOfOrder(Node node) {
        if (first == null) {
            first = node;
            last = node;
            return this;
        }

        if (first.getKey() > node.getKey()) {
            first.setPrev(node);
            node.setNext(first);
            first = node;
            return this;
        }

        return this;
    }

}
