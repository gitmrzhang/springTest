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
import com.zhang.module.login.LoginService;

public class SpringProxyTest {
	
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
		LoginService login=(LoginService)ac.getBean("loginService");
		UserVo u = login.getUser("zhang");
		System.out.println(u.toString());
	}

}

