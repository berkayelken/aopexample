package com.aop.demo.cache;

import static com.aop.demo.cache.ExampleCache.getCache;

public final class ExampleCaches {

	public static void addStr(String key, String str) {
		if (!getCache().containsKey(key))
			getCache().put(key, new StringBuffer());

		StringBuffer buffer = getBuffer(key);
		buffer.append(str);
		getCache().put(key, buffer);
	}

	public static void removeBuffer(String key) {
		getCache().remove(key);
	}

	private static StringBuffer getBuffer(String key) {
		return getCache().get(key);
	}

	public static String getStr(String key) {
		return getBuffer(key).toString();
	}

}
