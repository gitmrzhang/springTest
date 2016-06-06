/**
 * 文件名：com.zhang.spring.designpattern.adapter.controadapter.SimpleControllerAdapter.java<br/>
 * 创建时间：2016年6月6日 下午5:03:13<br/>
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


import com.zhang.spring.designpattern.adapter.controller.HttpController;

public class HttpControllerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof HttpController);
	}

	@Override
	public void handler(Object handler) {
		((HttpController) handler).handle();
	}
}

