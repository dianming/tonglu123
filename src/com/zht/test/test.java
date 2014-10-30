package com.zht.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test {
	@Before
	public void init(){
		System.out.println("初始化");
	}
	
	@After
	public void end(){
		System.out.println("结束");
	}
	
	@Test
	public void admifsadfn1(){
		System.out.println("1111111");
	}
	@Test
	public void bdmin(){
		System.out.println("222222222");
	}
	@Test
	public void cesasft3(){
		System.out.println("3333333333");
	}
	@Test
	public void deafsdfst4(){
		System.out.println("4444444444");
	}
	@Test
	public void eeefasst5(){
		System.out.println("5555555555");
		
	}
	@Test
	public void fwergfdg(){
		System.out.println("666666666");
		
	}
	
	@Test
	public void zAdmin(){
		System.out.println("777777");
		
	}
	
	@Test
	public void zZser(){
		System.out.println("88888888");
		
	}
}
