/**
 * 文件名：com.zhang.spring.designpattern.adapter.HandlerAdapter.java<br/>
 * 创建时间：2016年6月6日 下午4:59:27<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.spring.designpattern.adapter.controadapter;
public interface HandlerAdapter {
	
	boolean supports(Object handler); 
	
	void handler(Object handler);
}

