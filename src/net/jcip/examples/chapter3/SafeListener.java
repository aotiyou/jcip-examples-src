package net.jcip.examples.chapter3;

/**
 * SafeListener
 * <p/>
 * Using a factory method to prevent the this reference from escaping during construction
 *
 * @author Brian Goetz and Tim Peierls
 */
public class SafeListener {

    private final EventListener listener;

    private SafeListener(){
        listener = new EventListener() {
            @Override
            public void doEvent(Event e) {
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source){
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    void doSomething(Event e){

    }

    interface EventSource{
        void registerListener(EventListener e);
    }

    interface EventListener{
        void doEvent(Event e);
    }

    interface Event{

    }

}