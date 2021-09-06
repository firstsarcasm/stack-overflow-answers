package com.github.firstsarcasm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * The answer to stackoverflow.com question:
 * https://stackoverflow.com/questions/69061913/beancreationexception-error-creating-bean-with-name-configurationpropertiesbea/69065600#69065600
 */
@EnableHystrix
@SpringBootApplication
@EnableCircuitBreaker
public class CircuitBreakerHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerHystrixApplication.class, args);
	}
}