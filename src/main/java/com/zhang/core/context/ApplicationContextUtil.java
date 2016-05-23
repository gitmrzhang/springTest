/**
 * 文件名：com.zhang.core.context.ApplicationContextUtil.java<br/>
 * 创建时间：2016年5月23日 下午5:11:50<br/>
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

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * <p>
 * 通过实现 ServletContextAware 获取bean操作
 * <p>
 * @author zhanggaojie
 * @date 2016年5月23日 下午5:14:14
 * @version 1.0.0
 * @since 1.0.0
 */
public class ApplicationContextUtil implements ServletContextAware {
	
	private WebApplicationContext webApplicationContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		webApplicationContext=WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}
	
	public Object getBean(String name) {
		return webApplicationContext.getBean(name);
	}

	public <T> T getBean(String name, Class<T> requiredType) {
		return webApplicationContext.getBean(name, requiredType);
	}
}

