package com.knowledge.point;

public class SayHiElement {
	// 普通方法
	public void sayHiDefault(String name) {
		System.out.println("Hi," + name);
	}

	// 使用注解并传入参数方法
	@SayHiAnnotation(paramValue = "Jack")
	public void sayHiAnnotation(String name) {
		System.out.println("Hi," + name);
	}

	// 使用注解默认值
	@SayHiAnnotation
	public void sayHiAnnotationDefault(String name) {
		System.out.println("Hi," + name);
	}
}
