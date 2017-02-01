/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web;

import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

/**
 * @Author alex
 * @CreateTime Jan 16, 2015 9:21:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public class IE
{

	FirefoxProfile profile = new ProfilesIni().getProfile("default");
	public IE disableCookies()
	{
		String command = "REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet UserSettings\\Zones\\ \" /v       1A10 /t REG_DWORD /d 0X3 /f";

		try
		{
			Runtime.getRuntime().exec(command);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return this;
	}
}
