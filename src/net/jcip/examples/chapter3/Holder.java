package net.jcip.examples.chapter3;

/**
 * Holder
 * <p/>
 * Class at risk of failure if not properly published
 * 3-15 由于未被正确发布，因此这个类可能出现故障
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Holder {

    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n)
            throw new AssertionError("This statement is false.");
    }

}
