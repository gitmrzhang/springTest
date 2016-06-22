/**
 * 文件名：com.zhang.designpattern.proxy.SpringProxyTest.java<br/>
 * 创建时间：2016年6月20日 下午3:06:14<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.designpattern.proxy;

import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhang.module.bean.UserVo;
import com.zhang.module.login.ILoginService;

/**
 * java.lang.ClassCastException: com.sun.proxy.$Proxy18 cannot be cast to com.zhang.module.login.LoginService
 * loginService 存在 @Transactional 声明式事务，采用Aop实现，所以会产生代理对象，默认采用的是 JDK 代理 是基于接口的 ，
 * 这个代理对象则是实现接口的类，也是该类的父类，强制类型转换会出错 
 * (LoginService)ac.getBean("loginService"); 不能强制准换成LoginService
 */

public class SpringProxyTest {
	
	@Test
	public void test(){
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
		ILoginService login=(ILoginService)ac.getBean("loginService");
		UserVo u = login.getUser("zhang");
		System.out.println(u.toString());
	}
	
	@Test
	public void test2(){
		int a[]={8,2,1,0,3};
		int index[]={2,0,3,2,4,0,1,3,2,3,3};
		String tel = "";
		for(int i : index){
			tel  = tel+a[i];
		}
		System.out.println(tel);
	}

}

