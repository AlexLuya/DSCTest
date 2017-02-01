/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.common;

/**
 * @Author alex
 * @CreateTime Aug 24, 2016 9:12:59 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Pair<T>
{
	public T	left;
	public T	right;

	/**
	 * @param left
	 * @param right
	 */
	public Pair(T left, T right)
	{
		this.left = left;
		this.right = right;
	}

	public void swap()
	{
		T temp = left;
		left = right;
		right = temp;
	}
}
