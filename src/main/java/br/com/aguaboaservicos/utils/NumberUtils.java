package br.com.aguaboaservicos.utils;

public class NumberUtils {

	public static boolean isEmpty(Number n) {
		if (n == null) {
			return true;
		}
		
		if (n.doubleValue() == 0d) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isNotEmpty(Number n) {
		return !isEmpty(n);
	}

}
