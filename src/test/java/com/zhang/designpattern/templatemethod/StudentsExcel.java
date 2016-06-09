/**
 * 文件名：com.zhang.designpattern.templatemethod.StudentsExcel.java<br/>
 * 创建时间：2016年6月8日 下午2:29:45<br/>
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

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class StudentsExcel extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet("sheets0");
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell cell00 = row0.createCell(0);
		cell00.setCellValue("studends");
	}
	@Test
	public void test() throws IOException{
		AbstractExcelView view = new StudentsExcel();
		view.renderMergedOutputModel();
	}
}

