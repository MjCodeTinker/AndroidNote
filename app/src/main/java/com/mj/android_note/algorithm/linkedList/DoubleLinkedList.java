package com.mj.android_note.algorithm.linkedList;


/**
 * 双向链表的特点，有数据域，前驱节点，也有后继节点
 * 优点是：拿到任意节点，就能知道前驱节点，以及后继节点
 * 缺点是：每个节点都添加了额外的next域，占用了额外的空间
 */
public class DoubleLinkedList {


    // 双向链表节点
    static class Node<T> {
        // 数据域
        public T data;
        // 前驱节点
        public Node<T> prev;
        // 后继节点
        public Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

}
