package com.iluwater.componentobject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {
	// @SpringBootApplication requires a visible constructor.
	public TodoApplication() {}

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}
