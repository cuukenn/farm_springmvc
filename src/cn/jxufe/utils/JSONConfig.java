package cn.jxufe.utils;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JSONConfig {
	public static JsonConfig getJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new JsonValueProcessor() {
			private final String format = "yyyy-MM-dd";

			public Object processObjectValue(String key, Object value, JsonConfig arg2) {
				if (value == null)
					return "";
				if (value instanceof java.sql.Date) {
					String str = new SimpleDateFormat(format).format((java.sql.Date) value);
					return str;
				}
				return value.toString();
			}
			public Object processArrayValue(Object value, JsonConfig arg1) {
				return null;
			}

		});
		return jsonConfig;
	}

}
