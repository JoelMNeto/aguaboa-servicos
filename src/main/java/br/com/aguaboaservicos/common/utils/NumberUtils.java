package br.com.aguaboaservicos.common.utils;

public class NumberUtils {

    public static boolean isEmpty(Number n) {
        if (n == null) {
            return true;
        }

        return n.doubleValue() == 0d;
    }

    public static boolean isNotEmpty(Number n) {
        return !isEmpty(n);
    }

}
