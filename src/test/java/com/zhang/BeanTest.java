/**
 * 文件名：com.zhang.BeanTest.java<br/>
 * 创建时间：2016年6月15日 下午3:31:35<br/>
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

import org.junit.Test;

public class BeanTest {
	
	private static final int a = 12;
	
	private String name;
	
	private int age = a;

	/**
	 * name属性的get方法
	 * @return the name
	 */
	public String getName() {
	
		return name;
	}

	/**
	 * name属性的set方法
	 * @param name the name to set
	 */
	public void setName(String name) {
	
		this.name = name;
	}

	/**
	 * age属性的get方法
	 * @return the age
	 */
	public int getAge() {
	
		return age;
	}

	/**
	 * age属性的set方法
	 * @param age the age to set
	 */
	public void setAge(int age) {
	
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "BeanTest [name=" + name + ", age=" + age + "]";
	}

	@Test
	public void test(){
		BeanTest t = new BeanTest();
		System.out.println(t.toString());
	}

}

