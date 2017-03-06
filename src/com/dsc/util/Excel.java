/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author alex
 * @CreateTime 06.03.2017 15:04:33
 * @Version 1.0
 * @Since 1.0
 */
public class Excel
{
	public static void setBold(Workbook wb, Row row)
	{
		CellStyle style = wb.createCellStyle();// Create style
		Font font = wb.createFont();// Create font
		font.setBold(true);// Make font bold
		style.setFont(font);// set it to bold

		for (int i = 0; i < row.getLastCellNum(); i++)
		{// For each cell in the row
			row.getCell(i).setCellStyle(style);// Set the style
		}
	}

	public static void setColor(Workbook wb, Row row, IndexedColors foreground, IndexedColors background)
	{
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(foreground.getIndex());
		style.setFillBackgroundColor(background.getIndex());
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		row.setRowStyle(style);
	}
}
