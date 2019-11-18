package com.example.demo.common.utils;

public class UniqueIdUtils {

	public static Long getId(){  
		String trandStr = String.valueOf((Math.random() * 9 + 1) * 1000000);
		String numStr = trandStr.toString().substring(0, 7);
		return Long.parseLong(numStr);
	}
}
