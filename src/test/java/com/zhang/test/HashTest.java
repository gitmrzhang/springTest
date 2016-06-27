/**
 * 文件名：com.zhang.test.HashTest.java<br/>
 * 创建时间：2016年6月24日 上午10:45:29<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.test;

import java.util.Date;

import org.junit.Test;

public class HashTest {
	
	@Test
	public void test1(){
		
		Long a = System.currentTimeMillis();
		String a2 ="1466757221704";
		Date date = new Date();
		System.out.println(date.getTime()%1024);
		System.out.println(a2.length());
		System.out.println(a.hashCode());
		
	}

}

