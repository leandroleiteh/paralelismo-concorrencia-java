package classExchanger;


import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;

public class ClassExachanger1 {

    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {

        var executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String nameThread1 = Thread.currentThread().getName();
            String msgParam1 = "Hi friends";
            System.out.println("A Thread: " + nameThread1 + ", escreveu a mensagem: " + msgParam1);
            System.out.println("A Thread: " + nameThread1 + ", leu a mensagem: " + exchange(msgParam1));
        };

        Runnable r2 = () -> {
            String nameThread2 = Thread.currentThread().getName();
            String msgParam2 = "Olá amigos";
            System.out.println("A Thread: " + nameThread2 + ", escreveu a mensagem: " + msgParam2);
            System.out.println("A Trhead: " + nameThread2 + ", leu a mensagem: " + exchange(msgParam2));
        };

        executor.execute(r1);
        executor.execute(r2);


    }

    private static String exchange(String msg) {
        try {
            return EXCHANGER.exchange(msg);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
