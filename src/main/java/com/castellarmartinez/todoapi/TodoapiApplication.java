package com.castellarmartinez.todoapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoapiApplication {
	private static final Logger logger = LoggerFactory.getLogger(TodoapiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoapiApplication.class, args);
		logger.info("Application has started");
	}

}
