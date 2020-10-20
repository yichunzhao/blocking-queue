import lombok.Data;

import java.util.Deque;
import java.util.LinkedList;

@Data(staticConstructor = "create")
public class MyBlockingQueue<T> {
    private static final int MAX_SIZE_QUEUE = 20;
    private Deque<T> queue = new LinkedList<>();

    private int totalProduced;
    private int totalConsumed;

    //only one thread allow to put one element in the Q
    public synchronized void enqueue(T item) throws InterruptedException {
        //if reaching max size of Q, let threads to wait.
        if (queue.size() == MAX_SIZE_QUEUE) this.wait();

        if (queue.offer(item)) totalProduced++;
        //not full, so waking up threads to produce more.
        this.notifyAll();
    }

    //only one thread allow to take one element from the Q
    public synchronized T dequeue() throws InterruptedException {
        //if Q is empty, all threads giving up the lock of this instance
        if (queue.isEmpty()) this.wait();

        T item = queue.poll();
        //Q is not empty, notify more threads to consume
        this.notifyAll();
        totalConsumed++;
        return item;
    }

}
