/**
 * 文件名：com.zhang.module.login.LoginService.java<br/>
 * 创建时间：2016年5月11日 上午10:29:43<br/>
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


import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.zhang.module.bean.UserVo;

@Service
public class LoginService implements InitializingBean {
	//TODO 记得要写注释，方便别人，成就自己。
	public UserVo getUser(String username){
		UserVo user = new UserVo();
		user.setUsername(username);
		user.setPassword("123456");
		return user;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("LoginService init success");
	}
}

