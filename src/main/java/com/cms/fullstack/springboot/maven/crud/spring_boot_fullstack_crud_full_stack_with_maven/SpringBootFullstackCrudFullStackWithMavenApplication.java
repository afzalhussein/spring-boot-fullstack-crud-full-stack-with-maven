package com.cms.fullstack.springboot.maven.crud.spring_boot_fullstack_crud_full_stack_with_maven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFullstackCrudFullStackWithMavenApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootFullstackCrudFullStackWithMavenApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootFullstackCrudFullStackWithMavenApplication.class, args);
		logger.info("Application Started");
	}

}
