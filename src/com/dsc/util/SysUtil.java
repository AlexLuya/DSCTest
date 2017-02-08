/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import static com.dsc.util.Util.wrap;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;

/**
 * The Class SysUtil.
 *
 * @Author alex
 * @CreateTime Jul 7, 2016 8:53:56 AM
 * @Version 1.0
 * @Since 1.0
 */
public class SysUtil
{

	/** The Constant DATE_TIME_FORMAT. */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** The Constant MILLISECONDS_IN_A_30_DAYS. */
	public static final long MILLISECONDS_IN_A_30_DAYS = 2592000000L;

	/** The Constant MILLISECONDS_IN_A_MINUTE. */
	public static final long MILLISECONDS_IN_A_MINUTE = 60000;

	/** The Constant THIRTY_DAYS_AGO. */
	public static final long THIRTY_DAYS_AGO = new Date().getTime() - MILLISECONDS_IN_A_30_DAYS;

	/**
	 * Disable synchronizing system clock with network
	 */
	public static void disableTimeSync()
	{
		switchTimeSync(false);
	}

	/**
	 * Enable synchronizing system clock with network
	 */
	public static void enableTimeSync()
	{
		enableTimeSyncAfter(0);
	}

	public static void enableTimeSyncAfter(int seconds)
	{
		Util.sleep(seconds * 1000);
		switchTimeSync(true);
	}

	/**
	 * Sets the system time to.
	 *
	 * @param date
	 *              the date to set
	 * @return the date
	 */
	public static Date setSystemTimeTo(Date date)
	{
		if (SystemUtils.IS_OS_LINUX)
		{
			setTimeForLinux(date);
		} else if (SystemUtils.IS_OS_LINUX)
		{
			// setTimeForWindows(date);
			throw OSNotSupportedException();
		} else if (SystemUtils.IS_OS_MAC)
		{
			throw OSNotSupportedException();
		}

		return new Date();
	}

	/**
	 * Sets the system time to.
	 *
	 * @param timeString
	 *              format must like : '2016-06-08 23:11:01'
	 * @return the date
	 */
	public static Date setSystemTimeTo(String timeString)
	{
		try
		{
			return setSystemTimeTo(new SimpleDateFormat(DATE_TIME_FORMAT).parse(timeString));
		} catch (ParseException e)
		{
			throw new RuntimeException(wrap(format("'%s' can't be parsed as Date,right format must like:'%s'", timeString,
					"2016-06-08 23:11:01")), e.getCause());
		}
	}

	private static void execCmd(String cmd)
	{
		if (SystemUtils.IS_OS_LINUX)
		{
			execLinuxCmd(cmd);
		} else if (SystemUtils.IS_OS_WINDOWS)
		{
			// setTimeForWindows(date);
			throw OSNotSupportedException();
		} else if (SystemUtils.IS_OS_MAC)
		{
			throw OSNotSupportedException();
		}
	}

	/**
	 * Exec linux cmd.
	 *
	 * @param cmd
	 *              the cmd
	 */
	private static void execLinuxCmd(String cmd)
	{
		List<String> cmds = newArrayList("/bin/bash", "-c");

		cmds.add("echo " + Config.sudoerPassword() + " | sudo -S " + cmd);

		try
		{
			Runtime.getRuntime().exec(cmds.toArray(new String[cmds.size()])).waitFor();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * OS not supported exception.
	 *
	 * @return the illegal state exception
	 */
	private static IllegalStateException OSNotSupportedException()
	{
		return new IllegalStateException("MAC is supported yet,only LINUX supported right now");
	}

	/**
	 * Sets the time for linux.
	 *
	 * @param date
	 *              the new time for linux
	 */
	private static void setTimeForLinux(Date date)
	{
		disableTimeSync();

		execLinuxCmd("date -s '" + date.toString() + "'");
	}

	private static void switchTimeSync(boolean enable)
	{
		execCmd("timedatectl set-ntp " + enable);
	}

	/**
	 * @param date
	 */
	// private static void setTimeForWindows(Date date)
	// {
	// String df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
	//
	// try
	// {
	// Runtime.getRuntime().exec("cmd /C date " + df.split(" ")[0]);
	// Runtime.getRuntime().exec("cmd /C time " + df.split(" ")[1]);
	// } catch (IOException e)
	// {
	// e.printStackTrace();
	// }
	//
	// }
}
