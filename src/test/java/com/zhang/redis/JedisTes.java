/**
 * 文件名：com.zhang.redis.JedisTes.java<br/>
 * 创建时间：2016年7月6日 上午10:41:36<br/>
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

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTes {
	@Test
	public void test(){
		Jedis jedis = new Jedis("localhost");
		jedis.auth("MrZhang");
		System.out.println("Server is running: "+jedis.ping());
		jedis.set("name","zhang");
		System.out.println("jedis set success: "+jedis.get("name"));
	}

}

