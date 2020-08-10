package net.jcip.examples.chapter5;

import java.util.concurrent.BlockingDeque;

/**
 * TaskRunnable
 * <p/>
 * Restoring the interrupted status so as not to swallow the interrupt
 * 5-10 恢复中断状态以避免屏蔽中断
 *
 * @author Brian Goetz and Tim Peierls
 */
public class L5_10_TaskRunnable implements Runnable{
    BlockingDeque<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        }catch (InterruptedException e){
            // restore interrupted status 恢复被中断的状态
            Thread.currentThread().interrupt();
        }
    }

    void processTask(Task task) {
        // Handle the task
    }

    interface Task{

    }
}
