package executors.single;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsSingleThreadCallable {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = null;

        try {
            executor = Executors.newSingleThreadExecutor();

            Future<String> futureString = executor.submit(new MyCallable());

            Future<Integer> futureInteger = executor.submit(() -> new Random().nextInt(1000));

            System.out.println("Task com futureString terminada? " + futureString.isDone());
            System.out.println("Task com futureInteger terminada? " + futureInteger.isDone() + "\n");


            System.out.println("Task com futureInteger iniciada com a Thread:  " + Thread.currentThread().getName().toUpperCase() +" O valor de futureInteger é: "+ futureInteger.get(1, TimeUnit.MICROSECONDS));
            System.out.println(futureString.get());

            System.out.println("\nTask com futureString terminada? " + futureString.isDone());
            System.out.println("Task com futureInteger terminada? " + futureInteger.isDone());


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (Objects.nonNull(executor)) {
                executor.shutdownNow();
            }
        }
    }

    public static class MyCallable implements Callable<String> {

        @Override
        public String call() throws InterruptedException {
            return "Task com futureString iniciada com a Thread:  " + Thread.currentThread().getName().toUpperCase();
        }
    }
}
