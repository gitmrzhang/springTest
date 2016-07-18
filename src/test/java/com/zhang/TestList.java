/**
 * 文件名：com.zhang.TestList.java<br/>
 * 创建时间：2016年6月28日 下午6:07:13<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestList {
	
	public List<?> sub(List<?> list ,int limit){
		list = list.subList(1 , limit);
		return list;
	}
	
	@Test
	public void test(){
		List<String> a = new ArrayList<String>();
		a.add("zhang");
		a.add("zhang2");
		a.add("zhang3");
		a.add("zhang4");
		a.add("zhang5");
		TestList t = new TestList();
		a=(List<String>) t.sub(a, 2);
		System.out.println(a.size());
		a= a.subList(1,2);
		System.out.println(a.size());
	}
	@Test
	public void test2(){
//		String a = "6075520,6075521";
//		String [] a1= a.split(",");
//		System.out.println(a1.length);
//		
		String a = "";
		Integer b = (a == null||"".equals(a) )? null:Integer.parseInt(a);
		System.out.println(b.intValue());
	}
	@Test
	public void test3(){
		System.out.println(Long.parseLong(null));
	}

}

