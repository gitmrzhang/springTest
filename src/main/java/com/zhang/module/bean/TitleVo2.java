/**
 * 文件名：com.zhang.module.bean.TitleVo.java<br/>
 * 创建时间：2016年7月19日 下午4:26:05<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.module.bean;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class TitleVo2 {
	@Id
	private BigInteger id;
	private String title;
	private String user;
	
	
}

