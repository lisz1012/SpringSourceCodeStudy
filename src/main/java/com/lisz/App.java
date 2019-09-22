package com.lisz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App {
	@Test
    public void test() {
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		Arrays.asList(declaredFields).stream().forEach(System.out::println);//有没有stream().都可以
		// 打印结果：
		// private com.lisz.UserService com.lisz.UserController.userService
	}
	
	@Test
    public void test2() {
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		Field[] declaredFields = clazz.getFields();
		Arrays.asList(declaredFields).stream().forEach(System.out::println);
		// 打印结果：
		// 
	}
	
	@Test
    public void test3() throws Exception {
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		Field userServiceField = clazz.getDeclaredField("userService");
		/*
		 * 必须得设置这个。编译的时候会把private放在flags中
		 */
		userServiceField.setAccessible(true);
		userServiceField.set(userController, new UserService());
		Field[] declaredFields = clazz.getDeclaredFields();
		Arrays.asList(declaredFields).stream().forEach(System.out::println);
		// 打印结果：
		// private com.lisz.UserService com.lisz.UserController.userService
	}
	
	@Test
    public void test4() throws Exception {
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		Field field = clazz.getDeclaredField("userService");
		String name = field.getName();
		name = "set" + name.substring(0,1).toUpperCase() + name.substring(1, name.length());
		Method method = clazz.getMethod(name, UserService.class); // 各个参数的类型
		method.invoke(userController, new UserService());
		System.out.println(userController.getUserService());
		// 打印结果：
		// com.lisz.UserService@1efbd816
	}
}
