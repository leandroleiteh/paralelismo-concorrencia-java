package casesThreadsAndProblems;

import windown.Windown;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerAndConsumer3 {

    private static BlockingQueue<Integer> QUEUE = new LinkedBlockingQueue<>(5);

    public static void main(String[] args) {

        Runnable producer = () -> {
            simulationProcess();
            System.out.println("Produzindo...");
            putInQueue();
        };
        
        Runnable consumer = () -> {
            simulationProcess();
            System.out.println("Consumindo....");
            takeInQueue();
        };

        var executor = Executors.newScheduledThreadPool(2);

        executor.scheduleAtFixedRate(producer, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(consumer, 0, 100, TimeUnit.MILLISECONDS);


        Windown.monitor(() -> String.valueOf(QUEUE.size()));
    }

    private static void takeInQueue() {
        try {
            QUEUE.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void putInQueue() {
        var ramdom = new Random().nextInt(1000);
        try {
            QUEUE.put(ramdom);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void simulationProcess() {
        var timeRamdom = new Random().nextInt(100);
        try {
            Thread.sleep(timeRamdom);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
