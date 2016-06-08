package com.zhang.core.mvc;

import org.junit.Test;

public class FrameworkServlet {
	
	public void onRefresh(){
		System.out.println("onRefresh");
	}
	@Test
	public void test1(){
		FrameworkServlet.this.onRefresh();
		this.onRefresh();
	}
}
