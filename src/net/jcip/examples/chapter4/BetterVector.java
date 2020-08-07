package net.jcip.examples.chapter4;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;

/**
 * BetterVector
 * <p/>
 * Extending Vector to have a put-if-absent method
 * 4-13 扩展Vector并增加一个“若没有则添加”方法
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

    // When extending a serializable class, you should redefine serialVersionUID
    static final long serialVersionUID = -3963416950630760754L;

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent)
            add(x);
        return absent;
    }

}
