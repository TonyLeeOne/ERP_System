package com.tony.blog.utils;

import java.util.*;

public abstract class ListUtils {

    public static <T> List<T> difference(List<? extends T> list1, List<? extends T> list2) {
        ArrayList<T> result = new ArrayList(list1);
        Iterator iterator = list2.iterator();

        while(iterator.hasNext()) {
            result.remove(iterator.next());
        }

        return result;
    }

    public static <E> Set<E> asSet(E[] elements) {
        if (elements != null && elements.length != 0) {
            if (elements.length == 1) {
                return Collections.singleton(elements[0]);
            } else {
                LinkedHashSet<E> set = new LinkedHashSet(elements.length * 4 / 3 + 1);
                Collections.addAll(set, elements);
                return set;
            }
        } else {
            return Collections.emptySet();
        }
    }

    public static <E> List<E> asList(E[] elements) {
        return elements != null && elements.length != 0 ? Arrays.asList(elements) : Collections.emptyList();
    }
}
