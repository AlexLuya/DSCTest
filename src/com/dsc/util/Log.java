/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import org.apache.log4j.Logger;

/**
 * @Author alex
 * @CreateTime Dec 22, 2014 1:18:12 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Log
{
	// BLOG log the details of checking event to avoid debugging

	private static Logger log = Logger.getLogger(Log.class.getName()); //

	public static void debug(String message)
	{
		log.debug(message);
	}

	public static void endTestCase(String sTestCaseName)
	{
		log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + sTestCaseName + "             XXXXXXXXXXXXXXXXXXXXXX");

		log.info("X");

		log.info("X");

		log.info("X");

		log.info("X");

	}

	public static void error(String message)
	{
		log.error(message);
	}

	public static void fatal(String message)
	{
		log.fatal(message);
	}

	public static void info(String info, Object... args)
	{
		Util.mustNotNullOrEmpty(info, "info");
		log.info(Util.message(info, args));
	}

	public static void startTestCase(String testCaseName)
	{
		log.info("****************************************************************************************");

		log.info("****************************************************************************************");

		log.info("$$$$$$$$$$$$$$$$$$$$$                 " + testCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		log.info("****************************************************************************************");

		log.info("****************************************************************************************");
	}

	public static void warn(String message)
	{
		log.warn(message);
	}
}