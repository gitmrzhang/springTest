/**
 * 文件名：com.zhang.module.login.LoginService.java<br/>
 * 创建时间：2016年5月11日 上午10:29:43<br/>
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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.core.route.annotation.DataSource;
import com.zhang.module.bean.CouponPo;
import com.zhang.module.bean.UserPo;
import com.zhang.module.bean.UserVo;
import com.zhang.module.login.dao.LoginDao;

@Service("loginService")
public class LoginService implements ILoginService,InitializingBean {
	
	@Autowired
	private LoginDao loginDao;
	
	@DataSource("salver")
	public UserVo getUser(String username) {
		return loginDao.getUserByName(username);
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("LoginService init success");
	}

	@Override
	public void updateUser(UserPo userPo) {
		loginDao.updateUserTest(userPo);
		throw new RuntimeException();
	}

	@Override
	@DataSource("master")
	public void updateUserCoupon(CouponPo couponPo) {
		loginDao.updateUserCouponTest(couponPo);
	}

	@Override
	@Transactional
	public void update() {
		UserPo user = new UserPo();
		user.setId(1);
		user.setUserid(1);
		user.setUsername("1_test_1");
		updateUser(user);
		CouponPo coupon = new CouponPo();
		coupon.setId(1);
		coupon.setUserid(1);
		coupon.setCoupon(15);
		updateUserCoupon(coupon);
	}
}
