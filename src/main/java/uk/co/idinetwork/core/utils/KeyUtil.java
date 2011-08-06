package uk.co.idinetwork.core.utils;

import org.apache.commons.lang.StringUtils;

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

		s = StringUtils.replace(s, "?", "");
		s = StringUtils.replace(s, ".", "");
		
		return s;
	}
}
