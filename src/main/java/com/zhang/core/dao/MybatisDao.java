/**
 * 文件名：com.zhang.core.MybatisDao.java<br/>
 * 创建时间：2016年5月19日 下午8:31:13<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisDao extends SqlSessionDaoSupport {
	//TODO 记得要写注释，方便别人，成就自己。
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
}

