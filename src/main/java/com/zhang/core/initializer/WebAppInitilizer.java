/**
 * 文件名：com.zhang.core.initializer.WebAppInitilizer.java<br/>
 * 创建时间：2016年5月12日 下午5:11:07<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

public class WebAppInitilizer implements WebApplicationInitializer {

	//TODO 记得要写注释，方便别人，成就自己。
	public void onStartup(ServletContext arg0) throws ServletException {
		System.out.println("WebAppInitilizer onstartup");
		
	}
}

