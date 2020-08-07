package net.jcip.examples.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * PersonSet
 * <p/>
 * Using confinement to ensure thread safety
 * 4-2 通过封闭机制来确保线程安全
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    interface Person{

    }
}
