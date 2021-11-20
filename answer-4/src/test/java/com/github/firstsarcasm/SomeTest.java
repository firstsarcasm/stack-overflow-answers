package com.github.firstsarcasm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeTest {

	private static final String SPACE = " ";
	private static final String UPPER_CASE_REGEXP = "(?=\\p{Upper})";

	@Test
	@DisplayName("Should return a string in lower case and with space separator")
	void test() {
		assertEquals("how are you", split("HowAreYou"));
		assertEquals("i am fine", split("IAmFine"));
	}

	private String split(String str) {
		return Stream.of(str.split(UPPER_CASE_REGEXP))
				.map(String::toLowerCase)
				.collect(Collectors.joining(SPACE));
	}
}
