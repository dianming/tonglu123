package com.zht.test;

import java.nio.charset.Charset;

public class geshi {
	public static void main(String[] args) throws Exception {
//		String gStr1 = new String("【运动智慧】".getBytes(), "iso-8859-1");
//		System.out.println(gStr1);
//
//		String gStr2 = new String("【运动智慧】".getBytes("GB2312"));
//		System.out.println(gStr2);
//
//		String gStr3 = new String(gStr1.getBytes("GB2312"));
//		System.out.println("---->" + gStr3);
//
//		String gStr = new String(gStr1.getBytes("iso-8859-1"));
//		System.out.println("---->" + gStr);
		
		byte[] b;
		String utf8_value = "中文";
		
		String a1 = new String(utf8_value.getBytes(),"utf-8");
		System.err.println(a1);
		
		String a2 = new String(utf8_value.getBytes("GB2312"),"GB2312");
		System.err.println(a2);
		
		String a3 = new String(utf8_value.getBytes("GB2312"),"UTF-8");
		System.err.println(a3);
		
		b = utf8_value.getBytes("GB2312");
		for (byte c : b) {
			System.out.println("--->"+c);
		}
		
		System.out.println("Default Charset=" + Charset.defaultCharset());  
		System.out.println("file.encoding=" + System.getProperty("file.encoding"));  
		
		
//		byte[] b1;
//		b1 = name.getBytes("8859_1"); // 中间用ISO-8859-1过渡
//		String name1 = new String(b1, "GB2312"); // 转换成GB2312字符
//		
//		System.out.println("是不是---》"+name1);
//		
//		System.out.println(new String(name1.getBytes(),"UTF-8"));

//		String a = new String("【运动智慧】".getBytes("utf-8"), "ASCII");
//		System.out.println("【运动智慧】".getBytes());
//		System.out.println(a);
	}
}
