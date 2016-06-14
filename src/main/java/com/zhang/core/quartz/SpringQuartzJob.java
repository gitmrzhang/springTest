/**
 * 文件名：com.zhang.core.quartz.SpringQuartzJob.java<br/>
 * 创建时间：2016年6月14日 上午10:47:59<br/>
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
public class SpringQuartzJob implements Job {

	@Override
	public void execute() {
		
		System.out.println("SpringQuartzJob job begin");

	}

}

