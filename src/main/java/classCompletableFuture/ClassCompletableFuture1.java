package classCompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ClassCompletableFuture1 {

    public static void main(String[] args) {

        CompletableFuture<String> process1 = process();

        var process2 = process1.thenApply(s -> s + "\n2° Chamada");

        process2.thenAccept(System.out::println);

        System.out.println("Gratidão ao Java\n");

        sleep();
        sleep();
    }

    private static CompletableFuture<String> process() {
       return CompletableFuture.supplyAsync(() -> {
           sleep();
           return  "1° Chamada";
        });
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
