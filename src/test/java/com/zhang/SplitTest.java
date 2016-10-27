/**
 * 文件名：com.zhang.SplitTest.java<br/>
 * 创建时间：2016年8月30日 上午10:32:59<br/>
 * 创建者：zhanggj<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang;
public class SplitTest {
	
	public static void main(String[] args) {
		String a = "zhanggagagga，zhangjjj，kjjju";
		String[] b = a.split("[,，]");
		for(String str :b){
			System.out.println(str);
		}
	}

}

