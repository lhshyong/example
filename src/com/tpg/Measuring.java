package com.tpg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Measuring {
	
	public static void testCase(){
		String className = "com.tpg.Measuring";
		String method = "runSomething";
		String param1 = "This is string parameter";
		Integer param2 = 6;
		
		// measuring execution time by passin class name, method to measure and params needed.		
		long time = Measuring.measure(className, method, param1, param2);
		System.out.println("Elapse Time (nano second):" + time);		
	}

	public static long measure(String className, String method, Object... variables)  {		
		System.out.println("Test execution time for ...");
		System.out.println("Class:" + className);
		System.out.println("Method:" + method);

		long start = System.nanoTime();
		try {
			Class<?> target = Class.forName(className);
			Method m = target.getDeclaredMethod(method, Object[].class);
			m.invoke(target, new Object[]{variables});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}				 
		long end = System.nanoTime();
		return end - start;
	}
	
	public static void runSomething(Object... multipleParam){
		System.out.println("Executing method");
		System.out.println("Print out param...");
		for(Object obj : multipleParam){
			System.out.println("Param " + obj.getClass().getName() + ", value:" + obj.toString() );			
		}
		System.out.println("Delay execution for 3 seconds");		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Execution end");		
	}
}
