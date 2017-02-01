/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web;

/**
 * @Author alex
 * @CreateTime Jun 3, 2016 1:41:49 PM
 * @Version 1.0
 * @Since 1.0
 */
public class BroswerTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String TEST_PAGE = "https://www.baidu.com/";
		Browser browser = Browser.chrome();
		browser.open(TEST_PAGE);


		try
		{
			browser.findElemById("wild id - sdfasdfljsdorjw23");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		browser.close();
	}

}
