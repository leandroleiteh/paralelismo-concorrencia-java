package casesThreadsAndProblems;

import windown.Windown;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer2 {

    private static BlockingQueue<Integer> QUEUE = new LinkedBlockingQueue<>(5);
    private static final Lock LOCK = new ReentrantLock();
    private static volatile boolean PRODUCING = true;
    private static volatile boolean CONSUMING = true;

    public static void main(String[] args) {


        var producer = new Thread(() -> {
            while (true) {
                simulationProcess();
                if (PRODUCING) {
                    LOCK.lock();
                    System.out.println("----------PRODUZINDO...");
                    addElementsToList(QUEUE);

                    if (QUEUE.size() == 5) {
                        System.out.println("...PAUSANDO PRODUTOR");
                        PRODUCING = false;
                    }
                    if (QUEUE.size() == 1) {
                        System.out.println("INICIANDO CONSUMIDOR----------");
                        CONSUMING = true;
                    }
                    LOCK.unlock();
                } else System.out.println("CONSUMIDOR DORMINDO $$$");
            }
        });

        var consumer = new Thread(() -> {
            while (true) {
                simulationProcess();
                if (CONSUMING) {
                    LOCK.lock();
                    System.out.println("----------CONSUMINDO...");
                    removeFirstElementList(QUEUE);

                    if (QUEUE.size() == 0) {
                        System.out.println("...PAUSANDO CONSUMIDOR");
                        CONSUMING = false;
                    }
                    if (QUEUE.size() == 4) {
                        System.out.println("INICIANDO PRODUTOR----------");
                        PRODUCING = true;
                    }
                    LOCK.unlock();
                } else System.out.println("PRODUTOR DORMINDO ###");
            }
        });

        producer.start();
        consumer.start();

        Windown.monitor(() -> String.valueOf(QUEUE.size()));
    }

    private static void addElementsToList(BlockingQueue<Integer> queue) {
        var random = new Random().nextInt(1000);
        queue.add(random);
    }

    private static void removeFirstElementList(BlockingQueue<Integer> queue) {
        queue.stream().findFirst().ifPresent(QUEUE::remove);
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
