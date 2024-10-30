package classSemaphore;

import windown.Windown;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassSemaphore2 {

    private static final Semaphore SEMAPHORE = new Semaphore(20);
    private static AtomicInteger quantity = new AtomicInteger(0);
    private static CountDownLatch latch;
    private static volatile int i = 0;

    public static void main(String[] args) {

        var executor = Executors.newScheduledThreadPool(100);
        latch = new CountDownLatch(100);

        Instant start = Instant.now();

        Runnable r1 = () -> {
            String userName = "user" + i;
            var done = false;

            quantity.incrementAndGet();
            while (!done) {
                done = tryAcquire();
            }
            quantity.decrementAndGet();
            System.out.println("O usuário: " + userName + " executou usando a Thread: " + Thread.currentThread().getName() + "\n");
            sleep();
            SEMAPHORE.release();
            latch.countDown();
        };

        Windown.Message windown = Windown.createWindown("Quantitdade");
        Runnable r2 = () -> {
            int value = quantity.get();
            windown.setText(value + " usuários esperando para executar");
        };

        while (i < 100) {
            executor.execute(r1);
            i++;
        }
        executor.scheduleWithFixedDelay(r2, 0, 100, TimeUnit.MILLISECONDS);

        await();

        windown.setText("Levou: " + Duration.between(start, Instant.now()).toSeconds() + "s");
        Windown.closed();
        executor.shutdownNow();
    }

    private static void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean tryAcquire() {
        try {
            return SEMAPHORE.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
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
