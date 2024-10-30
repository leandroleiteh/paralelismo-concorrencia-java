  package classCyclicBarrier;

import java.util.concurrent.*;

  public class ClassCyclicBarrier3 {

    private static BlockingQueue<Double> results = new LinkedBlockingQueue<>();
    private static Runnable r1;
    private static Runnable r2;
    private static Runnable r3;
    private static Runnable finalization ;
    private static double sum = 0d;

    // (423*3) + (3¨14) + (45*127/12) = ?
    public static void main(String[] args) {

        finalization = () -> {
            System.out.println("\n Somando tudo.");
            sum += results.poll();
            sum += results.poll();
            sum += results.poll();
            System.out.println("Resultado da equação: " + sum);
            System.out.println("----------------------------------");
            sleep();
        };

        var executor = Executors.newFixedThreadPool(3);
        var cyclicBarrier = new CyclicBarrier(3,finalization);



        r1 = () -> {
            while (true) {
                results.add(423d * 3d);
                await(cyclicBarrier);
            }
        };

        r2 = () -> {
            while (true) {
                results.add(Math.pow(3d, 14d));
                await(cyclicBarrier);
            }
        };

        r3 = () -> {
            while (true) {
                results.add(45d * 127d / 12d);
                await(cyclicBarrier);
            }
        };

        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);

        //executor.shutdown();

    }

    private static void sleep() {
        try {
        Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
