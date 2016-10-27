/**
 * 文件名：com.zhang.mongo.MongoDBTest.java<br/>
 * 创建时间：2016年7月19日 上午11:21:06<br/>
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

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zhang.module.bean.TitleVo;
import com.zhang.module.bean.TitleVo2;
import com.zhang.module.bean.UserVo;
import com.zhang.module.login.ILoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class MongoDBTest {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private ILoginService loginService;
	
	private static final String USERS_COLLECTION="test";
	
	@Test
	public void saveInformation(){
		
	}
	@Test
	public void getInformation(){
		//增加
		TitleVo vo = new TitleVo();
		TitleVo2 vo2 = null;
		vo.setTitle("zhng110");
		vo.setUser("zhang");
		vo.setId(new BigInteger("110"));
//		vo2.setTitle("zhng110");
//		vo2.setUser("zhang");
//		vo2.setId(new BigInteger("110"));
		vo.setTitle2(null);
		mongoTemplate.insert(vo, USERS_COLLECTION);
		
	}

}

