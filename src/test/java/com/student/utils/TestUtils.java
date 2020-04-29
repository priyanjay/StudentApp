package com.student.utils;

import java.util.Random;

public class TestUtils {
	
	public static String getRandomValues() {
		Random random=new Random();
		int randomInt=random.nextInt(10000);
		String s=Integer.toString(randomInt);
		return s;
	}
	
}
