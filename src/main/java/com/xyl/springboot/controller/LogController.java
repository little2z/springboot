package com.xyl.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
		
	private Logger logger = LoggerFactory.getLogger(LogController.class);
	
	@GetMapping("/testLog")
	public String testLogLevel(){
		
		logger.debug("Logger level:debug");
		logger.info("Logger level:info");
		logger.error("Logger level:error");
		
		return null;
	}
	
}
