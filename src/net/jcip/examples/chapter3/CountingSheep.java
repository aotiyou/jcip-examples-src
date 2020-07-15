package net.jcip.examples.chapter3;

/**
 * CountingSheep
 * <p/>
 * Counting sheep
 * 3-4 数绵羊
 *
 * @author Brian Goetz and Tim Peierls
 */
public class CountingSheep {

    volatile boolean asleep;

    void tryToSleep(){
        while(!asleep){
            countSomeSheep();
        }
    }

    void countSomeSheep(){
        // One, two, three...
    }

}
