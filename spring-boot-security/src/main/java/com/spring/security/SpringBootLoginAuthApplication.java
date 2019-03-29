package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com"})
@EnableAutoConfiguration
public class SpringBootLoginAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLoginAuthApplication.class, args);
	}

}
