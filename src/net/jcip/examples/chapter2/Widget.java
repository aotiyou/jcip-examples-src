package net.jcip.examples.chapter2;

/**
 * Widget
 *
 * 2-7 如果内置锁不是可重入的，那么这段代码将发生死锁
 *
 * @author zxy
 */
public class Widget {
    public synchronized void doSomething(){
        System.out.println(toString() + ": invoke Widget...");
    }
}

class LoggingWidget extends Widget{

    @Override
    public synchronized void doSomething(){
        System.out.println(toString() + ": calling doSomething");
        super.doSomething();
    }

    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }
}
