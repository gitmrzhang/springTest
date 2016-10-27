/**
 * 文件名：com.zhang.core.mongo.AbstractRepository.java<br/>
 * 创建时间：2016年7月20日 上午10:06:09<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.mongo;

import java.util.List;

/**
 * <p>
 * 	 mongo操作接口
 * </p>
 * @author zhanggaojie
 * @date 2016年7月20日 上午10:06:13
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AbstractRepository<T> {
	/**
	 * 添加记录
	 * 2016年7月20日 上午10:09:51
	 * Administrator
	 * TODO
	 * @param t void
	 */
	void insert(T t);
	/**
	 * 根据id查找记录
	 * 2016年7月20日 上午10:10:14
	 * Administrator
	 * TODO
	 * @param id
	 * @return T
	 */
	T findOne(String id);
	/**
	 * 根据条件查找
	 * 2016年7月20日 上午10:10:30
	 * Administrator
	 * TODO
	 * @param regex
	 * @return List<T>
	 */
	List<T> findByRegex(String regex);
	/**
	 * 查找全部记录
	 * 2016年7月20日 上午10:10:30
	 * Administrator
	 * TODO
	 * @param regex
	 * @return List<T>
	 */
	List<T> findAll();
	/**
	 * 删除指定记录
	 * 2016年7月20日 上午10:11:18
	 * Administrator
	 * TODO
	 * @param id void
	 */
	void removeOne(String id);
	/**
	 * 删除全部记录
	 * 2016年7月20日 上午10:10:30
	 * Administrator
	 * TODO
	 * @param regex
	 * @return List<T>
	 */
	void removeAll();
}

