package pcp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model {
    private final BlockingQueue<Task> queue;
    private final AtomicInteger increTaskNo = new AtomicInteger(0);
    public BlockingQueueModel(int cap) {
        // LinkedBlockingQueue 的队列不 init，入队时检查容量；ArrayBlockingQueue 在创建时 init
        this.queue = new LinkedBlockingQueue<Task>(cap);
    }
    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }
    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }
    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {
        @Override
        public void consume() throws InterruptedException {
            Task task = queue.take();
            System.out.println("消费: " + task.no);
        }
    }
    private class ProducerImpl extends AbstractProducer implements Producer, Runnable {
        @Override
        public void produce() throws InterruptedException {
            Task task = new Task(increTaskNo.getAndIncrement());
            System.out.println("生产: " + task.no);
            queue.put(task);
        }
    }
    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        for (int i = 0; i < 1; i++) {
            new Thread(model.newRunnableConsumer(), "消费者" + (i + 1)).start();
        }
        for (int i = 0; i < 1; i++) {
            new Thread(model.newRunnableProducer(), "生产者" + (i + 1)).start();
        }
    }
}
