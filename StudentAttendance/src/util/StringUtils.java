package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtils {
	
	private static final String ALPHA_NUMERIC_STRING = "0123456789";

	public static String random(int num) {
		int count = num;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public static String getFileExtension(String fileName) {
	    int lastIndexOf = fileName.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return "";
	    }
	    return fileName.substring(lastIndexOf);
	}
	
	public static String encodeUrl(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decodeUrl(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
