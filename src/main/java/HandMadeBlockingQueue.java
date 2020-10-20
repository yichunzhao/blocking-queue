import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HandMadeBlockingQueue {

    public static void main(String[] args) {
        MyBlockingQueue<Integer> myQueue = MyBlockingQueue.create();

        Thread th1 = new Thread(Producer.of(myQueue));
        Thread th2 = new Thread(Consumer.of(myQueue));

        List<Thread> threads = Arrays.asList(th1, th2);

        threads.forEach(thread -> thread.start());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error("join exception: ", e);
            }
        });

        log.info("Total consumed: " + myQueue.getTotalConsumed());
        log.info("Total Produced: " + myQueue.getTotalProduced());
    }

}
