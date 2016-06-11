/**
 * 文件名：com.zhang.core.ProjectRequestMappingTest.java<br/>
 * 创建时间：2016年6月7日 下午12:12:00<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import com.zhang.core.web.servlet.mvc.annotation.RequestMappingHandlerMappingPlus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class ProjectRequestMappingTest {
	
	@Autowired
	private RequestMappingHandlerMappingPlus  requestMappingHandlerMapping;
	
	@Test
	public void showMapping(){
		StringBuilder sb = new StringBuilder();  
        sb.append("URL").append("--").append("Method").append("--").append("param").append("--").append("Class").append("--").append("Function").append('\n');  
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods(); 
        List<HandlerMethod> hm = requestMappingHandlerMapping.getHandlerMethodsForMappingName("getAvailableSource");
        for(HandlerMethod h :hm){
        	System.out.println(h.getMethod().getName());
        }
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {  
            RequestMappingInfo info = m.getKey();  
            HandlerMethod method = m.getValue();  
            sb.append(info.getPatternsCondition()).append("--");  
            sb.append(info.getMethodsCondition()).append("--");  
            sb.append(info.getParamsCondition()).append("--");  
            sb.append(info.getProducesCondition()).append("--");  
            sb.append(info.getConsumesCondition()).append("--");  
            sb.append(info.getCustomCondition()).append("--");  
            sb.append(info.getHeadersCondition()).append("--");  
            sb.append(method.getMethod().getDeclaringClass()).append("--");  
            sb.append(method.getMethod().getName()).append('\n');  
//            RequestMappingInfo mappinfo = requestMappingHandlerMapping.getRequestMappingInfoByHandlerMethod(method);
//            if(info.getMethodsCondition().equals(mappinfo.getMethodsCondition())){
//            	sb.append("method found mapping").append('\n');  
//            }
            
        }
        System.out.println("---------------------------");
        System.out.println(sb.toString());
	}
	
	@Test
	public void showNameMethod(){
		//获取是 @RequestMapping 中的name  
		//loginController--class org.springframework.web.method.HandlerMethod--class com.zhang.module.login.controller.LoginController--list
		List<HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethodsForMappingName("getAvailableSource");
		for(HandlerMethod handler : handlerMethods){
			System.out.println(handler.getBean()+"--"+handler.getClass()+"--"+handler.getBeanType()+"--"
					+handler.getMethod().getName());
		}
	}
	
}

