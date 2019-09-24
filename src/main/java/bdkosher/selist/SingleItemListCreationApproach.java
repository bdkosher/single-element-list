package bdkosher.selist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An API Choice represents a means of creating a list containing a single item of some parameterized type.
 */
public enum SingleItemListCreationApproach {
    ADD_TO_ARRAY_LIST {
        @Override
        <T> List<T> toList(T item) {
            List<T> list = new ArrayList<>();
            list.add(item);
            return list;
        }
    },
    ANONYMOUS_ARRAY_LIST {
        @Override
        <T> List<T> toList(T item) {
            return new ArrayList<>() {{
                this.add(item);
            }};
        }
    },
    ARRAYS_AS_LIST {
        @Override
        <T> List<T> toList(T item) {
            return Arrays.asList(item);
        }
    },
    LIST_OF {
        @Override
        <T> List<T> toList(T item) {
            return List.of(item);
        }
    },
    COLLECTIONS_SINGLETON_LIST {
        @Override
        <T> List<T> toList(T item) {
            return Collections.singletonList(item);
        }
    },
    STREAM_AND_COLLECT_TO_LIST {
        @Override
        <T> List<T> toList(T item) {
            return Stream.of(item).collect(Collectors.toList());
        }
    },
    STREAM_AND_COLLECT_TO_UNMODIFIABLE_LIST {
        @Override
        <T> List<T> toList(T item) {
            return Stream.of(item).collect(Collectors.toUnmodifiableList());
        }
    };

    /**
     * Create a list containing the given item.
     *
     * @param item the one and only list item
     * @param <T>  the arbitrary type of the item
     * @return a List
     */
    abstract <T> List<T> toList(T item);
}
