/**
 * 文件名：com.zhang.designpattern.proxy.ProxyUserService.java<br/>
 * 创建时间：2016年6月20日 下午2:43:16<br/>
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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class ProxyUserService implements InvocationHandler {
	
	private UserService targetObject;
	
	public UserService createProxyInstance(UserService targetObject){
		this.targetObject=targetObject;
		return (UserService) Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(),
                this.targetObject.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("aop 开始日志"+System.currentTimeMillis());
		Object obj = method.invoke(targetObject, args);
		System.out.println("aop 结束日志"+System.currentTimeMillis());
		return obj;
	}
	@Test
	public void test(){
		ProxyUserService  s = new ProxyUserService();
		UserService proxy = s.createProxyInstance(new UserServiceImpl());
		proxy.add();
		proxy.delete();
		proxy.addAnddelete();
		
	}

}

