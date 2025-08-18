package com.junior.boletapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BoletappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoletappApplication.class, args);
	}

}
