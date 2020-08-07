package net.jcip.examples.chapter4;

import net.jcip.annotations.NotThreadSafe;

/**
 * MutablePoint
 * <p/>
 * Mutable Point class similar to java.awt.Point
 * 4-5 与Java.awt.Point类似的可变Point类（不要这么做）
 *
 * 表示车辆的位置
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class MutablePoint {

    public int x,y;

    public MutablePoint(){
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

}
