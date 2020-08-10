package net.jcip.examples.chapter5;

import java.util.concurrent.ConcurrentMap;

/**
 * 使用一种粒度更细的加锁机制来实现更大程度的共享，这种机制成为分段锁(Lock Striping)
 * 返回的迭代器具有弱一致性（Weakly Consistent）
 * 只有当应用程序需要加锁Map已进行独占访问时，才应该放弃使用ConcurrentHashMap
 *
 * @param <K>
 * @param <V>
 */
public interface ConcurrentMapTest<K,V> extends ConcurrentMap<K,V> {

    /**
     * 仅当K没有相应的映射值时才插入
     */
    @Override
    V putIfAbsent(K key, V value);

    /**
     * 仅当K被映射到V时才移除
     */
    @Override
    boolean remove(Object key, Object value);

    /**
     * 仅当K被映射到oldValue时才替换为newValue
     */
    @Override
    boolean replace(K key, V oldValue, V newValue);

    /**
     * 仅当K被映射到某个值时才替换为newValue
     */
    @Override
    V replace(K key, V newValue);

}
