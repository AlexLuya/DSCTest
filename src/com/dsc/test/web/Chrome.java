/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web;

import java.util.Arrays;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @Author alex
 * @CreateTime Jan 16, 2015 9:21:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Chrome
{
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();

	public Chrome disableCookies()
	{
		capabilities.setCapability("chrome.switches", Arrays.asList("--disable-local-storage"));

		return this;
	}
}
