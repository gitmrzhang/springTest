/**
 * 文件名：com.zhang.T14.java<br/>
 * 创建时间：2016年7月26日 下午2:26:32<br/>
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
class T14P{
	T14P(){
		System.out.println("T14P");
	}
}
public  class T14 extends T14P {
	
	
	
	
	static{
		byte a=(byte) 4L;
		a+=2;
		
	}
	T14(){
		System.out.println("T14");
	}
	
	public String name="tom";
	private int age = 20;
	
	public T14(String name,int age){
		name =name;
		this.age=age;
	}
	
	static boolean isTrue(){
		char c = 'a';
		String s = "s";
		System.out.println(c+1+s);
		return true;
	}
	static boolean isFalse(){
		int a =2;
		int b=a;
		System.out.println(a++>b);
		return false;
	}
	public static void main(String[] args) {
		
		
		int m = 2,n=5,k=3;
		while((m++)<(--n)){
			++k;
		}
		System.out.println(k);
		T14 s = new T14("zhang",12);
		System.out.println(s.name+" "+s.age);
		
		if(isFalse()&&isTrue()){
			System.out.println("d");
		}
		if(isTrue()||isFalse()){
			System.out.println("e");
		}
		
		
		int[] arr =Arr();
		System.out.println(arr[1]);
	}
	
	
	public static int[] Arr(){
		final int[] arr ={1,2,3};
		int [] arr2 = arr;
		arr2[1] = 50;
		return arr2;
	}

}

