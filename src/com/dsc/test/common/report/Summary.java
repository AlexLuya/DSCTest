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
	public final int	fails;
	public final int	ignores;
	public final float	time;
	public final int	total;

	/**
	 * @param tests
	 */
	public Summary(List<Test> tests)
	{
		total = tests.size();
		fails = fails(tests);
		ignores = ignores(tests);
		time = time(tests);
	}

	/**
	 * @param tests
	 * @return
	 */
	private int fails(List<Test> tests)
	{
		// NP- Auto-generated method stub
		return 0;
	}

	/**
	 * @param tests
	 * @return
	 */
	private int ignores(List<Test> tests)
	{
		// NP- Auto-generated method stub
		return 0;
	}

	/**
	 * @param tests
	 * @return
	 */
	private float time(List<Test> tests)
	{
		float time = 0;

		for (Test test : tests)
		{
			time = time + test.time();
		}

		return time;
	}

}
