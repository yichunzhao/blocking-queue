import lombok.Data;

import java.util.Deque;
import java.util.LinkedList;

@Data(staticConstructor = "create")
public class MyBlockingQueue<T> {
    private Deque<T> queue = new LinkedList<>();

    private int totalProduced;
    private int totalConsumed;

    //only one thread allow to put one element in the Q
    public synchronized void enqueue(T t) {
        if (queue.offer(t)) totalProduced++;
        //waking up a thread to offer
        notify();
    }

    //only one thread allow to take one element from the Q
    public synchronized T dequeue() throws InterruptedException {
        if (queue.isEmpty()) wait();
        T polled = queue.poll();
        totalConsumed++;
        return polled;
    }

}
