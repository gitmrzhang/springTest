/**
 * 文件名：com.zhang.module.login.UserCacheTest.java<br/>
 * 创建时间：2016年5月31日 上午11:59:21<br/>
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

import com.zhang.module.cache.UserCache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class UserCacheTest {
	// TODO 记得要写注释，方便别人，成就自己。
	@Autowired
	private UserCache userCache;

	@Test
	public void test1() {
		userCache.getAllUser();
		userCache.getAllUser();
	}
	
	@Test
	public void test2(){
		userCache.getAllUser();
		userCache.evictGetAllUser();
		userCache.getAllUser();
	}
}
