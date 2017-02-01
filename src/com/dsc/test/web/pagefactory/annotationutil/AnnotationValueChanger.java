/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.pagefactory.annotationutil;

/**
 * @Author alex
 * @CreateTime Dec 23, 2014 9:29:33 PM
 * @Version 1.0
 * @Since 1.0
 */
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.openqa.selenium.support.FindBy;
@Deprecated
public class AnnotationValueChanger
{
	@SuppressWarnings("unchecked")
	public static  <T> T setAttrValue(Annotation anno, Class<? extends Annotation> type, String attrName, Object newValue)
			
	{
		InvocationHandler handler = new AnnotationInvocationHandler(anno, attrName, newValue);
		return (T) Proxy.newProxyInstance(anno.getClass().getClassLoader(), new Class[] { type }, handler);
	}

	/**
	 * @param composite
	 * @param field
	 */
	public void prefixFieldId(Field composite, Field field)
	{
		FindBy compositeAnno = findByAnno(composite);
		if (compositeAnno == null)
		{
			throw new IllegalStateException(composite.getName()+" isn't be annotated by FindBy");
		}

		FindBy fieldAnno = findByAnno(field);
		if (fieldAnno == null)
		{
			throw new IllegalStateException(field.getName()+"isn't be annotated by FindBy");
		}

		try
		{
			FindBy newAnno = setAttrValue(fieldAnno, FindBy.class, "id", compositeAnno.id() + "." + fieldAnno.id());
			System.out.println(String.format("old id: %s,new id: %s", fieldAnno.id(),newAnno.id()));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param obj
	 * @return
	 */
	private FindBy findByAnno(Field obj)
	{
		return obj.getAnnotation(FindBy.class);
	}
}