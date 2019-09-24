package bdkosher.selist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

/**
 * For assessing List API method available for the lists returned by different single-item list approaches.
 */
public class ApiAssessmentHarness {

    static void can_set_first_element(List<String> list) {
        list.set(0, "bar");
    }

    static void can_add_elements(List<String> list) {
        list.add("bar");
    }

    static void can_add_elements_with_index(List<String> list) {
        list.add(0, "bar");
    }

    static void can_remove_element_at_index_0(List<String> list) {
        list.remove(0);
    }

    static void can_remove_element_by_value(List<String> list) {
        list.remove(list.get(0));
    }

    static void can_clear(List<String> list) {
        list.clear();
    }

    static void can_add_all(List<String> list) {
        list.addAll(List.of("bar", "baz"));
    }

    static void can_add_all_at_index(List<String> list) {
        list.addAll(0, List.of("bar", "baz"));
    }

    static void can_remove_all_when_containing(List<String> list) {
        list.removeAll(List.of(list.get(0)));
    }

    static void can_remove_all_when_not_containing(List<String> list) {
        list.removeAll(List.of("bar"));
    }

    static void can_sort(List<String> list) {
        list.sort(Comparator.<String>naturalOrder().reversed());
    }

    static void can_retain_all_exists(List<String> list) {
        list.retainAll(List.of(list.get(0)));
    }

    static void can_retain_all_not_exists(List<String> list) {
        list.retainAll(List.of("bar"));
    }

    static void can_sub_list(List<String> list) {
        list.subList(0, 0);
    }

    static void can_remove_via_iterator(List<String> list) {
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.remove();
    }

    static void can_set_via_list_iterator(List<String> list) {
        ListIterator<String> iterator = list.listIterator();
        iterator.next();
        iterator.set("bar");
    }

    static void can_replace_all(List<String> list) {
        list.replaceAll(s -> "bar");
    }

    private static void tryMethod(SingleItemListCreationApproach type, Method method) {
        List<String> list = type.toList("foo");
        try {
            method.invoke(null, list);
            System.out.println("\t" + method.getName());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof UnsupportedOperationException == false) {
                System.out.println("\t" + method.getName() + " supported but failed due to "
                        + cause.getClass().getSimpleName() + ": " + cause.getMessage());
            }
        }
    }

    public static void main(String... args) {
        Stream.of(SingleItemListCreationApproach.values()).forEach(approach -> {
                    System.out.println(approach);
                    Arrays.stream(ApiAssessmentHarness.class.getDeclaredMethods())
                            .filter(method -> method.getName().startsWith("can"))
                            .forEach(method -> tryMethod(approach, method));
                }
        );
    }
}
