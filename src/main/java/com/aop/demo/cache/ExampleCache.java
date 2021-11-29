package com.aop.demo.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

class ExampleCache {
	private Map<String, StringBuffer> cache;

	private static ExampleCache instance;

	static Map<String, StringBuffer> getCache() {
		return getInstance().getIntanceCache();
	}

	private Map<String, StringBuffer> getIntanceCache() {
		if (cache == null) {
			synchronized (this) {
				if (cache == null)
					cache = new ConcurrentSkipListMap<>();
			}
		}

		return cache;
	}

	private static ExampleCache getInstance() {
		if (instance == null) {
			synchronized (ExampleCache.class) {
				if (instance == null)
					instance = new ExampleCache();
			}
		}

		return instance;
	}

}
