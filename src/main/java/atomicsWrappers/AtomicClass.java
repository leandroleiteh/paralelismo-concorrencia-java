package atomicsWrappers;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;


public class AtomicClass {

    static AtomicInteger atomicInteger = new AtomicInteger();
    static AtomicLong atomicLong = new AtomicLong();
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    static AtomicReference<String> atomicReference = new AtomicReference<>("Atomic Reference");

    public static void main(String[] args) throws InterruptedException {

        var runnable4 = new MeuRunnable4();

        Thread thread0 = new Thread(runnable4);
//        Thread thread1 = new Thread(runnable4);
//        Thread thread2 = new Thread(runnable4);

        thread0.start();
//        thread1.start();
//        thread2.start();

    }

    public static class MeuRunnable4 implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ::: " + atomicInteger.incrementAndGet());
            System.out.println(Thread.currentThread().getName() + " ::: " + atomicLong.incrementAndGet());
            System.out.println(Thread.currentThread().getName() + " ::: " + atomicBoolean.compareAndExchange(false, true));
            System.out.println(Thread.currentThread().getName() + " ::: " + atomicReference.accumulateAndGet("-", (s1, s2) -> s1 + s2));
        }
    }
}
