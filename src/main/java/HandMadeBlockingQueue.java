import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class HandMadeBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> myQueue = MyBlockingQueue.create();

        ExecutorService service = Executors.newFixedThreadPool(20);

        IntStream.range(0, 450).forEach(i -> service.submit(Producer.of(myQueue, i)));
        IntStream.range(0, 10).forEach(i -> service.submit(Consumer.of(myQueue)));

        service.shutdown();
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        log.info("is terminated? " + service.isShutdown());

        log.info("total produced: " + myQueue.getTotalProduced());
        log.info("total consumed: " + myQueue.getTotalConsumed());

        System.exit(1);
    }

}
