/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web;


/**
 * @Author alex
 * @CreateTime Dec 30, 2014 9:01:29 PM
 * @Version 1.0
 * @Since 1.0
 */
public interface Validable
{
	String	PATTERN	= "pattern";
	String	WARNING	= "warning";

	void ensureValidationAttrsSet();
}
