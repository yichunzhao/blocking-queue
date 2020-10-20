import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class Producer implements Runnable {
    private final MyBlockingQueue<Integer> blockingQueue;
    private int counter;

    @SneakyThrows
    @Override
    public void run() {
        while (counter <= 1000) {
            log.info("produce: " + counter);
            if (counter == 1000) blockingQueue.enqueue(Integer.MAX_VALUE);
            else blockingQueue.enqueue(counter);
            counter++;
        }
    }
}
