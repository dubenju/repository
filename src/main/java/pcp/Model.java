package pcp;

public interface Model {
    public Runnable newRunnableConsumer();
    public Runnable newRunnableProducer();
}
