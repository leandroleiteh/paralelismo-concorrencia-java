package synchronizedPack;

public class SynchronizeExample2 {


    static int i = -1;

    public static void main(String[] args) {

        var myRunnable2 = new MyRunnable2();

        Thread thread0 = new Thread(myRunnable2);
        Thread thread1 = new Thread(myRunnable2);
        Thread thread2 = new Thread(myRunnable2);
        Thread thread3 = new Thread(myRunnable2);
        Thread thread4 = new Thread(myRunnable2);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        //System.out.println(i);
    }

    public static class MyRunnable2 implements Runnable {
        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName());

            int j;
            synchronized (this) {
                i++;
                j = i * 2;
                System.out.println("Synchronized --> " + Thread.currentThread().getName());
            }

            double sumJ = Integer.sum(j, 10);
            double divideJ = sumJ / 2;
            System.out.println(Thread.currentThread().getName() + " <---> " + divideJ);
        }
    }
}
