/**
 * 文件名：com.zhang.designpattern.templatemethod.ExcelView.java<br/>
 * 创建时间：2016年6月8日 下午2:19:50<br/>
 * 创建者：zhanggaojie<br/>
 * 修改者：暂无<br/>
 * 修改简述：暂无<br/>
 * 修改详述：
 * <p>
 * 暂无<br/>
 * </p>
 * 修改时间：暂无<br/>
 */
package com.zhang.designpattern.templatemethod;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public abstract class AbstractExcelView {
	
	protected final void renderMergedOutputModel() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		buildExcelDocument(workbook);
		OutputStream out = new FileOutputStream("D://1.xls");
		workbook.write(out);
		out.flush();
		System.out.println("D://1.xls 导出成功");
	}
	protected abstract void buildExcelDocument(HSSFWorkbook workbook);
	
}

