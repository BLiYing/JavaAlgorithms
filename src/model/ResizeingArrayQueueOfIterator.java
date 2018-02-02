package model;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 动态数组实现Queue 《java算法》自我练习
 * Created by liying on 2018-1-25.
 */
public class ResizeingArrayQueueOfIterator<Item> implements Iterable<Item> {

    private int head;//头
    private int tail;//尾
    private int n;//队列中元素个数
    public Item[] q;//数组

    public ResizeingArrayQueueOfIterator(){

        q = (Item[]) new Object[2];
        n = 0;
        head = 0;
        tail = 0;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    private void resizeBagArray(int m) {
        assert m >= n;//这句代码也没想到
        Item[] newbagArray = (Item[]) new Object[m];
        for (int i = 0; i< n; i++) {
            //比如为什么是(head+i)% q.length
            newbagArray[i] = q[(head+i)% q.length];//初次写成newbagArray[i] = q[i];
        }
        q = newbagArray;
        head = 0;
        tail = n;
    }

    public void add(Item t){
        if(n == q.length)  resizeBagArray(2* q.length);//条件判断第一次写成if(n >= q.length)
        q[tail++] = t;
        if(tail == q.length) tail = 0;
        n++;

    }

    /**
     * 好好琢磨
     * @return
     */
    public Item delete(){
        Item t = q[head];
        q[head] = null;
        head++;
        n--;
        if (head == q.length) head = 0;           // wrap-around
        // shrink size of array if necessary
        //比如为什么是四分之一
        if(head > 0 && head == q.length/4) resizeBagArray(q.length/2);
        return t;

    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>{

        private int i = 0;
        public boolean hasNext() {return i < n;           }

        public void remove()     {throw  new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item t =  q[(head+i)% q.length];
            i++;
            return t;
        }
    }

    public static void main(String[] args) {
        ResizeingArrayQueueOfIterator<String> queueOfIterator = new ResizeingArrayQueueOfIterator<>();

        queueOfIterator.add("1");
        queueOfIterator.add("2");
        queueOfIterator.add("3");
        queueOfIterator.add("4");

        for (String queueStr:queueOfIterator) {
            if(queueOfIterator.isEmpty()){
                System.out.print("为空");
                return;
            }
            System.out.print(queueStr+"\n");
        }
    }


}
