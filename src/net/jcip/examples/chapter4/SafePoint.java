package net.jcip.examples.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * SafePoint
 * 4-11 线程安全且可变的Point类
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafePoint {

    @GuardedBy("this")
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y){
        this.set(x, y);
    }

    public synchronized int[] get() {
        return new int[]{x,y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
