package net.jcip.examples.chapter3;

import net.jcip.annotations.NotThreadSafe;

/**
 * MutableInteger
 * <p/>
 * Non-thread-safe mutable integer holder
 * 3-2 非线程安全的可变整数类
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class MutableInteger {

    private int value;

    public int get(){
        return value;
    }

    public void set(int value){
        this.value =value;
    }

}
