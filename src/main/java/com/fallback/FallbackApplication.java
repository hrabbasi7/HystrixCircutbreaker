package com.fallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class FallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FallbackApplication.class, args);
	}
}
