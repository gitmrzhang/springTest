package com.zhang.module.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class TestController {
	
	@RequestMapping("/test/{id}")
	@ResponseBody
	public String test(@PathVariable String id){
		return "{id:"+id+",name:zhang,value:test}";
	}

}
