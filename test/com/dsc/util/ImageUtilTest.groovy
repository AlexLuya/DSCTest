/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util

import static com.dsc.util.FileUtil.*

import org.apache.commons.io.FileUtils

import spock.lang.Specification

public class ImageUtilTest extends Specification {
	def setup(){

	}

	def "combine"(){
		//var
		String COMBINED_IMG=imagePath("combined.png")
		String IMAGE_1 = imagePath("image_1.png")
		String IMAGE_2 = imagePath("image_2.png")

		//"precombined image got delete if existed"
		FileUtils.forceDeleteOnExit(new File(COMBINED_IMG))
		given:"images to combin existed"
		ensureExisted(IMAGE_1)
		ensureExisted(IMAGE_2)

		when:"combine two images"
		ImageUtil.combine(IMAGE_1,IMAGE_2,COMBINED_IMG)

		then:"image got combined"
		ensureExisted(COMBINED_IMG)
	}
}
