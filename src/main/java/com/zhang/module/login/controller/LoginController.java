/**
 * 文件名：com.zhang.module.login.controller.LoginController.java<br/>
 * 创建时间：2016年5月20日 下午4:51:54<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.module.login.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("api")
public class LoginController {
	
	@RequestMapping(value = "/toLogin", name="toLogin",method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}
	@RequestMapping(value = "/toLogin.htm", name="toLogin",method = RequestMethod.GET)
	public String toLogin2() {
		return "login";
	}

}
