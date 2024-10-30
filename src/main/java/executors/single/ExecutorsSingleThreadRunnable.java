package executors.single;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorsSingleThreadRunnable {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = null;

        try {
            executor = Executors.newSingleThreadExecutor();

            executor.execute(() -> System.out.println("Executando task 1 com execute... com a Thread:  " + Thread.currentThread().getName().toUpperCase()));
            executor.execute(() -> System.out.println("Executando task 2 com execute... com a Thread:  " + Thread.currentThread().getName().toUpperCase()));
            executor.execute(() -> System.out.println("Executando task 3 com execute... com a Thread:  " + Thread.currentThread().getName().toUpperCase()));
            Future<?> future = executor.submit(() -> {
                System.out.println("Executando task 4 com submit... com a Thread:  " + Thread.currentThread().getName().toUpperCase() + "\n");
            });
            Thread.sleep(3);
            System.out.println("Task com submit terminada? " + future.isDone());
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            Thread.sleep(2000);
            System.out.println("Task com submit terminada " + future.isDone());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (Objects.nonNull(executor)) {
                executor.shutdownNow();
            }
        }
    }
}
