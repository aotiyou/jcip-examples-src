package net.jcip.examples.chapter4;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * DelegatingVehicleTracker
 * <p/>
 * Delegating thread safety to a ConcurrentHashMap
 * 4-7 将线程安全委托给ConcurrentHashMap
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points){
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    /**
     * 不可修改但却实时的车辆位置视图
     */
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if(locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehicle name: " + id);
    }

    /**
     * 4-8 返回locations的静态拷贝而非实时拷贝
     *
     */
    // Alternate version of getLocations (Listing 4.8)
    public Map<String, Point> getLocationsAsStatic() {
        return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
    }

}
