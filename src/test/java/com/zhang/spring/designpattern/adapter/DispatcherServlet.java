/**
 * 文件名：com.zhang.DispatcherServlet.java<br/>
 * 创建时间：2016年6月6日 下午4:58:04<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.spring.designpattern.adapter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zhang.spring.designpattern.adapter.controadapter.HandlerAdapter;
import com.zhang.spring.designpattern.adapter.controadapter.HttpControllerAdapter;
import com.zhang.spring.designpattern.adapter.controadapter.SimpleControllerAdapter;
import com.zhang.spring.designpattern.adapter.controller.HttpController;

public class DispatcherServlet {
	//TODO 记得要写注释，方便别人，成就自己。
	
	private static List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();
	
	public DispatcherServlet(){
		init();
	}
	
	public void init(){
		handlerAdapters.add(new HttpControllerAdapter());
		handlerAdapters.add(new SimpleControllerAdapter());
	}
	
	public void doDispatch(){
		//SimpleController handler = new SimpleController();
		HttpController handler = new HttpController();
		try {
			HandlerAdapter ha = getHandlerAdapter(handler);
			ha.handler(handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected HandlerAdapter getHandlerAdapter(Object handler) throws Exception{
		for(HandlerAdapter ha : handlerAdapters){
			if(ha.supports(handler)){
				return ha;
			}
		}
		throw new Exception("No adapter for handler [" + handler +
				"]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
	}
	@Test
	public void test(){
		new DispatcherServlet().doDispatch();
	}
}

