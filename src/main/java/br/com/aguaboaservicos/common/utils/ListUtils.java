package br.com.aguaboaservicos.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {

    public static <T> boolean isEmpty(List<T> l) {
        if (l == null) {
            return true;
        }

        List<T> aux = new ArrayList<>(l);
        aux.removeAll(Collections.singleton(null));

        return aux.isEmpty();
    }

    public static <T> boolean isNotEmpty(List<T> l) {
        return !isEmpty(l);
    }
}
