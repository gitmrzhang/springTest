/**
 * 文件名：com.zhang.module.login.ReflectObj.java<br/>
 * 创建时间：2016年5月11日 下午1:48:42<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.module.login;
public class ReflectObj {
	//TODO 记得要写注释，方便别人，成就自己。
	private String name ;
	
	public void sysName(){
		System.out.println(this.name);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<ReflectObj> clazz = (Class<ReflectObj>) Class.forName("com.zhang.module.login.ReflectObj");
		ReflectObj obj = clazz.newInstance();
		obj.name="zhang";
		obj.sysName();
	}
}

