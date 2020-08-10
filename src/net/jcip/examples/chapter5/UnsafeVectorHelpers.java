package net.jcip.examples.chapter5;

import java.util.Vector;

/**
 * UnsafeVectorHelpers
 * <p/>
 * Compound actions on a Vector that may produce confusing results
 * 5-1 Vector上可能导致混乱结果的复合操作
 *
 * @author Brian Goetz and Tim Peierls
 */
public class UnsafeVectorHelpers {

    public static Object getLast(Vector list){
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list){
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }

    /**
     * 5-3 可能抛出ArrayIndexOutBoundsException的迭代操作
     */
    public static void iteratorElement(Vector vector){
        for(int i = 0; i<vector.size(); i++){
            doSomething(vector.get(i));
        }
    }

    public static void doSomething(Object o){

    }

}
