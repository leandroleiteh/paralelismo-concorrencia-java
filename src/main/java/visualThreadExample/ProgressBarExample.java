package visualThreadExample;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class ProgressBarExample {

    static class Task implements Runnable {
        private String name;
        private int totalSteps;

        public Task(String name, int totalSteps) {
            this.name = name;
            this.totalSteps = totalSteps;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < totalSteps; i++) {
                    Thread.sleep(100);
                    synchronized (System.out) {
                        System.out.print(name + " [" + "#".repeat(i + 1) + "]\r");
                    }
                }

                synchronized (System.out) {
                    System.out.println("A Thread: " +Thread.currentThread().getName() +" está executando à " + name + " ########## Done!");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void runSingleThreadExecution() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var task = new Task("Task" + i, 10);
            tasks.add(task);
        }

        System.out.println("Execução em Single Thread:");

        tasks.forEach(Task::run);
    }

    private static void runMultiThreadExecution() throws InterruptedException {
        List<Task>  listTasks = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            var Task = new Task("Tarefa" + i, 10);
            listTasks.add(Task);
        }

        System.out.println("Execução em Multi Thread:");

        ExecutorService executor = Executors.newCachedThreadPool();

        List<Callable<Object>> callables = listTasks.stream()
                .map(Executors::callable)
                .collect(Collectors.toList());


        executor.invokeAll(callables);
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) throws InterruptedException {

        Instant startTime = Instant.now();
        runSingleThreadExecution();
        long singleThreadTime = Duration.between(startTime, Instant.now()).toSeconds();
        System.out.println("Tempo total Single Thread: " + singleThreadTime + "s\n");

        startTime = Instant.now();
        runMultiThreadExecution();
        long multiThreadTime = Duration.between(startTime, Instant.now()).toSeconds();
        System.out.println("Tempo total Multi Thread: " + multiThreadTime + "s\n");


        System.out.println("Multi Thread foi " + (singleThreadTime - multiThreadTime) + "s mais rápido.");
    }
}



