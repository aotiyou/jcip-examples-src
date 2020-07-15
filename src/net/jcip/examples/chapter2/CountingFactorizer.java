package net.jcip.examples.chapter2;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * CountingFactorizer
 *
 * Servlet that counts requests using AtomicLong
 * 2-4 使用AtomicLong类型的变量来统计已处理请求的数量
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class CountingFactorizer extends GenericServlet implements Servlet {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount(){
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }

    BigInteger extractFromRequest(ServletRequest req){
        return new BigInteger("1");
    }

    BigInteger[] factor(BigInteger i){
        return new BigInteger[]{i};
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors){}

}
