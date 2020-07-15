package net.jcip.examples.chapter2;

import net.jcip.annotations.NotThreadSafe;

/**
 * LazyInitRace
 *
 * Race condition in lazy initialization
 * 2-3 延迟初始化中的竞态条件（不要这么做）
 *
 * @author zxy
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance(){
        if(instance == null)
            instance = new ExpensiveObject();
        return instance;
    }

}

class ExpensiveObject{}
