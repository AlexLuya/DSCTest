/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util.common.model


import static com.dsc.util.StringUtil.trimBoth

import com.google.common.collect.Lists

/**
 * @Author alex
 * @CreateTime 17.03.2017 16:44:29
 * @Version 1.0
 * @Since 1.0
 */
class NameValue extends Name
{
	String value

	NameValue(String[] arr){
		//HP validate args
		name=trimBoth(arr[0])
		value=trimBoth(arr[1])
	}

	static List<NameValue>  parseAsList(Object obj){
		List<NameValue> list= Lists.newArrayList()

		if(null==obj||!(obj instanceof String)){
			return list
		}

		//more smart way,for example,use other lib
		//HP more types of detection,for example ',','&'
		//HP move to StringUtil
		String[] data=obj.toString().split("\\r\\n|\\n|\\r")

		for(int i=0;i<data.length;i++){
			//HP more types of saperator
			list.add(new NameValue(data[i].split("=")))
		}

		return list
	}
}
