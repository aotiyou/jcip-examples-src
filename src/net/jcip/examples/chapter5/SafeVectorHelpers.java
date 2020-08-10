package net.jcip.examples.chapter5;

import java.util.Vector;

/**
 * SafeVectorHelpers
 * <p/>
 * Compound actions on Vector using client-side locking
 * 5-2 在使用客户端加锁的Vector上的复合操作
 *
 * @author Brian Goetz and Tim Peierls
 */
public class SafeVectorHelpers {

    public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void removeLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

    /**
     * 5-4 带有客户端加锁的迭代
     */
    public static void iteratorElement(Vector vector) {
        synchronized (vector) {
            for(int i=0; i<vector.size();i++){
                doSomething(vector.get(i));
            }
        }
    }

    public static void doSomething(Object o){

    }


}
