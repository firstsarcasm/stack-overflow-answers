package com.github.firstsarcasm.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerHystrixController {

	@GetMapping("/process")
	@HystrixCommand(fallbackMethod = "doWork")
	public String doProcess() {
		String response = "This msg come for processes";
		int i = 10 / 0;
		return response;
	}

	public String doWork() {
		return "This msg coming from doWork()...!!";
	}
}
