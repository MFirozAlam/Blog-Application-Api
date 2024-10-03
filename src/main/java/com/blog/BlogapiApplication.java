package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogapiApplication {

	public static void main(String[] args) {
		System.out.println("Project started successfully");
		SpringApplication.run(BlogapiApplication.class, args);
		System.out.println("Project finished successfully");
	}

}
