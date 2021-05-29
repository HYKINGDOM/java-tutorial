package com.java.util.javautil.datastructure.linkedlist.singlylinkedlist;

public class SinglyLinkedListTest {

    public static void main(String[] args) {
        Node node1 = new Node(1, "张三");
        Node node2 = new Node(3, "李四");
        Node node3 = new Node(7, "王五");
        Node node4 = new Node(5, "赵六");

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        System.out.println("-----------添加节点（尾部）");
        singlyLinkedList.add(node1);
        singlyLinkedList.add(node2);
        singlyLinkedList.add(node3);
        singlyLinkedList.add(node4);
        singlyLinkedList.print();

        System.out.println("-----------获取某个节点");
        Node node = singlyLinkedList.get(3);
        System.out.println(node);

        singlyLinkedList.remove(node3);
        System.out.println("-----------移除节点");
        singlyLinkedList.print();

        System.out.println("-----------修改节点");
        singlyLinkedList.update(new Node(5, "赵六2"));
        singlyLinkedList.print();

        System.out.println("-----------按顺序添加节点");
        Node node5 = new Node(4, "王朝");
        singlyLinkedList.addOfOrder(node5);
        singlyLinkedList.print();

    }
}
