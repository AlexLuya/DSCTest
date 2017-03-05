package com.dsc.test.api

import com.dsc.test.api.base.ColumnCfg
import com.dsc.test.common.report.Summary

import spock.lang.Specification

public class APITestImplTest extends Specification
{
	def "multiple tests in excel"(){

		String excelFile="test/com/dsc/test/api/PD-API-Testing.xls"
		int ignoredRows = 3
		String authKey="Bearer x-69Fz7k36wZl7WPKQ0cQwu64GK5b7UcDUsDngeR5qFR9H7gpT_wD-pHOKaxN4hf-mgalUWY7PE-M2zs_mrEZ2abgLy7RMcOsk5HJovxxsY1r6g5f7fieJXgcyF9V0ZLl826omXKoolbhdJtGkLJt9m-agTSaC3sDBfWEA3YBldWXMjX58XN5SAfLdQCjpEHJRCwSM321HaA6P0bexY3Q4m9FpGkfvk2fnAzraR5Ju2tYsmjyE6oDtgGDX6Oo_NpUrFwkiwxhUsaNVnAp3sKoJfwJ69F7VIBpBwKabFRioEdz78pjWkyMQn8qvvtkMd2kjFT8c8s4U5egw-Bt2j1Io_DEI8jLlALqnkqDQTN0Seg4RIBhDlqx8pCoAz08MrtVarHvlxNSxMdDYyrhQpc_A"
		ColumnCfg columnCfg = ColumnCfg.name(2).url(4).method(3).data(5).expectation(6)

		when:""
		Summary summary=API.test.name("HuaShan").header("Authorization",authKey).excel(new File(excelFile).getAbsolutePath(),ignoredRows,columnCfg).resultAsExcel()

		then:""
		summary.total==64
		summary.fails==0
		summary.ignores==0


	}
	def "single test"(){

	}
}
