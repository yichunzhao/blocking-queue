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
                Integer iterm = myBlockingQueue.dequeue();
                log.info("consumed: " + iterm);
                if (iterm == Integer.MAX_VALUE) break;
            } catch (InterruptedException e) {
                log.error("my Q dequeue", e);
            }
        }
    }
}
