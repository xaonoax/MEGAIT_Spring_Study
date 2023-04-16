package com.megait.example.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomError implements ErrorController {
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest req) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		log.info("Very Small Error");
		
		if (status != null) {  // Error 발생
			int statusCode = Integer.valueOf(status.toString());
			
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/404";				
			}
			
		}
		return "error/500";
	}
}
