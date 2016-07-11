/**
 * 文件名：com.zhang.redis.RedisTest.java<br/>
 * 创建时间：2016年7月11日 上午11:56:32<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.redis;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class RedisTest {
	
	@Autowired
	private RedisTemplate<String,String> redis;
	@Test
	public void test(){
		//redis.opsForValue().set("spring-redis", "spring-redis");
		Set keys = redis.keys("*");
		System.out.println(keys);
		//redis.opsForValue().set("spring-redis","spring-test2");
		System.out.println(redis.opsForValue().get("spring-redis"));
	}
	

}

