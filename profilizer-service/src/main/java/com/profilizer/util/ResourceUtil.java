package com.profilizer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.profilizer.ProfilizerServiceApplication;

public class ResourceUtil {
	
	public static String loadResource(String name) throws IOException {
		BufferedReader reader = null;
		try
		{
			InputStream stream = ProfilizerServiceApplication.class.getResourceAsStream(name);
			reader = new BufferedReader(new InputStreamReader(stream));
			return reader.lines().collect(Collectors.joining());
		}
		finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
}
