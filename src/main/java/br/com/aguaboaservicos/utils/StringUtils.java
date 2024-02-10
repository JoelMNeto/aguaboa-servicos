package br.com.aguaboaservicos.utils;

public class StringUtils {

    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        return s.trim().isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isOnlyNumbers(String s) {
        if (isEmpty(s)) {
            return false;
        }

        return s.matches("[1-9]+");
    }
}
