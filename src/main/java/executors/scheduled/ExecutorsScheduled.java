package executors.scheduled;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsScheduled {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var executorsScheduled = Executors.newScheduledThreadPool(3);

        var futureCallable = executorsScheduled.schedule(() -> Thread.currentThread().getName(), 1, TimeUnit.SECONDS);

        System.out.println("A Thread Callable é: " + futureCallable.get());

        executorsScheduled.scheduleAtFixedRate(() -> System.out.println("A Thread Runnable é: " + Thread.currentThread().getName()), 3,2, TimeUnit.SECONDS);

//        executorsScheduled.scheduleAtFixedRate();
//        executorsScheduled.scheduleWithFixedDelay();
//        executorsScheduled.schedule();


//        executorsScheduled.shutdown(); non repeat the Thread with scheduled
    }
}
