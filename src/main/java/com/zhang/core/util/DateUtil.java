/**
 * 文件名：com.zhang.core.util.DateUtil.java<br/>
 * 创建时间：2016年5月13日 下午3:47:12<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	//TODO 记得要写注释，方便别人，成就自己。
	
	public static String parseFormatString2(String str,String format){
		return formatDate(parseDateString(str,format),format);
	}
	
	public static Date parseDateString(String str, String format) {
		if (str == null || str.equals(""))
			return null;
		Date dt = null;
		try {
			DateFormat df = new SimpleDateFormat(format);
			dt = df.parse(str);

		} catch (Exception pe) {
		}

		return dt;
	}
	
	public static String formatDate(Date dt, String format) {
		if ((dt == null) || format == null) {
			return "";
		}
		String strDate = "";
		String s1;
		try {
			SimpleDateFormat DATA_FORMAT = new SimpleDateFormat(format);
			strDate = DATA_FORMAT.format(dt);
			String s = strDate;
			return s;
		} catch (Exception e) {
			s1 = null;
		}
		return s1;
	}
	public static void main(String[] args) {
		String s = DateUtil.parseFormatString2("2016-05-13 15:33:00.0", "yyyy-MM-dd HH:mm");
		System.out.println(s);
	}
	
}

