package com.vinymt.course.verifications;

public class Verifications {
	public static boolean isNumeric(String number) {
		if(number == null) {
			return false;
		}

		String strNumber = number.replaceAll(",", ".");
		return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
