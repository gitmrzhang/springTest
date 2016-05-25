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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhang.module.bean.UserVo;

@Controller
public class LoginController {
	// TODO 记得要写注释，方便别人，成就自己。
	
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "/dologin2", method = RequestMethod.POST)
	public String loginPage(@RequestBody UserVo vo) {
		UsernamePasswordToken token = new UsernamePasswordToken(vo.getUsername(), vo.getPassword());
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		if (subject.isAuthenticated()) {
			return "index";
		} else {
			return "redirect:/toLogin";
		}
	}
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		if (subject.isAuthenticated()) {
			return "index";
		} else {
			return "redirect:/toLogin";
		}
	}

	@RequestMapping(value = "/dologin/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public String loginPage2(@PathVariable String userid) {
		return userid;
	}

	@RequestMapping(value = "/controllerversion", method = RequestMethod.GET)
	@ResponseBody
	public String controllerversion(HttpServletRequest request, HttpServletResponse reponse) {
		return "";
	}

}
