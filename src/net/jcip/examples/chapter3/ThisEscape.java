package net.jcip.examples.chapter3;

/**
 * ThisEscape
 * <p/>
 * Implicitly allowing the this reference to escape
 * 3-7 隐式地使this引用逸出（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThisEscape {

    public ThisEscape(EventSource source){
        source.registerListener(new EventListener(){

            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
    }

    void doSomething(Event e) {

    }

    interface EventSource{
        void registerListener(EventListener e);
    }

    interface EventListener{
        void onEvent(Event e);
    }

    interface Event{

    }
}
