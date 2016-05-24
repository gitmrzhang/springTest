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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhang.module.bean.UserVo;
import com.zhang.module.login.dao.LoginDao;
@Service
public class LoginService implements InitializingBean {
	
	@Autowired
	private LoginDao loginDao;

	public UserVo getUser(String username) {
		return loginDao.getUserByName(username);
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("LoginService init success");
	}
}
