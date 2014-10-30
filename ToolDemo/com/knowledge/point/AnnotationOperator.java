package com.knowledge.point;

import java.lang.reflect.Method;

public class AnnotationOperator {
	public static void main(String[] args) throws Exception{
		SayHiElement element = new SayHiElement();
		Method[] methods = SayHiElement.class.getDeclaredMethods(); //获取所有方法
		for (Method method : methods) {
			SayHiAnnotation annotationTemp = null;
			if((annotationTemp = method.getAnnotation(SayHiAnnotation.class))!=null){
				// 如果使用了我们的注解，我们就把注解里的"paramValue"参数值作为方法参数来调用方法
				method.invoke(element,annotationTemp.paramValue());
			}else{
				// 如果没有使用我们的注解，我们就需要使用普通的方法来调用
				method.invoke(element, "xxx");
			}
		}
	}
}
