/**
 * 文件名：com.zhang.designpattern.proxy.UserServiceImpl.java<br/>
 * 创建时间：2016年6月20日 下午2:42:13<br/>
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
public class UserServiceImpl implements UserService {

	@Override
	public void add() {
		System.out.println("add");
	}

	@Override
	public void delete() {
		System.out.println("delete");
	}

	@Override
	public void addAnddelete() {
		add();
		delete();
	}

}

