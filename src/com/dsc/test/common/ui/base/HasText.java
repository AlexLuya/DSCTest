/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.base;

/**
 * @Author alex
 * @CreateTime 06.03.2017 11:44:17
 * @Version 1.0
 * @Since 1.0
 */
public interface HasText
{
	boolean contains(String text);

	boolean endsWith(String text);

	boolean partOf(String text);

	boolean startsWith(String text);

	String text();
}
