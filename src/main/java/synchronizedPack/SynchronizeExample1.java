package synchronizedPack;

public class SynchronizeExample1 {

    static int i = -1;

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        var runnable = new MyRunnable();

//        for(int i =0; i< 5; i++){
//            runnable.run();
//        }

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();





    }

    //    public synchronized static void printInScreen() {
    public static synchronized void printInScreen() {

            i++;
            System.out.println(Thread.currentThread().getName() + " <--> " + i + " ::: " + System.currentTimeMillis());



    }

    public static class MyRunnable implements Runnable {

        static final Object lock1 = new Object();
        static final Object lock2 = new Object();

//        public synchronized void run() {
        @Override
        public void run() {


//            //printInScreen();
//               synchronized (this) {
//                    i++;
//                   System.out.println(Thread.currentThread().getName() + " <--> " + i + " ::: " + System.currentTimeMillis());
//                }

           synchronized (lock1) {
                i++;
               System.out.println(Thread.currentThread().getName() + " <--> " + i + " ::: " + System.currentTimeMillis());
           }
            synchronized (lock2) {
               i++;
           }
        }
    }


}
