package net.jcip.examples.chapter2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * CachedFactorizer
 * <p/>
 * Servlet that caches its last request and result
 * 缓存最近执行因数分解的数值及其计算结果的Servlet
 *
 * 一个同步代码块负责保护判断是否只需返回缓存结果的“先检查后执行”操作徐磊
 * 另一个同步代码块则负责确保对缓存的数值和因素分解结果进行同步更新
 * 引入“命中计数器”，添加一个“缓存命中”计数器，并在第一个同步代码块中更新这两个变量
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class CachedFactorizer extends GenericServlet implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;
    @GuardedBy("this")
    private BigInteger[] lastFactors;
    @GuardedBy("this")
    private long hits;
    @GuardedBy("this")
    private long cachedHits;

    public long getHits() {
        return hits;
    }

    public double getCachedHits(){
        return (double) cachedHits / (double) hits;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        synchronized (this){
            ++hits;
            if(i.equals(lastNumber)){
                ++cachedHits;
                factors = lastFactors.clone();
            }
        }
        if(factors == null){
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors;
            }
        }
        encodeIntoResponse(resp, factors);
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
