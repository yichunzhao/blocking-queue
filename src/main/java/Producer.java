import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class Producer implements Runnable {
    private final MyBlockingQueue<Integer> blockingQueue;
    private final int seqNum;

    @Override
    public void run() {
        log.info("produce: " + seqNum);
        blockingQueue.enqueue(seqNum);
    }
}
