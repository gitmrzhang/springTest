/**
 * 文件名：com.zhang.designpattern.Adapter.java<br/>
 * 创建时间：2016年6月6日 下午1:37:10<br/>
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
 * 适配器模式  通过类之间的继承来实现
 */
public class ClassAdapter extends Adaptee implements Target {

	@Override
	public void request() {
		super.specificRequest();
	}
	
	@Test
	public void test(){
		Target target = new ClassAdapter();
		target.request();
	}
	
}

//目标接口 
interface Target{
	public void request();
}

//特殊的功能  但是不符合接口的标准
class Adaptee{
	public void specificRequest(){
		System.out.println("Adaptee specificRequest");
	}
}



