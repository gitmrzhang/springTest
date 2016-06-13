package com.zhang.core;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import com.zhang.core.web.servlet.mvc.annotation.CustomRequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class CustomRequestMappingHandlerMappingTest {
	@Autowired
	CustomRequestMappingHandlerMapping requestMappingHandlerMapping ;
	
	@Test
	public void test() {
		StringBuilder sb = new StringBuilder();  
        sb.append("URL").append("--").append("Method").append("--").append("param").append("--").append("Class").append("--").append("Function").append('\n');  
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();  
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
            RequestMappingInfo a = requestMappingHandlerMapping.getRequestMappingInfoByHandlerMethod(method);
            if(a!=null){
            	sb.append(a.getMethodsCondition()).append('\n');  
            }
        }
        System.out.println("---------------------------");
        System.out.println(sb.toString());
	}

}
