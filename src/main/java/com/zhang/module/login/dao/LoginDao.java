/**
 * 文件名：com.zhang.module.login.dao.LoginDao.java<br/>
 * 创建时间：2016年5月19日 下午4:11:24<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.module.login.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhang.core.dao.MybatisDao;
import com.zhang.module.bean.UserVo;
@Repository
public class LoginDao extends MybatisDao{
	//TODO 记得要写注释，方便别人，成就自己。
	
	public List<UserVo> getUserList(){
		List<UserVo> list = this.getSqlSession().selectList("selectList");
		return list;
	}
}

