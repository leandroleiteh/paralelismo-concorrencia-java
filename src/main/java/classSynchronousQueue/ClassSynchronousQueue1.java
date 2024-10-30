package classSynchronousQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class ClassSynchronousQueue1 {

    private static final SynchronousQueue<String> QUEUE = new SynchronousQueue<>();

    public static void main(String[] args) {

        ExecutorService executors = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            put();
        };

        Runnable r2 = () -> {
            var msg = take();
            System.out.println("Pegou da fila: " + msg);
        };


        executors.execute(r1);
        executors.execute(r2);

        executors.shutdown();

    }

    private static void put() {
        try {
            System.out.println("Adicionando na fila.... ");
            var content = "Content";
            QUEUE.put(content);
//            QUEUE.offer(content, 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String take() {
        try {
            return QUEUE.take();
//            return QUEUE.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Error";
        }
    }
}
