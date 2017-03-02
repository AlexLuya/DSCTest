/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.performance;

import com.dsc.test.api.APITestImpl;

/**
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public class PerformanceTestJmeterImpl extends APITestImpl
{

	//	public static void main(String[] argv) throws Exception
	//	{
	//		// JMeter Engine
	//		StandardJMeterEngine jmeter = new StandardJMeterEngine();
	//
	//		// JMeter initialization (properties, log levels, locale, etc)
	//		JMeterUtils.loadJMeterProperties("/path/to/your/jmeter/bin/jmeter.properties");
	//		JMeterUtils.initLogging();// you can comment this line out to see extra
	//		// log messages of i.e. DEBUG level
	//		JMeterUtils.initLocale();
	//
	//		// JMeter Test Plan, basic all u JOrphan HashTree
	//		HashTree testPlanTree = new HashTree();
	//
	//		// HTTP Sampler
	//		HTTPSampler httpSampler = new HTTPSampler();
	//		httpSampler.setDomain("example.com");
	//		httpSampler.setPort(80);
	//		httpSampler.setPath("/");
	//		httpSampler.setMethod("GET");
	//
	//		// Loop Controller
	//		LoopController loopController = new LoopController();
	//		loopController.setLoops(1);
	//		loopController.addTestElement(httpSampler);
	//		loopController.setFirst(true);
	//		loopController.initialize();
	//
	//		// Thread Group
	//		ThreadGroup threadGroup = new ThreadGroup();
	//		threadGroup.setNumThreads(1);
	//		threadGroup.setRampUp(1);
	//		threadGroup.setSamplerController(loopController);
	//
	//		// Test Plan
	//		TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");
	//
	//		// Construct Test Plan from previously initialized elements
	//		testPlanTree.add("testPlan", testPlan);
	//		testPlanTree.add("loopController", loopController);
	//		testPlanTree.add("threadGroup", threadGroup);
	//		testPlanTree.add("httpSampler", httpSampler);
	//
	//		// Run Test Plan
	//		jmeter.configure(testPlanTree);
	//		jmeter.run();
	//
	//	}

}
