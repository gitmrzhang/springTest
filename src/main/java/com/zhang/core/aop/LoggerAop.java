/**
 * 文件名：com.zhang.core.aop.LoggerAop.java<br/>
 * 创建时间：2016年6月23日 上午9:46:30<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.aop;

import org.apache.log4j.Logger;

public class LoggerAop {
	
	private Logger logger = Logger.getLogger(LoggerAop.class);
	
	public void preHandler(){
		logger.info("--------------------------LoggerAop preHandler");
	}
	
	public void afterHandler(){
		logger.info("afterHandler");
	}
	
	

}

