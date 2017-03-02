package com.dsc.test.api

import com.dsc.test.common.report.Summary

import spock.lang.Specification

public class APITestImplTest extends Specification
{
	def "multiple tests in excel"(){
		String file=""
		String authKey=""

		when:""
		Summary summary=API.test.name("HuaShan").header("Authorization",authKey).excel("",3).resultAsExcel()

		then:""
		summary.total==64
		summary.failed==0
		summary.ignored==0

	}
	def "single test"(){

	}
}
