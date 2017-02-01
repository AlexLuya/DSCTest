/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
**/
package com.dsc.spock.extractor.mavenplugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.logging.Log;

import com.dsc.spock.extractor.Extract;
import com.dsc.spock.extractor.domain.Spec;

class SpecificationModelGeneratorWrapper {
	private final Log log;

	public SpecificationModelGeneratorWrapper(Log log) {
		this.log = log;
	}

	List<Spec> generateSpecificationModel(ClassLoader contextClassLoader, List<File> spockFiles) {
		List<Spec> specs = new ArrayList<>();
		for (File file : spockFiles) {
			specs.addAll(Extract.specsOf(file, contextClassLoader));
			log.info("Generated specification model for file " + file.getAbsolutePath());
		}
		return specs;
	}
}
