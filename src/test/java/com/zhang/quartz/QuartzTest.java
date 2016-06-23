/**
 * 文件名：com.zhang.quartz.QuartzTest.java<br/>
 * 创建时间：2016年6月13日 下午4:52:44<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.quartz;


import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.zhang.core.quartz.HelloQuartzJob;

public class QuartzTest {
	
	@Test
	public void test() throws SchedulerException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
        // 具体任务
        JobDetail job = JobBuilder.newJob(HelloQuartzJob.class).withIdentity("job1", "group1").build();

        // 触发时间点 5s执行一次 执行10次
        @SuppressWarnings("static-access")
		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForTotalCount(10)
                .withIntervalInSeconds(5);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow().withSchedule(simpleScheduleBuilder).build();
        // 交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);
        try {
            TimeUnit.MINUTES.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
	}

}

