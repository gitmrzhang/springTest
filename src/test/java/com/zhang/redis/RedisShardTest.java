/**
 * 文件名：com.zhang.redis.RedisShardTest.java<br/>
 * 创建时间：2016年7月13日 上午10:43:25<br/>
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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class RedisShardTest {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	static ShardedJedisPool pool;

	@Test
	public void test() {
		String clientName = redisTemplate.getConnectionFactory().getConnection().getClientName();
		ValueOperations<String, String> strOpt = redisTemplate.opsForValue();
		System.out.println("clientName" + clientName + " " + strOpt.get("name"));
	}

	@Test
	public void test2() {
		init();
		ShardedJedis jedis = pool.getResource();
		jedis.set("name--123","names");
	}

	public void init() {
		JedisPoolConfig config = new JedisPoolConfig();// Jedis池配置
		config.setMaxIdle(1000 * 60);// 对象最大空闲时间
		config.setTestOnBorrow(true);
		String hostA = "127.0.0.1";
		int portA = 6379;
		String hostB = "127.0.0.1";
		int portB = 6378;
		List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);
		JedisShardInfo infoA = new JedisShardInfo(hostA, portA);
		JedisShardInfo infoB = new JedisShardInfo(hostB, portB);
		jdsInfoList.add(infoA);
		jdsInfoList.add(infoB);
		pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
	}

}
