/**
 * 文件名：com.zhang.core.context.WebApplicationContextUtil.java<br/>
 * 创建时间：2016年5月23日 下午4:21:35<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * <p>
 * WebApplicationContextUtil 可以在程序中获取 bean操作 <br/>
 * <p>
 * 
 * @author zhanggaojie
 * @date 2016年5月23日 下午4:38:29
 * @version 1.0.0
 * @since 1.0.0
 */
public class WebApplicationContextUtil implements ServletContextListener {

	private WebApplicationContext webApplicationContext;
	
	private ServletContext context;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		context = contextEvent.getServletContext();
	}

	protected void initContext() {
		if (webApplicationContext == null) {
			webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		}
	}

	public Object getBean(String name) {
		initContext();
		return webApplicationContext.getBean(name);
	}

	public <T> T getBean(String name, Class<T> requiredType) {
		initContext();
		return webApplicationContext.getBean(name, requiredType);
	}

}
