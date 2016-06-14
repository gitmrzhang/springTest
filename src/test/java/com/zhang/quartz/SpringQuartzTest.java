/**
 * 文件名：com.zhang.quartz.SpringQuartzTest.java<br/>
 * 创建时间：2016年6月14日 上午10:58:05<br/>
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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class SpringQuartzTest {
	@Test
	public void test(){
		System.out.println("容器启动开始执行quartz");
	}
}

