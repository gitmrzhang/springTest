/**
 * 文件名：com.zhang.designpattern.adapter.ObjectAdapter.java<br/>
 * 创建时间：2016年6月6日 下午2:35:50<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.designpattern.adapter;

import org.junit.Test;

/**
 * 适配器模式  通过对象的组合实现
 */
public class ObjectAdapter implements Target2{
	
	private Adaptee2 adaptee2 = new Adaptee2();
	

	@Override
	public void request() {
		this.adaptee2.specificRequest();
	}
	
	@Test
	public void test(){
		ObjectAdapter obj = new ObjectAdapter();
		obj.request();
	}
	
}

//目标接口 
interface Target2{
	public void request();
}

//特殊的功能  但是不符合接口的标准
class Adaptee2{
	public void specificRequest(){
		System.out.println("Adaptee specificRequest");
	}
}