package net.jcip.examples.chapter2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * SynchronizedFactorizer
 *
 * Servlet that caches last result, but with unnacceptably poor concurrency
 * 2-6 这个Servlet能正确地缓存最新的计算结果，但并发性却非常糟糕（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SynchronizedFactorizer extends GenericServlet implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;
    @GuardedBy("this")
    private BigInteger[] lastFactors;

    @Override
    public synchronized void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if(i.equals(lastNumber)){
            encodeIntoResponse(resp, lastFactors);
        }else{
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(resp, factors);
        }
    }

    BigInteger extractFromRequest(ServletRequest req){
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i){
        // Doesn't really factor
        return new BigInteger[] { i };
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors){

    }
}
