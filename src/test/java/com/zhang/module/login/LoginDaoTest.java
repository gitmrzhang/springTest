/**
 * 文件名：com.zhang.module.login.LoginDaoTest.java<br/>
 * 创建时间：2016年5月19日 下午8:28:17<br/>
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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhang.module.bean.UserVo;
import com.zhang.module.login.dao.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class LoginDaoTest {
	//TODO 记得要写注释，方便别人，成就自己。
	@Autowired
	private LoginDao loginDao;
	@Test
	public void testList(){
		List<UserVo> l = loginDao.getUserList();
		System.out.println(l.size()+" "+l.get(0).getUsername());
	}
}

