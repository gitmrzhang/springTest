/**
 * 文件名：com.zhang.redis.ShardTest.java<br/>
 * 创建时间：2016年7月13日 上午11:47:19<br/>
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

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class ShardTest {
	static ShardedJedisPool pool;

	@Test
	public void test() {
		init();
		ShardedJedis jedis = pool.getResource();
		jedis.set("name--123","name--1234");
		System.out.println(jedis.get("name--123"));
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
		jdsInfoList.add(infoB);
		jdsInfoList.add(infoA);
		pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
	}

}

