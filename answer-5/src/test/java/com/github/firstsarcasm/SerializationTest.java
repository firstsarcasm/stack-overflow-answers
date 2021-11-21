package com.github.firstsarcasm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializationTest {

	@Test
	void test() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Date date = new Date(1637493733644L);
		User user = new User("user", date);
		String userJson = objectMapper.writeValueAsString(user);
		assertEquals("{\"id\":null,\"user\":\"user\",\"date\":\"2021-11-21\"}", userJson);
		assertEquals("Sun Nov 21 14:22:13 MSK 2021", user.date.toString());
	}

}
