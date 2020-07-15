package net.jcip.examples.chapter1;

import net.jcip.annotations.NotThreadSafe;

/**
 * UnsafeSequence
 *
 * Introduction
 * 1.1 Non-thread-safe sequence generator
 * 程序清单1-1 非线程安全的数值序列生成器
 *
 * 并发安全问题：竞态条件(Race Condition)
 * getValue是否会返回唯一的值，要取决于运行时对线程中操作的交替执行方式
 *
 * @author Brian Goetz and TimePeierls
 */
@NotThreadSafe
public class UnsafeSequence implements Runnable{
    private int value;

    /**
     * Return a unique value.
     */
    public int getNext(){
        return value++;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-next = " + getNext());
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        for(int i=0; i<100; i++){
            new Thread(unsafeSequence, "thread-" + i).start();
        }
    }
}


