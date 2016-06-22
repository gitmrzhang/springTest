/**
 * 文件名：com.zhang.designpattern.proxy.CglibProxy.java<br/>
 * 创建时间：2016年6月22日 下午4:36:17<br/>
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

import java.lang.reflect.Method;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
	
	private Object target;
	
	public Object getCglibProxy(Object target){
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());// 设置代理目标
        enhancer.setCallback(this);// 设置回调
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
	}


	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		preHandler();
		Object result = proxy.invoke(target, args);
		afterHandler();
		return result;
	}
	
	void preHandler(){
		System.out.println("preHandler");
	}
	void afterHandler(){
		System.out.println("afterHandler");
	}
	
	@Test
	public void test(){
		CglibProxy proxy = new CglibProxy();
		UserServiceImpl targetProxy = (UserServiceImpl)proxy.getCglibProxy(new UserServiceImpl());
		targetProxy.add();
	}

}

