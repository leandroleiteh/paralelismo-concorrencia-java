package casesThreadsAndProblems;

import windown.Windown;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerAndConsumer1 {

    private static List<Integer> LIST = new ArrayList<>(5);
    private static boolean PRODUCING = true;
    private static boolean CONSUMING = true;

    public static void main(String[] args) {


        var producer = new Thread(() -> {
            while (true) {
                if (PRODUCING) {
                    System.out.println("----------PRODUZINDO...");
                    simulationProcess();
                    addElementsToList(LIST);

                }

                if (LIST.size() == 5) {
                    System.out.println("...PAUSANDO PRODUTOR");
                    PRODUCING = false;
                }
                if (LIST.size() == 1){
                    System.out.println("INICIANDO CONSUMIDOR----------");
                    CONSUMING = true;
                } else System.out.println("CONSUMIDOR DORMINDO $$$");
            }
        });

        var consumer = new Thread(() -> {
            while (true) {
                if (CONSUMING) {
                    System.out.println("----------CONSUMINDO...");
                    simulationProcess();
                    removeFirstElementList(LIST);
                }
                if (LIST.size() == 0) {
                    System.out.println("...PAUSANDO CONSUMIDOR");
                    CONSUMING = false;
                }
                if (LIST.size() == 4) {
                    System.out.println("INICIANDO PRODUTOR----------");
                    PRODUCING = true;
                } else System.out.println("PRODUTOR DORMINDO ###");
            }
        });

        producer.start();
        consumer.start();

        Windown.monitor(() -> String.valueOf(LIST.size()));

    }

    private static void addElementsToList(List<Integer> list) {
        var random = new Random().nextInt(1000);
        list.add(random);
    }

    private static void removeFirstElementList(List<Integer> list) {
        list.stream().findFirst().ifPresent(LIST::remove);
    }

    private static void simulationProcess() {
        var timeRamdom = new Random().nextInt(100);
        try {
            Thread.sleep(timeRamdom);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
