/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author alex
 * @CreateTime Jul 8, 2016 2:47:14 PM
 * @Version 1.0
 * @Since 1.0
 */
public class FileUtil
{
	public static boolean ensureImageExisted(String file)
	{
		if (!Files.exists(Paths.get(file)))
		{
			throw new IllegalStateException("File " + imagePath(file) + " not existed");
		}

		return true;
	}

	public static String imagePath(String image)
	{
		return System.getProperty("user.dir") + "/images/" + image;
	}

	public static String standardImagePath(String image)
	{
		return System.getProperty("user.dir") + "/standard_images/" + image;
	}
}