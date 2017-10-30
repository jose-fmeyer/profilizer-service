package com.profilizer.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationUtils {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static String serialize(Object value) throws IOException {
		return (value != null) ? objectMapper.writeValueAsString(value) : null;
	}

	public static <T> T deserialize(String value, Class<T> valueType) throws IOException {
		return (value != null) ? objectMapper.readValue(value, valueType) : null;
	}
}
