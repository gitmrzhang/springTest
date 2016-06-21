/**
 * 文件名：com.zhang.module.login.LoginServiceTest.java<br/>
 * 创建时间：2016年5月11日 上午10:52:38<br/>
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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhang.module.bean.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class LoginServiceTest {
	@Autowired
	private ILoginService loginService;
	
	@Test
	public void testGetUser() {
		UserVo vo = loginService.getUser("zhang");
		System.out.println(vo.getUsername()+" "+vo.getPassword());
	}
	@Test
	public void testTran() {
		loginService.update();
	}
}

