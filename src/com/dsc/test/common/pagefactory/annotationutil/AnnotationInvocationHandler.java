/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
/**
 * @Author alex
 * @CreateTime Dec 24, 2014 2:39:56 PM
 * @Version 1.0
 * @Since 1.0
 */
package com.dsc.test.common.pagefactory.annotationutil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnnotationInvocationHandler implements InvocationHandler
{
	private static Class<?>[] toClassArray(Object[] arr)
	{
		if (arr == null)
		{
			return null;
		}
		Class<?>[] classArr = new Class[arr.length];
		for (int i = 0; i < arr.length; i++)
		{
			classArr[i] = arr[i].getClass();
		}
		return classArr;
	}

	private String		attrName;
	private Object		newValue;

	private Annotation	orig;

	public AnnotationInvocationHandler(Annotation orig, String attrName, Object newValue) 
	{
		this.orig = orig;
		this.attrName = attrName;
		this.newValue = newValue;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		// "override" the return value for the property we want
		if (method.getName().equals(attrName) && args == null)
		{
			return newValue;
		}

		return orig.getClass().getMethod(method.getName(), toClassArray(args)).invoke(orig, args);
	}
}