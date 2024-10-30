package volatileAndYeld;

public class VolatileAndYieldExample1 {

    private static int number;
    private static boolean prepare;

    public static void main(String[] args) {

        Thread thread0 = new Thread(new Runnable5() {});
        thread0.start();
        number = 42;
        prepare = true;
    }

    public static class Runnable5 implements Runnable {
        @Override
        public void run() {
            while (!prepare) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
}
