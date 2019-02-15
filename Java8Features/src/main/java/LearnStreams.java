import java.util.Date;
//import java.util.HashMap;
import java.util.stream.Stream;

public class LearnStreams {
    String[] data = new String[] { "Chandra", "Mohan", "Balram", "Sathya", "Narayana", "Chandra" };

    private Stream<String> stringStream1 = Stream.of(data);
    private Stream<String> stringStream2 = Stream.of(data);
    private Stream<String> stringStream3 = Stream.of(data);
    private Stream<String> stringStream4 = Stream.of(data);

    private void processStreams() {
        System.out.printf("Start %s %n", new Date());
        stringStream1.distinct().forEach(element -> System.out.println(element.toUpperCase()));
        System.out.printf("End %s %n", new Date());
        stringStream2.distinct().parallel().forEach(element -> System.out.println(element.toUpperCase())); // Parallel

//        stringStream1.sorted()

//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.values().stream();


        // Returns Boolean:
        // stringStream3.anyMatch(element -> element.endsWith("a"));
        // stringStream4.allMatch(element -> element.contains("a"));

        // Filters out and gets elements satifying a condition
        stringStream3.filter(element -> element.contains("o")).forEach(element -> System.out.println(element));

        Stream<String> stringStream5 = stringStream4.map(element -> element.toUpperCase());
        stringStream5.forEach(element -> System.out.println(element));
    }

    public static void main(String[] args) {
        new LearnStreams().processStreams();
    }
}
