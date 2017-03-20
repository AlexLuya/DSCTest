/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api
import org.apache.http.client.params.ClientPNames
import org.apache.http.params.CoreConnectionPNames

import com.dsc.test.api.base.ColumnCfg
import com.dsc.test.api.base.Response
import com.dsc.test.common.report.Summary
import com.dsc.util.ContentType

import io.restassured.RestAssured
import io.restassured.config.EncoderConfig
import io.restassured.config.HttpClientConfig
import io.restassured.specification.RequestSpecification
import spock.lang.Specification

/**
 * @Author alex
 * @CreateTime 02.03.2017 13:58:49
 * @Version 1.0
 * @Since 1.0
 */
public class APITestImplTest extends Specification
{
	def "multiple specs in excel"(){

		String excelFile="test/com/dsc/test/api/PD-API-Testing.xls"
		int ignoredRows = 3
		String authKey="Bearer x-69Fz7k36wZl7WPKQ0cQwu64GK5b7UcDUsDngeR5qFR9H7gpT_wD-pHOKaxN4hf-mgalUWY7PE-M2zs_mrEZ2abgLy7RMcOsk5HJovxxsY1r6g5f7fieJXgcyF9V0ZLl826omXKoolbhdJtGkLJt9m-agTSaC3sDBfWEA3YBldWXMjX58XN5SAfLdQCjpEHJRCwSM321HaA6P0bexY3Q4m9FpGkfvk2fnAzraR5Ju2tYsmjyE6oDtgGDX6Oo_NpUrFwkiwxhUsaNVnAp3sKoJfwJ69F7VIBpBwKabFRioEdz78pjWkyMQn8qvvtkMd2kjFT8c8s4U5egw-Bt2j1Io_DEI8jLlALqnkqDQTN0Seg4RIBhDlqx8pCoAz08MrtVarHvlxNSxMdDYyrhQpc_A"
		ColumnCfg columnCfg = ColumnCfg.name(2).action(4).method(3).data(5).expectation(6)

		when:"perform tests from specs in excel"
		Summary summary=API.test.name("HuaShan").header("Authorization",authKey).excel(excelFile,ignoredRows,columnCfg).resultAsExcel()

		then:"expected result got returned"
		summary.total==61
		summary.fails==0
		summary.ignores==0
	}

	def "upload image"(){
		String imageFile="test/com/dsc/test/api/食堂阿姨眼中的我们.gif"
		String authKey="Bearer x-69Fz7k36wZl7WPKQ0cQwu64GK5b7UcDUsDngeR5qFR9H7gpT_wD-pHOKaxN4hf-mgalUWY7PE-M2zs_mrEZ2abgLy7RMcOsk5HJovxxsY1r6g5f7fieJXgcyF9V0ZLl826omXKoolbhdJtGkLJt9m-agTSaC3sDBfWEA3YBldWXMjX58XN5SAfLdQCjpEHJRCwSM321HaA6P0bexY3Q4m9FpGkfvk2fnAzraR5Ju2tYsmjyE6oDtgGDX6Oo_NpUrFwkiwxhUsaNVnAp3sKoJfwJ69F7VIBpBwKabFRioEdz78pjWkyMQn8qvvtkMd2kjFT8c8s4U5egw-Bt2j1Io_DEI8jLlALqnkqDQTN0Seg4RIBhDlqx8pCoAz08MrtVarHvlxNSxMdDYyrhQpc_A"
		String uploadingUrl="http://139.196.24.63:8082/api/PatientDiagnose/UploadImgs"

		when:"upload a local image"
		String res=API.test.name("API-Test-Image-Uploading").header("Authorization",authKey).action(uploadingUrl).upload(imageFile)

		then:"expected result got returned"
		res=="200"
	}

	def "Careplan test"(){
		String url="http://test.icloudcare.com/assessment/api/Test.html"
		String excelFile="test/com/dsc/test/api/CarePlan-Testing.xls"
		int ignoredRows = 1
		ColumnCfg columnCfg = ColumnCfg.name(0).method(1).url(2).data(3).expectation(4)

		when:"perform tests from specs in excel"
		Summary summary=API.test.name("Care plan").url(url).contentType(ContentType.URLENC).excel(excelFile,ignoredRows,columnCfg).resultAsExcel()

		then:"expected result got returned"
		summary.total==2
		summary.fails==1
		summary.ignores==0
	}

	def "single spec"(){
		String url="http://test.icloudcare.com/assessment/api/Test.html"
		API test=API.test.name("Care plan").url(url).contentType(ContentType.URLENC)

		when:"perform tests from specs in excel"
		Response res=test.formParam("Action", "getDiagnosisList").formParam("Data", "[{\"subject\":\"家庭介护能力\",\"option\":\"介护能力较弱\"}]").post()

		then:"expected result got returned"
		res.asString()=="[\"家庭照护能力不足\",\"家庭成员关系问题\"]"
	}

	def "Pure RestAssured test"(){
		String url="http://test.icloudcare.com/assessment/api/Test.html"
		RestAssured.config = RestAssured.config()
				.httpClient(HttpClientConfig.httpClientConfig()
				.setParam(ClientPNames.CONN_MANAGER_TIMEOUT, 20000L)  // HttpConnectionManager connection return time
				.setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000) // Remote host connection time
				.setParam(CoreConnectionPNames.SO_TIMEOUT, 20000)  // Remote host response time
				)

		RequestSpecification	given=RestAssured.given().config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).header("Content-Type", "application/x-www-form-urlencoded")


		when:"perform tests from specs in excel"
		io.restassured.response.Response res=given.formParam("Action", "getDiagnosisList").formParam("Data", "[{\"subject\":\"家庭介护能力\",\"option\":\"介护能力弱\"}]").post(url)

		then:"expected result got returned"
		res.asString()=="sdfsdaf"
	}
}