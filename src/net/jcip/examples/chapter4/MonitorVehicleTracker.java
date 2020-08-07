package net.jcip.examples.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * MonitorVehicleTracker
 * <p/>
 * Monitor-based vehicle tracker implementation
 * 4-4 基于监视器模式的车辆追踪
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class MonitorVehicleTracker {

    /**
     * 车辆的标识和位置
     */
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations){
        this.locations = deepCopy(locations);
    }

    /**
     * deepCopy方法来复制正确的值
     */
    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    /**
     * MutablePoint拷贝构造函数
     * 返回车辆位置的快照
     */
    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    /**
     * 输入数据来修改车辆的位置
     */
    public synchronized void setLocations(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if(loc == null)
            throw new IllegalArgumentException("No such ID: " + id);
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for(String id : m.keySet()){
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
