package com.ncr.util;

import java.util.regex.Pattern;

public class NcrUtil {
	private static final Pattern PATTERN = 
			Pattern.compile("\\([+-]?(90(\\.0+)?|([1-8][0-9]|[1-9])(\\.\\d+)?), [+-]?(180(\\.0+)?|(1[0-7][0-9]|[1-9][0-9]|[1-9])(\\.\\d+)?)\\)");
    
	public static boolean isValid(String coordinate) {
		return PATTERN.matcher(coordinate).matches();
	}
}