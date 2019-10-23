package com.lisz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 总结：以前框架设计必须考虑：扩展性！！！！
 * 1.抽象（多态）
 * 2.设计模式
 * Spring 给出了扩展
 * 1.在对象创建之前干点事
 * 2.在容器初始化之前干点事
 * 3.在不同的阶段发出不同的事件，你还可以干点事
 * 4.抽象出各个接口为所欲为
 * 5.面向接口编程 （类似javax.xxx.xxx, jdbs, service, ejb, jmx, jms, rmi, 全都是接口（借口）找别人实现好甩锅。。。）
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
		Field[] fields = clazz.getFields();
		Arrays.asList(fields).stream().forEach(System.out::println);
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
    public void test4() throws Exception { // Spring IOC 核心代码
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		Field field = clazz.getDeclaredField("userService");
		String name = field.getName();
		name = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
		Method method = clazz.getMethod(name, UserService.class); // 各个参数的类型
		method.invoke(userController, new UserService());
		System.out.println(userController.getUserService());
		// 打印结果：
		// com.lisz.UserService@1efbd816
	}
	
	@Test
    public void test5() throws Exception { // Spring IOC + @Autowired 核心代码
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		Field field = clazz.getDeclaredField("userService");
		if (field.isAnnotationPresent(Autowired.class)) {
			String name = field.getName();
			name = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Method method = clazz.getMethod(name, UserService.class); // 各个参数的类型
			method.invoke(userController, new UserService());
		}
		System.out.println(userController.getUserService());
		// 打印结果：
		// com.lisz.UserService@4de8b406
	}
	
	@Test
    public void test6() throws Exception { // Spring IOC + @Autowired 核心代码
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Stream.of(fields).forEach(field -> {
			if (field.isAnnotationPresent(Autowired.class)) {
				field.setAccessible(true);
				Class<?> type = field.getType();
				try {
					//Spring会根据配置文件或者注解把这个类型的对象先放在容器Map<String, BeanDefination>中 key就是bean的ID，或者Map<Class, BeanDefination>，HashMap加载因子为0.75
					//在这里spring并不new而是从这两个map里面拿
					field.set(userController, type.getConstructor().newInstance()); 
				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(userController.getUserService());
		// 打印结果：
		// com.lisz.UserService@7c0e2abd
	}
}
