package net.jcip.examples.chapter3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * SynchronizedInteger
 * <p/>
 * Thread-safe mutable integer holder
 * 3-3 线程安全的可变整数类
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SychronizedInteger {

    @GuardedBy("this")
    private int value;

    public synchronized int get(){
        return value;
    }

    public synchronized void set(int value){
        this.value = value;
    }
}
