package net.jcip.examples.chapter4;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PublishingVehicleTracker
 * <p/>
 * Vehicle tracker that safely publishes underlying state
 * 4-12 安全发布底层状态的车辆追踪器
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class PublishingVehicleTracker {

    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations){
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return locations;
    }

    /**
     * 返回底层Map对象的一个不可变副本
     */
    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y) {
        if(!locations.containsKey(id))
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        locations.get(id).set(x, y);
    }

}
