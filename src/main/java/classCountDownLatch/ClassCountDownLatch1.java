package classCountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClassCountDownLatch1 {

    private static volatile int j = 0;
    private static volatile int i = 0;
    private static CountDownLatch countDown;

    public static void main(String[] args) {

        var executor = Executors.newScheduledThreadPool(3);
        countDown = new CountDownLatch(3);

        Runnable runnable = () -> {
            j = new Random().nextInt();
            int x = i * j;
            System.out.println(i + " X " + j + " = " + x);
            countDown.countDown();
            sleep();
        };

        executor.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);

        while (true) {
            await();
            i++;
            countDown = new CountDownLatch(3);
        }

    }

    private static void await() {
        try {
            countDown.await();
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
