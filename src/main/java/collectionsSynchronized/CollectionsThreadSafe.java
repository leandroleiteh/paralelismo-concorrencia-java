package collectionsSynchronized;

import java.util.concurrent.LinkedBlockingQueue;

public class CollectionsThreadSafe {

//    private static List<String> list = new CopyOnWriteArrayList<>();
//    private static Map<Integer, String> map = new ConcurrentHashMap<>();
//    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static LinkedBlockingQueue<String> dequeue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {

        var meuRunnable4 = new MeuRunnable4();

        Thread thread0 = new Thread(meuRunnable4);
        Thread thread1 = new Thread(meuRunnable4);
        Thread thread2 = new Thread(meuRunnable4);

        thread0.start();
        thread1.start();
        thread2.start();

        Thread.sleep(500);
//        System.out.println(list);
//        System.out.println(map);
//        System.out.println(queue);
        System.out.println(dequeue);
    }

    public static class MeuRunnable4 implements Runnable {

        @Override
        public void run() {
//            list.add("A");
//            map.put(new Random().nextInt(), "Insert");
//            queue.add("insert");
            dequeue.offer("insert");
//            System.out.println(Thread.currentThread().getName() + " Inseriu na lista");
//            System.out.println(Thread.currentThread().getName() + " Inseriu no mapa");
//            System.out.println(Thread.currentThread().getName() + " Inseriu na fila");
            System.out.println(Thread.currentThread().getName() + " Inseriu na fila dupla");
        }
    }
}
