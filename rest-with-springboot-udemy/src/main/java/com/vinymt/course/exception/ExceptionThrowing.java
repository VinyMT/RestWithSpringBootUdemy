package com.vinymt.course.exception;

import com.vinymt.course.verifications.Verifications;

public class ExceptionThrowing {
	public static void throwExceptionIfNotNumericOneParam(String number) {
		if(!Verifications.isNumeric(number)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
	}
	
	public static void throwExceptionIfNotNumericTwoParam(String numberOne, String numberTwo) {
		if(!Verifications.isNumeric(numberOne) || !Verifications.isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
	}
}
