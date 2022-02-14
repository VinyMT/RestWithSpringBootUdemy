package com.vinymt.course.converters;

public class MathConverter {

	public static Double convertToDouble(String number) {	
		String strNumber = number.replaceAll(",", ".");
		return Double.parseDouble(strNumber);
	}
	
}
