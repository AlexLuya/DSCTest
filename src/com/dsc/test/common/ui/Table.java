/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.widget.Widget;
import com.dsc.test.common.TesteeHost;

/**
 * @Author alex
 * @CreateTime Jan 23, 2015 10:59:25 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Table extends Widget
{

	public Table(TesteeHost browser,String id)
	{
		super(browser,id);
	}

	public Table(TesteeHost browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	public boolean ensureCellContentIs(int row, int cell, String text)
	{
		return getCell(row, cell).textIs(text);
	}

	public Cell getCell(int row, int cell)
	{
		return getRow(row).getCell(cell);
	}

	public Row getRow(int row)
	{
		return new Row(browser,wrapee.findElements(By.tagName("tr")).get(row));
	}

	public void mouseOver(int row)
	{
		getRow(row).mouseOver();
	}

	//	public void test()
	//	{
	//
	//		WebElement Webtable = driver.findElement(By.id("TableID")); // Replace
	//																	// TableID
	//																	// with
	//																	// Actual
	//																	// Table ID
	//																	// or Xpath
	//		List<WebElement> TotalRowCount = Webtable.findElements(By.xpath("//*[@id='TableID']/tbody/tr"));
	//
	//		System.out.println("No. of Rows in the WebTable: " + TotalRowCount.size());
	//		// Now we will Iterate the Table and print the Values
	//		int RowIndex = 1;
	//		for (WebElement rowElement : TotalRowCount)
	//		{
	//			List<WebElement> TotalColumnCount = rowElement.findElements(By.xpath("td"));
	//			int ColumnIndex = 1;
	//			for (WebElement colElement : TotalColumnCount)
	//			{
	//				System.out.println("Row " + RowIndex + " Column " + ColumnIndex + " Data " + colElement.getText());
	//				ColumnIndex = ColumnIndex + 1;
	//			}
	//			RowIndex = RowIndex + 1;
	//		}
	//
	//	}

	public void mouseOver(int row, int cell)
	{
		getCell(row, cell).mouseOver();
	}
}
