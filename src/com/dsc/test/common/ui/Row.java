/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
**/
package com.dsc.test.common.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

/**
 * @Author alex
 * @CreateTime Jan 23, 2015 11:06:33 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Row extends UIObject
{

	/**
	 * @param wrapee
	 */
	public Row(TesteeHost browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	/**
	 * @param cell
	 * @return
	 */
	public Cell getCell(int cell)
	{
		return  new Cell(browser,wrapee.findElements(By.tagName("td")).get(cell));
	}

}
