/**
 * 文件名：com.zhang.designpattern.proxy.UserService.java<br/>
 * 创建时间：2016年6月20日 下午2:41:06<br/>
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
public interface UserService {
	/**
	 * 属性名和方法名要尽量做到见名知意，以达到自注释效果，这样也能过做到约定优于配置的原则
	 * 接口中定义的所有属性的属性名必须全部大写，并且赋予初始值。
	 */
	void add();
	void delete();
	void addAnddelete();
}

