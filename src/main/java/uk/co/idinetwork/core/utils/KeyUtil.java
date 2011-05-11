package uk.co.idinetwork.core.utils;

public class KeyUtil {
	public static String buildKey(String key) {
		String s = key.toLowerCase().replaceAll(" ", "-");
		s = s.replaceAll("\"", "");
		s = s.replaceAll("'", "");
		s = s.replaceAll("!", "");
		s = s.replaceAll(";", "");
		s = s.replaceAll(",", "");
		s = s.replaceAll("&", "");
		s = s.replaceAll(":", "");

		return s;
	}
}
