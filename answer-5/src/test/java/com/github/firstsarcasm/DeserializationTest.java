package com.github.firstsarcasm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeserializationTest {

	@Test
	void test() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		String json = "{\"name\":\"user\",\"date\":\"2021-11-21\"}";

		User2 user2 = objectMapper.readValue(json, User2.class);
		assertEquals("Sun Nov 21 00:00:00 MSK 2021", user2.date.toString());

	}

}
