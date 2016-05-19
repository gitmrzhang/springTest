/**
 * 文件名：com.zhang.module.login.LoginTest.java<br/>
 * 创建时间：2016年5月19日 下午4:19:03<br/>
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

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.zhang.module.bean.UserVo;

public class LoginTest {
	@Test
	public void testConnection(){
		InputStream is=LoginTest.class.getClass().getResourceAsStream("/com/zhang/mybatis/mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		List<UserVo> list=session.selectList("com.zhang.module.login.loginDao.selectList");
		session.close();
		System.out.println("list size:"+list.size()+ " "+list.get(0).getUsername());
	}
}

