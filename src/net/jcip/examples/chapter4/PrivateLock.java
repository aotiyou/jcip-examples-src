package net.jcip.examples.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.examples.chapter2.Widget;

/**
 * PrivateLock
 * <p/>
 * Guarding state with a private lock
 * 4-3 通过一个私有锁来保护状态
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PrivateLock {

    private final Object myLock = new Object();
    @GuardedBy("myLock")
    Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }

}
