package net.jcip.examples.chapter3;

/**
 * UnsafeStates
 * <p/>
 * Allowing internal mutable state to escape
 * 3-6 使内部的可变状态逸出（不要这么做)
 *
 * @author Brian Goetz and Tim Peierls
 */
public class UnsafeStates {

    private String[] states = new String[]{
            "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return states;
    }

}
