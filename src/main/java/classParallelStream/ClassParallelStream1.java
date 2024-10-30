package classParallelStream;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClassParallelStream1 {

    public static void main(String[] args) {

        Map<Double, Double> map = new ConcurrentHashMap<>();
        Instant start = Instant.now();
        IntStream.range(1, 10000000)
                .parallel()
                .mapToDouble(n -> n+1)
                .filter(i -> i % 2 == 0)
                .forEach(t -> {
                    map.put(t,Math.pow(t,5));
                    System.out.println(t);
                });
        System.out.println(Duration.between(start, Instant.now()).toSeconds());
    }
}
