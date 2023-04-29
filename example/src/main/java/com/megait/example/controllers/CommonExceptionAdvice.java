package com.megait.example.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

// @ExceptionHandler : 특정 하나의 익셉션만 대상
// @ControllerAdvice : Controller, 전역에서 발생할 수 있는 예외 처리
@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
	@ExceptionHandler(Exception.class)
	public String except(Exception e, Model model) {
		log.error("[Exception!!!] " + e.getMessage());
		model.addAttribute("exception", e);
		log.error(model.toString());
		return "error_page";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException e) {
		log.error("[Exception!!!] NoHandler Exception");
	return "error_page";
	}
}
