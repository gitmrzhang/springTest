/**
 * 文件名：com.zhang.module.cache.UserCache.java<br/>
 * 创建时间：2016年5月31日 上午11:53:36<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.module.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.zhang.module.bean.UserVo;
import com.zhang.module.login.dao.LoginDao;

@Component
public class UserCache {
	//TODO 记得要写注释，方便别人，成就自己。
	@Autowired
	private LoginDao loginDao;
	
	@Cacheable(value="default_test")
	public List<UserVo> getAllUser(){
		System.out.println("-------------------cache");
		return loginDao.getUserList();
	}
	
	@CacheEvict(value="default_test",allEntries=true)
	public void evictGetAllUser(){
		
	}
	
}

