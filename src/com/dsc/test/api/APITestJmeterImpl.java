/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

/**
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public class APITestJmeterImpl implements Serializable, API
{
	private static final String	GET					= "GET";

	private static final String	POST				= "GET";

	private static final long	serialVersionUID	= 1L;

	public static void main(String[] args){
		// Engine
		StandardJMeterEngine jm = new StandardJMeterEngine();
		// jmeter.properties
		JMeterUtils.loadJMeterProperties("c:/tmp/jmeter.properties");

		HashTree hashTree = new HashTree();

		// HTTP Sampler
		HTTPSampler httpSampler = new HTTPSampler();
		httpSampler.setDomain("www.google.com");
		httpSampler.setPort(80);
		httpSampler.setPath("/");
		httpSampler.setMethod("GET");

		// Loop Controller
		TestElement loopCtrl = new LoopController();
		((LoopController)loopCtrl).setLoops(1);
		((LoopController)loopCtrl).addTestElement(httpSampler);
		((LoopController)loopCtrl).setFirst(true);

		// Thread Group
		SetupThreadGroup threadGroup = new SetupThreadGroup();
		threadGroup.setNumThreads(1);
		threadGroup.setRampUp(1);
		threadGroup.setSamplerController((LoopController)loopCtrl);

		// Test plan
		TestPlan testPlan = new TestPlan("MY TEST PLAN");

		hashTree.add("testPlan", testPlan);
		hashTree.add("loopCtrl", loopCtrl);
		hashTree.add("threadGroup", threadGroup);
		hashTree.add("httpSampler", httpSampler);

		jm.configure(hashTree);

		jm.run();
	}

	public static void main2(String[] argv) throws Exception {

		String jmeterHome1 = "/home/ksahu/apache-jmeter-2.13";
		File jmeterHome=new File(jmeterHome1);
		//      JMeterUtils.setJMeterHome(jmeterHome);
		String slash = System.getProperty("file.separator");

		if (jmeterHome.exists()) {
			File jmeterProperties = new File(jmeterHome.getPath() + slash + "bin" + slash + "jmeter.properties");
			if (jmeterProperties.exists()) {
				//JMeter Engine
				StandardJMeterEngine jmeter = new StandardJMeterEngine();

				//JMeter initialization (properties, log levels, locale, etc)
				JMeterUtils.setJMeterHome(jmeterHome.getPath());
				JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
				JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
				JMeterUtils.initLocale();

				// JMeter Test Plan, basically JOrphan HashTree
				HashTree testPlanTree = new HashTree();

				// First HTTP Sampler - open example.com
				HTTPSamplerProxy examplecomSampler = new HTTPSamplerProxy();
				examplecomSampler.setDomain("www.google.com");
				examplecomSampler.setPort(80);
				examplecomSampler.setPath("/");
				examplecomSampler.setMethod("GET");
				examplecomSampler.setName("Open example.com");
				examplecomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
				examplecomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());


				// Second HTTP Sampler - open blazemeter.com
				HTTPSamplerProxy blazemetercomSampler = new HTTPSamplerProxy();
				blazemetercomSampler.setDomain("www.tripodtech.net");
				blazemetercomSampler.setPort(80);
				blazemetercomSampler.setPath("/");
				blazemetercomSampler.setMethod("GET");
				blazemetercomSampler.setName("Open blazemeter.com");
				blazemetercomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
				blazemetercomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());


				// Loop Controller
				LoopController loopController = new LoopController();
				loopController.setLoops(1);
				loopController.setFirst(true);
				loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
				loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
				loopController.initialize();

				// Thread Group
				ThreadGroup threadGroup = new ThreadGroup();
				threadGroup.setName("Example Thread Group");
				threadGroup.setNumThreads(1);
				threadGroup.setRampUp(1);
				threadGroup.setSamplerController(loopController);
				threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
				threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());

				// Test Plan
				TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");
				testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
				testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
				testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

				// Construct Test Plan from previously initialized elements
				testPlanTree.add(testPlan);
				HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
				threadGroupHashTree.add(blazemetercomSampler);
				threadGroupHashTree.add(examplecomSampler);

				// save generated test plan to JMeter's .jmx file format
				SaveService.saveTree(testPlanTree, new FileOutputStream(jmeterHome + slash + "example.jmx"));

				//add Summarizer output to get test progress in stdout like:
				// summary =      2 in   1.3s =    1.5/s Avg:   631 Min:   290 Max:   973 Err:     0 (0.00%)
				Summariser summer = null;
				String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
				if (summariserName.length() > 0) {
					summer = new Summariser(summariserName);
				}


				// Store execution results into a .jtl file
				String logFile = jmeterHome + slash + "example.jtl";
				ResultCollector logger = new ResultCollector(summer);
				logger.setFilename(logFile);
				testPlanTree.add(testPlanTree.getArray()[0], logger);

				// Run Test Plan
				jmeter.configure(testPlanTree);
				jmeter.run();

				System.out.println("Test completed. See " + jmeterHome + slash + "example.jtl file for results");
				System.out.println("JMeter .jmx script is available at " + jmeterHome + slash + "example.jmx");
				System.exit(0);

			}
		}

		System.err.println("jmeter.home property is not set or pointing to incorrect location");
		System.exit(1);


	}
	private String				data;
	private String				domain;

	private String				method				= GET;

	private String				password;

	private int					port				= 80;

	private String				token;
	private String				user;

	@Override
	public APITestJmeterImpl authorize(String token)
	{
		this.token = token;
		return this;
	}

	@Override
	public APITestJmeterImpl authorize(String user, String password)
	{
		this.user = user;
		this.password = password;
		return this;
	}

	@Override
	public APITestJmeterImpl DELETE(String data)
	{
		this.data = data;
		return this;
	}

	@Override
	public APITestJmeterImpl domain(String domain)
	{
		this.domain = domain;
		return this;
	}

	@Override
	public APITestJmeterImpl GET(String data)
	{
		this.method = GET;
		this.data = data;
		return this;
	}

	@Override
	public APITestJmeterImpl port(int port)
	{
		this.port = port;
		return this;
	}

	@Override
	public APITestJmeterImpl POST(String data)
	{
		this.method = POST;
		this.data = data;
		return this;
	}
	//
	// @Override
	// public APITestJmeterImpl properties(String file)
	// {
	//
	// return this;
	// }
	//
	// @Override
	// public APITestJmeterImpl restfull(String url)
	// {
	// return this;
	// }

	@Override
	public String run() throws Exception
	{
		SampleResult result = new SampleResult();
		result.sampleStart(); // start stopwatch

		try
		{
			java.net.URL url = new java.net.URL(url());
			java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.connect();

			result.sampleEnd(); // stop stopwatch
			result.setSuccessful(true);
			result.setResponseMessage("Successfully performed action");
			result.setResponseCodeOK(); // 200 code
		} catch (Exception e)
		{
			result.sampleEnd(); // stop stopwatch
			result.setSuccessful(false);
			result.setResponseMessage("Exception: " + e);

			// get stack trace as a String to return as document data
			java.io.StringWriter stringWriter = new java.io.StringWriter();
			e.printStackTrace(new java.io.PrintWriter(stringWriter));
			// result.setResponseData(stringWriter.toString());
			result.setDataType(org.apache.jmeter.samplers.SampleResult.TEXT);
			result.setResponseCode("500");

			throw e;
		}

		return result.getResponseDataAsString();
	}

	/**
	 * @return
	 */
	private String url()
	{
		return domain + ":" + port + "/" + data;
	}
}
