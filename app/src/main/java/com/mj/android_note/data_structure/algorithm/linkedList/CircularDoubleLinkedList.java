package com.mj.android_note.data_structure.algorithm.linkedList;


/**
 * 循环双链表的特点：
 * 循环链表头结点的，前驱节点指向链表的尾结点，尾结点的后继节点指向头结点，就构成了双向链表
 */
public class CircularDoubleLinkedList {

    // 循环双链表，数据节点
    static class Node<T> {
        // 数据域
        T data;
        // 前驱节点
        Node<T> prev;
        // 后继节点
        Node<T> next;
    }

}
