package collectionsSynchronized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SynchronizedCollectionsExample1 {

    private static List<String> list = new ArrayList<String>();

    public static void main(String[] args) throws InterruptedException {

        list = Collections.synchronizedList(list);

//        list = Collections.synchronizedCollection(list);
//        list = Collections.synchronizedSet(list);
//        list = Collections.synchronizedMap(list);

        var runnable3 = new MeuRunnable3();

        Thread thread0 = new Thread(runnable3);
        Thread thread1 = new Thread(runnable3);
        Thread thread2 = new Thread(runnable3);

        thread0.start();
        thread1.start();
        thread2.start();

        Thread.sleep(300);
        System.out.println(list);
    }

    public static class MeuRunnable3 implements Runnable {

        @Override
        public void run() {
            list.add("A");
            System.out.println(Thread.currentThread().getName() + " Inseriu na lista");
        }
    }

}
