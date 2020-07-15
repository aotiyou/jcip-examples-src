package net.jcip.examples.chapter2;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * UnsafeCachingFactorizer
 *
 * Servlet that attempts to cache its last result without adequate atomicity
 * 2-5 该Servlet在没有足够原子性保证的情况下对其最近计算结果进行缓存（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if(i.equals(lastNumber.get())){
            encodeIntoResponse(resp, lastFactors.get());
        }else{
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }

    }

    BigInteger extractFromRequest(ServletRequest req){
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i){
        // Doesn't really factor
        return new BigInteger[]{i};
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors){

    }
}
