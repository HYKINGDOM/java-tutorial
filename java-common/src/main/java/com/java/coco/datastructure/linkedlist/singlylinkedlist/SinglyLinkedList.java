package com.java.coco.datastructure.linkedlist.singlylinkedlist;

/**
 * 单链表 java实现
 *
 * @author HY
 */
public class SinglyLinkedList {

    /**
     * head节点是单链表的开始，不用来存储数据
     */
    private final Node head = new Node(0, null);

    /**
     * 将节点添加到尾部
     *
     * @param node
     */
    public void add(Node node) {
        Node temp = head;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(node);
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 按顺序添加节点
     *
     * @param node
     */
    public void addOfOrder(Node node) {
        Node temp = head;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(node);
                break;
            } else if (temp.getNext().getKey() > node.getKey()) {
                node.setNext(temp.getNext());
                temp.setNext(node);
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 获取某个节点
     *
     * @param key
     * @return
     */
    public Node get(int key) {
        if (head.getNext() == null) {
            return null;
        }
        Node temp = head.getNext();
        while (temp != null) {
            if (temp.getKey() == key) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    /**
     * 移除一个节点
     *
     * @param node
     */
    public void remove(Node node) {
        Node temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getKey() == node.getKey()) {
                temp.setNext(temp.getNext().getNext());
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 修改一个节点
     *
     * @param node
     */
    public void update(Node node) {
        Node temp = head.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getKey() == node.getKey()) {
                temp.setValue(node.getValue());
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 打印链表
     */
    public void print() {
        Node temp = head.getNext();
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }


}
