package net.jcip.examples.chapter4;

import net.jcip.annotations.Immutable;

/**
 * Point
 * <p/>
 * Immutable Point class used by DelegatingVehicleTracker
 * 4-6 在DelegatingVehicleTracker中使用的不可变Point类
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class Point {

    public final int x,y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

}
