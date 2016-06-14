/**
 * 文件名：com.zhang.core.HelloQuartzJob.java<br/>
 * 创建时间：2016年6月13日 下午4:56:52<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloQuartzJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("Hello, Quartz! - executing HelloQuartzJob JOB at "+ 
	          new SimpleDateFormat("yyyy-MM-dd hh:mm:ss-ms").format(new Date())  + " by " + context.getTrigger().getKey());
		
	}

}

