/**
 * 文件名：com.zhang.designpattern.proxy.ProxyTargetImpl.java<br/>
 * 创建时间：2016年6月20日 下午2:15:54<br/>
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

public class ProxyTargetImpl implements Target {
	
	private TargetImpl impl;

	@Override
	public void title() {
		impl = new TargetImpl();
		impl.title();
	}
	
	@Test
	public void test(){
		ProxyTargetImpl im = new ProxyTargetImpl();
		im.title();
	}

}

