package com.example.demo.common.utils;

import org.junit.Test;

public class UniqueIdUtilsTest {

	@Test
	public void test() {
		
		String numStr = "" ;       
		String trandStr = String.valueOf((Math.random() * 9 + 1) * 1000000);
		numStr = trandStr.toString().substring(0, 7);
		System.out.println(numStr);
		
	}

}
