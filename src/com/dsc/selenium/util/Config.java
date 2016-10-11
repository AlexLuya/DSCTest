/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author alex
 * @CreateTime Jul 8, 2016 11:26:15 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Config
{
	private static final String	CONFIG_INI	= "config.properties";
	private static final String	PASSWORD	= "password";

	public static String sudoerPassword()
	{
		return config().getProperty(PASSWORD);
	}

	private static Properties config()
	{
		Properties props = new Properties();
		InputStream is = null;

		try
		{
			is = ClassLoader.getSystemResourceAsStream(CONFIG_INI);
			props.load(is);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return props;
	}
	// private static INIConfiguration config()
	// {
	// Parameters params = new Parameters();
	//
	// DefaultExpressionEngine engine = new
	// DefaultExpressionEngine(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS,
	// NodeNameMatchers.EQUALS_IGNORE_CASE);
	//
	// FileBasedConfigurationBuilder<INIConfiguration> builder = new
	// FileBasedConfigurationBuilder<INIConfiguration>(
	// INIConfiguration.class).configure(params.hierarchical().setFileName(CONFIG_INI).setExpressionEngine(engine));
	//
	// try
	// {
	// return builder.getConfiguration();
	// } catch (ConfigurationException e)
	// {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
}
