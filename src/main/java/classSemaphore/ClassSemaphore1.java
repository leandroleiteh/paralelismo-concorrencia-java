package classSemaphore;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ClassSemaphore1 {

    private static final Semaphore SEMAPHORE = new Semaphore(10);
    private static CountDownLatch latch;
    private static volatile int i = 0;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        latch = new CountDownLatch(100);

        Instant start = Instant.now();

        Runnable r1 = () -> {
            String userName = "user" + i;

            acquire();
            System.out.println("O usuário: " + userName + " executou usando a Thread: " + Thread.currentThread().getName() + "\n");
            sleep();
            SEMAPHORE.release();
            latch.countDown();
        };

        while (i < 100) {
            executor.execute(r1);
            i++;
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Levou: " + Duration.between(start, Instant.now()).toSeconds() + "s");

        executor.shutdown();
    }

    private static void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void acquire() {
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleep() {
        try {
            int timeSequence = new Random().nextInt(3);
            timeSequence++;
            Thread.sleep(timeSequence * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
