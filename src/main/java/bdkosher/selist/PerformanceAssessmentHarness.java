package bdkosher.selist;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class PerformanceAssessmentHarness {

    private static final Object ITEM = LocalDateTime.now();

    @State(Scope.Benchmark)
    public static class Lists {
        volatile List<Object> SINGLETON_LIST = Collections.singletonList(ITEM);
        volatile List<Object> LIST_OF = List.of(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void addToArrayList() {
        SingleItemListCreationApproach.ADD_TO_ARRAY_LIST.toList(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void anonymousArrayList() {
        SingleItemListCreationApproach.ANONYMOUS_ARRAY_LIST.toList(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void arraysAsList() {
        SingleItemListCreationApproach.ARRAYS_AS_LIST.toList(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void collectionsSingletonList() {
        SingleItemListCreationApproach.COLLECTIONS_SINGLETON_LIST.toList(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void listOf() {
        SingleItemListCreationApproach.LIST_OF.toList(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void streamAndCollectToList() {
        SingleItemListCreationApproach.STREAM_AND_COLLECT_TO_LIST.toList(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void streamAndCollectToUnmodifiableList() {
        SingleItemListCreationApproach.STREAM_AND_COLLECT_TO_UNMODIFIABLE_LIST.toList(ITEM);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void getFromCollectionsSingletonList(Lists lists) {
        lists.SINGLETON_LIST.get(0);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void getFromListOf(Lists lists) {
        lists.LIST_OF.get(0);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void collectionsSingletonListIterator(Lists lists) {
        lists.SINGLETON_LIST.listIterator();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void listOfIterator(Lists lists) {
        lists.SINGLETON_LIST.listIterator();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void collectionsSingletonListIteratorGet(Lists lists) {
        lists.SINGLETON_LIST.listIterator().next();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void listOfIteratorGet(Lists lists) {
        lists.SINGLETON_LIST.listIterator().next();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PerformanceAssessmentHarness.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
