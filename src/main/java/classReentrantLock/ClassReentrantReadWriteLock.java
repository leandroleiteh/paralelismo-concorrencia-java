package classReentrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClassReentrantReadWriteLock {

    private static int i = 0;
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            var writeLock = lock.writeLock();
            writeLock.lock();
            System.out.println("Escrevendo: " + i + " com a Thread: " + Thread.currentThread().getName());
            i++;
            System.out.println("Escrito: " + i + " com a Thread: " + Thread.currentThread().getName());
            writeLock.unlock();
        };

        Runnable r2 = () -> {
          var readLock = lock.readLock();
          readLock.lock();
            System.out.println("Lendo: " + i);
            System.out.println("Lido: " + i);
            readLock.unlock();
        };

        for (int j = 0; j < 6; j++) {
            executor.execute(r1);
            executor.execute(r2);
        }

        executor.shutdown();
    }
}
