/**
 * 文件名：com.zhang.core.route.datasource.RoutingDateSourceHolder.java<br/>
 * 创建时间：2016年6月23日 下午2:49:36<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.route.datasource;

public class RoutingDateSourceHolder {
	
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }

}

