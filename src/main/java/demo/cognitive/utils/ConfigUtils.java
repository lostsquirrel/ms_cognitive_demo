package demo.cognitive.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

	private static Properties prop;
	
	static {
		prop = new Properties();
		InputStream in = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}
	
	public static final String CS_API_KEY = "cs.api.key";
	
	public static final String CS_API_URL = "cs.api.url";
}
