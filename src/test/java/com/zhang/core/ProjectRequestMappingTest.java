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

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class ProjectRequestMappingTest {
	
	@Autowired
	private RequestMappingHandlerMapping  requestMappingHandlerMapping;
	
	@Test
	public void showMapping(){
		StringBuilder sb = new StringBuilder();  
        sb.append("URL").append("--").append("Class").append("--").append("Function").append('\n');  
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();  
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {  
            RequestMappingInfo info = m.getKey();  
            HandlerMethod method = m.getValue();  
            sb.append(info.getPatternsCondition()).append("--");  
            sb.append(method.getMethod().getDeclaringClass()).append("--");  
            sb.append(method.getMethod().getName()).append('\n');  
        }
        System.out.println("---------------------------");
        System.out.println(sb.toString());
	}
	
}

