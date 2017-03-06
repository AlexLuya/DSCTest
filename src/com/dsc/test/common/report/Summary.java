/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.report;

import java.util.List;

import com.dsc.test.api.base.Test;

/**
 * @Author alex
 * @CreateTime 02.03.2017 22:58:21
 * @Version 1.0
 * @Since 1.0
 */
public class Summary
{
	public int			fails;
	public int			ignores;
	public float		time;
	public final int	total;

	/**
	 * @param tests
	 */
	public Summary(List<Test> tests)
	{
		total = tests.size();
		for (Test test : tests)
		{
			if (test.invalid())
			{
				++ignores;
			} else if (test.diff() != null)
			{
				++fails;
			} else
			{
				time = time + test.time();
			}
		}
	}

}
