package classCountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClassCountDownLatch2 {

    private static volatile int j = 0;
    private static volatile int i = 0;
    private static CountDownLatch latch;

    public static void main(String[] args) {

        var executor = Executors.newScheduledThreadPool(4);
        latch = new CountDownLatch(3);

        Runnable r1 = () -> {
            j = new Random().nextInt();
            int x = i * j;
            System.out.println(i + " X " + j + " = " + x);
            latch.countDown();
        };

        Runnable r2 = () -> {
            await();
            i = new Random().nextInt(100);
        };

        Runnable r3 = () -> {
            await();
            latch = new CountDownLatch(3);
        };

        Runnable r4 = () -> {
            await();
            System.out.println("Terminado!");
        };

        executor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(r2, 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(r3, 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(r4, 0, 1, TimeUnit.SECONDS);

    }

    private static void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
