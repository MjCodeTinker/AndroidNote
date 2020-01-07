package com.mj.android_note.algorithm.LinkedList;

/**
 * 链表结构：A -> B -> C -> D.....
 * @param <T>
 */
public class SinglyLinkedList<T> {


    // 链表头结点
    Node<T> head;


    // 反转链表
    public Node<T> reverseLinkedList() {

        // 链表如果为空或者链表没有下一个节点则直接返回链表本身
        if (head == null || head.next == null) {
            return head;
        }

        // 前驱节点
        Node<T> prev;
        //后继节点
        Node<T> next;

        while (head.hasNext()) {
            next = head.next;
        }
        return head;
    }


    // 单链表节点
    static class Node<T> {
        T data;
        Node<T> next;

        public Node() {
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        // 检查是否有下一个节点
        public boolean hasNext() {
            return next != null;
        }

    }

}
