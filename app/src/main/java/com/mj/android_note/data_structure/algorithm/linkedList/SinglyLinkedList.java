package com.mj.android_note.data_structure.algorithm.linkedList;

/**
 * 链表结构：A -> B -> C -> D.....
 *
 * @param <T>
 */
public class SinglyLinkedList<T> {


    // 链表头结点
    private Node<T> head;


    public SinglyLinkedList(Node<T> head) {
        this.head = head;
    }

    // 反转链表
    public Node<T> reverseLinkedList() {

        // 链表如果为空或者链表没有下一个节点则直接返回链表本身

        // 前驱节点
        Node<T> pre = null;
        // 后继节点
        Node<T> next;

        while (head != null) {
            next = head.next;// 保存当前节点的后继节点
            head.next = pre; // 当前节点的后继节点切换为前驱节点
            pre = head; // 前驱节点变为当前节点
            head = next; // 当前节点变为后继节点，继续遍历
        }

        return pre;
    }


    // 通过递归的方式反转
    public Node<T> reverseByRecursion(Node head) {
        // head为空，或者已经到了尾结点，直接返回当前链表
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> reverseResult = reverseByRecursion(head.next);
        System.out.println("reverseResult = " + reverseResult.data);
        // 将当前节点的下一个节点的后继指针指向当前节点，
        head.next.next = head;
        head.next = null;
        return reverseResult;
    }


    // 排序，从小到大排序
    public Node<T> sortList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.data instanceof Integer) {
            // int 类型的

            Node<T> tempNode = head;
            while (tempNode.next != null) {
                Node<Integer> current = (Node<Integer>) tempNode;
                Node<Integer> next = (Node<Integer>) tempNode.next;
                if (current.data > next.data) {

                }
                tempNode = (Node<T>) next;
            }
        }

        return null;
    }


    public void printData(Node linkedList) {
        if (linkedList == null) {
            System.out.println("head s null... ");
            return;
        }
        Node temp = linkedList;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
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

    }

}
