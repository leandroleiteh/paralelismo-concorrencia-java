  package classCyclicBarrier;

import java.util.concurrent.*;

  public class ClassCyclicBarrier2 {

    private static BlockingQueue<Double> results = new LinkedBlockingQueue<>();


    // (423*3) + (3¨14) + (45*127/12) = ?
    public static void main(String[] args) {

        Runnable finalization = () -> {
            System.out.println("Somando tudo.");
            var sum = 0d;
            sum += results.poll();
            sum += results.poll();
            sum += results.poll();
            System.out.println("Resultado da equação: " + sum);
        };

        var executor = Executors.newFixedThreadPool(3);
        var cyclicBarrier = new CyclicBarrier(3,finalization);



        Runnable r1 = () -> {
            System.out.println("Comecei " + Thread.currentThread().getName());
            results.add(423d*3d);
            await(cyclicBarrier);
            System.out.println("Terminei " + Thread.currentThread().getName());
        };

        Runnable r2 = () -> {
            System.out.println("Comecei " + Thread.currentThread().getName());
            results.add(Math.pow(3d, 14d));
            await(cyclicBarrier);
            System.out.println("Terminei " + Thread.currentThread().getName());
        };

        Runnable r3 = () -> {
            System.out.println("Comecei " + Thread.currentThread().getName());
            results.add(45d*127d/12d);
            await(cyclicBarrier);
            System.out.println("Terminei " + Thread.currentThread().getName());
        };

        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);

        executor.shutdown();

    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
