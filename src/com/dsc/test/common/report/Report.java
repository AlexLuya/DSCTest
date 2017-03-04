/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.report;

import java.io.File;

/**
 * @Author alex
 * @CreateTime 02.03.2017 22:41:09
 * @Version 1.0
 * @Since 1.0
 */
public class Report
{
	private static String	APP_TESTING_REPORT_DIR	= "report/app/";
	private static String	APT_TESTING_REPORT_DIR	= "report/api/";
	private static String	WEB_TESTING_REPORT_DIR	= "report/web/";

	public static File forAPITesting(String fileName)
	{
		// HP ensure parent dir existed
		return new File(new File(APT_TESTING_REPORT_DIR + fileName).getAbsolutePath());
	}

}
