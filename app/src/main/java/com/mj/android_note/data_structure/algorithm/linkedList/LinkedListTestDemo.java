package com.mj.android_note.data_structure.algorithm.linkedList;

import java.util.Arrays;


/**
 * 单链表的特点：
 * 单链表分为 数据域：data ,指针域：next，指针域
 * 数据域 + 指针域 ： 称为节点
 * 多个节点连接在一起就组成的链表
 * 算法中有一个概念叫：原地排序，不需要额外的存储空间，在原数据结构的基础上进行排序，这种排序的空间复杂度是O(1),插入排序，冒泡排序...都是原地排序算法
 */
public class LinkedListTestDemo {

    public static void main(String[] args) {
        System.out.println("LinkedListTestDemo main args: " + Arrays.toString(args));

        SinglyLinkedList.Node<Integer> n3 = new SinglyLinkedList.Node<>(4, null);
        SinglyLinkedList.Node<Integer> n1 = new SinglyLinkedList.Node<>(2, n3);
        SinglyLinkedList.Node<Integer> n2 = new SinglyLinkedList.Node<>(3, n1);
        SinglyLinkedList.Node<Integer> head = new SinglyLinkedList.Node<>(1, n2);

        SinglyLinkedList list = new SinglyLinkedList<>(head);
        list.printData(head);
        System.out.println("################");
        list.printData(list.reverseByRecursion(head));
    }

}
