/**
 * 文件名：com.zhang.route.RouteTest.java<br/>
 * 创建时间：2016年6月23日 下午3:14:21<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.route;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhang.module.bean.CouponPo;
import com.zhang.module.bean.UserVo;
import com.zhang.module.login.ILoginService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class RouteTest {
	
	@Autowired
	private ILoginService loginService;
	
	@Test
	public void testgetUser(){
		UserVo userPo = loginService.getUser("13916030294");
		System.out.println(userPo.toString());
	}
	
	@Test
	public void updateUserCoupon(){
		CouponPo couponPo = new CouponPo();
		couponPo.setCoupon(20);
		couponPo.setId(1);
		couponPo.setUserid(1);
		loginService.updateUserCoupon(couponPo);
	}

}

