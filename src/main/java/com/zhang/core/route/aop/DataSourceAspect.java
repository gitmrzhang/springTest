/**
 * 文件名：com.zhang.core.route.aop.DataSourceAspect.java<br/>
 * 创建时间：2016年6月23日 下午3:02:26<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.route.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.zhang.core.route.annotation.DataSource;
import com.zhang.core.route.datasource.RoutingDateSourceHolder;

public class DataSourceAspect {
	
	private static final Logger logger = Logger.getLogger(DataSourceAspect.class);

	public void route(JoinPoint point) {
		Object target = point.getTarget();
		String method = point.getSignature().getName();
		Class<?>[] classz = target.getClass().getInterfaces();
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = classz[0].getMethod(method, parameterTypes);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				DataSource data = m.getAnnotation(DataSource.class);
				RoutingDateSourceHolder.putDataSource(data.value());
				if(logger.isDebugEnabled()){
					logger.debug("切换至数据源"+data.value());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
