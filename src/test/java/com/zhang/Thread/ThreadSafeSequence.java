/**
 * 文件名：com.zhang.Sequence.java<br/>
 * 创建时间：2016年6月27日 下午4:28:05<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.Thread;
public class ThreadSafeSequence {
	
	private static ThreadLocal<Integer> sequence = new ThreadLocal<Integer>(){

		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	public static int getNext(){
		sequence.set(sequence.get()+1);
		return sequence.get();
	}
}

