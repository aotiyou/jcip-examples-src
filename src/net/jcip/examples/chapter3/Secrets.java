package net.jcip.examples.chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * Secrets
 *
 * Publishing an object
 * 3-5 发布一个对象
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Secrets {

    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<Secret>();
    }

}

class Secret{

}
