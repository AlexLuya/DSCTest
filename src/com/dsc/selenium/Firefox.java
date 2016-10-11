/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

/**
 * @Author alex
 * @CreateTime Jan 16, 2015 9:21:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Firefox
{
	FirefoxProfile profile = new ProfilesIni().getProfile("default");

	public Firefox disableCookies()
	{
		profile.setPreference("network.cookie.cookieBehavior", 2);
		return this;
	}
}
