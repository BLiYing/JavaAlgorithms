package model;

import java.util.Iterator;

/**
 * Description:链表实现Queue 《java算法》自我练习
 * Created by liying
 * on 2018-1-27.
 */

public class QueueByLink<Item> implements Iterable<Item> {
    private Node first;//首节点
    private Node last;//尾节点
    private int n;//集合中元素个数

    @Override
    public Iterator<Item> iterator() {
        return new RightListIterator();
    }

    private class Node{
        private Item item;
        private Node next;
    }

    private int size(){ return n;}

    private boolean isEmpty(){ return first == null;}

    /**
     * 错误示例
     * 插入到尾节点（我初次的错误写法）
     * @param item
     */
    /*private void enqueue(Item item){
        Node oldlastNode = last;
        last = new Node();
        last.item = item;
        last.next = null;
        //错误①
        oldlastNode.next = last;
        if(isEmpty()) first = last;
        n++;
    }*/

    /**
     * 插入正解
     */
    private void enqueue(Item item){
        Node oldlastNode = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else          oldlastNode.next = last;
        n++;
    }

    /**
     * 错误示例
     * 删除首节点（我初次的错误写法）
     * @return
     */
    /*private Item dequeue(){
        Item item = first.item;
        first.next = null;//错误①
        if(isEmpty()) last.next = null;////错误②
        n--;
        return item;
    }*/

    /**
     * 删除正解
     */
    private Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        n--;
        return item;
    }


    /**
     * 迭代错误示例
     * 我自己练习时有误的例子
     */
    class ErrorListIterator implements Iterator<Item>{
        private Node currentNode;
        @Override
        public boolean hasNext() {
            return currentNode.next != null;//错误①
        }

        @Override
        public Item next() {
            currentNode = first;//错误②顺序放在这里执行不行
            Item item = currentNode.item;
            first = first.next;//错误③
            return item;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * 迭代正解
     */
    class RightListIterator implements Iterator<Item>{
        private Node currentNode = first;
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        QueueByLink<String> queueByLink = new QueueByLink();
        queueByLink.enqueue("4");
        queueByLink.enqueue("2");
        queueByLink.enqueue("3");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");
        queueByLink.enqueue("4");

        System.out.println("元素个数:"+ queueByLink.size());
        String firstitem = queueByLink.dequeue();
        System.out.println("被删除元素:"+firstitem);
        int n = 0;
        for (String queueStr:queueByLink){
            if(queueByLink.isEmpty()){
                System.out.print("为空");
                return;
            }
            n++;
            System.out.println("第"+n+"个元素是:"+queueStr);

        }
        System.out.println("元素个数:"+ queueByLink.size());


    }
}
