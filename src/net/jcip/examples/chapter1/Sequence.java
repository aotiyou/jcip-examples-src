package net.jcip.examples.chapter1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Sequence
 *
 * Introduction
 * 1.2 Thread-safe sequence generator
 * 程序清单1-2 线程安全的数值序列生成器
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class Sequence implements Runnable{
    @GuardedBy("this")
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-next = " + getNext());
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        for(int i=0; i<100; i++){
            new Thread(sequence, "thread-"+i).start();
        }
    }
}


