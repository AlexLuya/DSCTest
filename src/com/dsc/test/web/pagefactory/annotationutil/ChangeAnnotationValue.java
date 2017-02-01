/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ChangeAnnotationValue {
    public static void main(String[] args)  {
        Foo foo = new Foo();
        Field field = foo.getClass().getDeclaredFields()[0];

        Anno anno = field.getAnnotation(Anno.class);
        System.out.println(String.format("Old properties: %s, %s, %s", anno.value(), anno.bar(), anno.barr()));

        Anno anno2 = (Anno) setAttrValue(anno, Anno.class, "value", "new");
        System.out.println(String.format("New properties: %s, %s, %s", anno2.value(), anno2.bar(), anno2.barr()));

        Anno anno3 = (Anno) setAttrValue(anno2, Anno.class, "bar", "new bar");
        System.out.println(String.format("old value:%s,New properties: %s, %s, %s",anno2.bar(), anno3.value(), anno3.bar(), anno3.barr()));
    }

    public static Annotation setAttrValue(Annotation anno, Class<? extends Annotation> type, String attrName, Object newValue)  {
        InvocationHandler handler = new AnnotationInvocationHandler(anno, attrName, newValue);
        Annotation proxy = (Annotation) Proxy.newProxyInstance(anno.getClass().getClassLoader(), new Class[]{type}, handler);
        return proxy;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Anno {
    String bar();
    String barr();
    String value();
}


class Foo {
    @Anno(value="old", bar="bar", barr="barr")
    public Object field1;
}