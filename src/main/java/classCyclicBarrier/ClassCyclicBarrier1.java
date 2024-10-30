package classCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class ClassCyclicBarrier1 {


    // (423*3) + (3¨14) + (45*127/12) = ?
    public static void main(String[] args) {

        var executor = Executors.newFixedThreadPool(3);
        var cyclicBarrier = new CyclicBarrier(3);


        Runnable r1 = () -> {
            System.out.println(423d*3d);
            await(cyclicBarrier);
            System.out.println("Terminei");
        };

        Runnable r2 = () -> {
            System.out.println(Math.pow(3d, 14d));
            await(cyclicBarrier);
            System.out.println("Terminei");
        };

        Runnable r3 = () -> {
            System.out.println(45d*127d/12d);
            await(cyclicBarrier);
            System.out.println("Terminei");
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
            e.printStackTrace();
        }
    }
}
