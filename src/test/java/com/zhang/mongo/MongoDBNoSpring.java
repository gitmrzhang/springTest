/**
 * 文件名：com.zhang.mongo.MongoDBNoSpring.java<br/>
 * 创建时间：2016年7月19日 下午3:43:55<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.mongo;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBNoSpring {

	@Test
	public void test() throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB dataBase = client.getDB("user");
		DBCollection collection = dataBase.getCollection("test");
		System.out.println("user dataBase test size:" + collection.count());
		// 插入文档
		
	}
}
