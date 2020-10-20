import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class Producer implements Runnable {
    private static final int MAX_NUM_PRODUCTS = 1000;

    private final MyBlockingQueue<Integer> blockingQueue;
    private int counter;

    @SneakyThrows
    @Override
    public void run() {
        while (counter <= MAX_NUM_PRODUCTS) {
            log.info("produce: " + counter);
            if (counter == MAX_NUM_PRODUCTS) blockingQueue.enqueue(Integer.MAX_VALUE);
            else blockingQueue.enqueue(counter);
            counter++;
        }
    }
}
