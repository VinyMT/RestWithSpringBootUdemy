package com.vinymt.course.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinymt.course.converters.MathConverter;
import com.vinymt.course.exception.ExceptionThrowing;

@RestController
public class SqrtController {
	
	@RequestMapping(value="/squareRoot/{numberOne}", method=RequestMethod.GET)
	public Double squareRoot(@PathVariable("numberOne") String numberOne) throws Exception {
		ExceptionThrowing.throwExceptionIfNotNumericOneParam(numberOne);
		
		Double squareRoot = Math.sqrt(MathConverter.convertToDouble(numberOne));
		return squareRoot;
	}
}
