package volatileAndYeld;

import java.util.ArrayList;
import java.util.List;

public class VolatileAndYieldExample2 {

    private static  volatile int number;
    private static  volatile boolean prepare;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            Thread thread0 = new Thread(new Runnable6() {
            }, "thread-Silas e victoria");
            thread0.start();
            Thread thread1 = new Thread(new Runnable6() {
            }, "thread-Rarik");
            thread1.start();
            Thread thread2 = new Thread(new Runnable6() {
            }, "thread-Le");
            thread2.start();

            number = 42;
            prepare = true;
            System.out.println("Deu bom...  o valor de number é: " + number + " e o nome da Thread é: " + Thread.currentThread().getName());


            List<Thread> threads = List.of(thread0, thread1, thread2);
            List<Boolean> printed = new ArrayList<>(List.of(false, false, false));

            while (threads.stream().anyMatch(t -> t.getState() != Thread.State.TERMINATED)) {

                for (int i = 0; i < threads.size(); i++) {
                    var thread = threads.get(i);

                    if (thread.getState() == Thread.State.TERMINATED && !printed.get(i)) {
                        printed.set(i, true);
                        System.out.println("A " + thread.getName() + " FINALIZOU!");
                    }
                }
            }
            number = 0;
            prepare = false;
        }
    }

    public static class Runnable6 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " EXECUTANDO...  \n");
            while (!prepare) {
                Thread.yield();
            }
            if (number != 42)
                throw new IllegalThreadStateException("Deu ruim...  o valor de number é: " + number + " e o nome da Thread é: " + Thread.currentThread().getName());
        }
    }
}
