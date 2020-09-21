import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor(staticName = "of")
@Slf4j
public class Consumer implements Runnable {
    private final MyBlockingQueue<Integer> myBlockingQueue;

    @Override
    public void run() {
        while (true) {
            try {
                log.info(myBlockingQueue.dequeue().toString());
            } catch (InterruptedException e) {
                log.error("my Q dequeue", e);
            }
        }
    }
}
