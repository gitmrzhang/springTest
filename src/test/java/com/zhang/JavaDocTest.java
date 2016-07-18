/**
 * 文件名：com.zhang.JavaDocTest.java<br/>
 * 创建时间：2016年7月18日 下午1:40:58<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang;
/**
 * <p>
 * TODO 测试<br/>
 * <p>
 * @author zhanggaojie
 * @date 2016年7月18日 下午1:41:12
 * @version 1.0.0
 * @since 1.0.0
 */
public class JavaDocTest {
	
	/**
	 * JavaDocTest 输出 “hello”<br/>
	 * 2016年7月18日 下午1:41:54
	 * @author zhanggaojie
	 */
	public void sayHello(){
		System.out.println("hello");
	}
	
	/**
	 * JavaDocTest 输出 “hello2”<br/>
	 * 2016年7月18日 下午1:41:54
	 * @author zhanggaojie
	 */
	public void sayHello2(){
		System.out.println("hello");
	}
	
	public static void main(String[] args) {
		JavaDocTest t = new JavaDocTest();
		t.sayHello();
	}
	

}

