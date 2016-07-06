/**
 * 文件名：com.zhang.TestThreadLocal.java<br/>
 * 创建时间：2016年6月27日 下午4:31:39<br/>
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

import org.junit.Test;

public class TestThreadLocal extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			// ④每个线程打出3个序列值
//			System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn[" + Sequence.getNext() + "]");
			System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn[" + ThreadSafeSequence.getNext() + "]");
		}
	}
	@Test
	public void test() {
		TestThreadLocal t1 = new TestThreadLocal();
		TestThreadLocal t2 = new TestThreadLocal();
		t2.start();
		try {
			t1.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.start();
	}

}
