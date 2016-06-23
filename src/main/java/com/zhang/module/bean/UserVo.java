/**
 * 文件名：com.zhang.module.bean.UserVo.java<br/>
 * 创建时间：2016年5月11日 上午10:31:39<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.module.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserVo implements Serializable{
	//TODO 记得要写注释，方便别人，成就自己。
	/**
	 * 
	 */
	private static final long serialVersionUID = 8439043390403066893L;
	private String username;//用户名
	private transient String password;//密码
	
	@Override
	public String toString() {
		return "UserVo [username=" + username + ", password=" + password + "]";
	}
}

