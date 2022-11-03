package com.shift4.util;

import com.shift4.Shift4Gateway;

import java.io.IOException;
import java.util.Properties;

public class Shift4Utils {

	private static final String BUILD_VERSION;

	static {
		Properties properties = new Properties();
		try {
			properties.load(Shift4Gateway.class.getResourceAsStream("build.properties"));
		} catch (IOException ignore) {
		}

		BUILD_VERSION = properties.getProperty("shift4java.build.version");
	}

	private Shift4Utils() {
	}

	public static String toStringNullSafe(Object o) {
		return o != null ? o.toString() : null;
	}

	public static String getBuildVersion() {
		return BUILD_VERSION;
	}

	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}
}
