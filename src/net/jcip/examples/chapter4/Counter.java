package net.jcip.examples.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Counter
 * <p/>
 * Simple thread-safe counter using the Java monitor pattern
 * 4-1 使用java监视器模式的线程安全计数器
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public final class Counter {

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue(){
        return value;
    }

    public synchronized long increment() {
        if(value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
        return ++value;
    }

}
