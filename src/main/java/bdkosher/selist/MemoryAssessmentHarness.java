package bdkosher.selist;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * For assessing memory usage by the various single-item list approaches.
 */
public class MemoryAssessmentHarness {

    private static final Map<SingleItemListCreationApproach, Vector<List<Integer>>> data = new EnumMap<>(SingleItemListCreationApproach.class);

    public static void main(String... args) throws IOException {
        String num = args.length > 0 && args[0].matches("\\d+") ? args[0] : null;
        int instances = num != null ? Integer.parseInt(num) : 100_000;

        Stream.of(SingleItemListCreationApproach.values()).forEach(approach ->
            data.put(approach, IntStream.rangeClosed(1, instances)
                    .boxed()
                    .map(approach::toList)
                    .collect(Collectors.toCollection(() -> new Vector<>(instances)))));

        System.out.println(instances + " single-items lists created using " + SingleItemListCreationApproach.values().length
                + " different approaches. Run jcmd to analyze memory usage.");
        System.in.read(); // keep thread and JVM process alive
    }
}
