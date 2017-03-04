package com.dsc.test.api

import com.dsc.test.api.base.ColumnCfg
import com.dsc.test.common.report.Summary

import spock.lang.Specification

public class APITestImplTest extends Specification
{
	def "multiple tests in excel"(){
		String excelFile="test/api/PD-API-Testing.xls"
		String ignoredRows = 3
		String authKey=""
		ColumnCfg columnCfg = ColumnCfg.name(1).url(3).method(2).data(4).expectation(5)

		when:""
		Summary summary=API.test.name("HuaShan").header("Authorization",authKey).excel(excelFile,ignoredRows,columnCfg).resultAsExcel()

		then:""
		summary.total==64
		summary.fails==0
		summary.ignores==0

	}
	def "single test"(){

	}
}
