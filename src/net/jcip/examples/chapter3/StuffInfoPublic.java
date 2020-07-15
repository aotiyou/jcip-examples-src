package net.jcip.examples.chapter3;

/**
 * StuffIntoPublic
 * <p/>
 * Unsafe publication
 * 3-14 在没有足够同步的情况下发布对象（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffInfoPublic {

    public Holder holder;

    public void initialize(){
        holder = new Holder(42);
    }

}
