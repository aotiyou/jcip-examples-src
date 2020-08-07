package net.jcip.examples.chapter4;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ListHelper
 * <p/>
 * Examples of thread-safe and non-thread-safe implementations of
 * put-if-absent helper methods for List
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ListHelper<E> {

    /**
     * 4-14 非线程安全的“若没有则添加”（不要这么做）
     */
    @NotThreadSafe
    class BadListHelper<E> {
        public List<E> list = Collections.synchronizedList(new ArrayList<E>());

        public synchronized boolean putIfAbsent(E x) {
            boolean absent = !list.contains(x);
            if(absent)
                list.add(x);
            return absent;
        }
    }

    /**
     * 4-15 通过客户端加锁来实现“若没有则添加”
     */
    @ThreadSafe
    class GoodListHelper<E> {
        public List<E> list = Collections.synchronizedList(new ArrayList<>());

        public boolean putIfAbsent(E x) {
            synchronized (list) {
                boolean absent = !list.contains(x);
                if(absent)
                    list.add(x);
                return absent;
            }
        }
    }

}
