package com.dsc.spock.extractor

import com.dsc.spock.extractor.domain.Block
import com.dsc.spock.extractor.domain.Ignored
import com.dsc.spock.extractor.domain.Scenario
import com.dsc.spock.extractor.domain.Spec
import com.dsc.spock.extractor.domain.Statement
import com.dsc.spock.extractor.test.GivenExpectAndSetupExpectCleanupSpec
import com.dsc.spock.extractor.test.NoScenariosSpec
import com.dsc.spock.extractor.test.OnlyExpectWithAnd
import com.dsc.spock.extractor.test.OnlyExpectWithDescriptionSpec
import com.dsc.spock.extractor.test.OnlyExpectWithNarrative
import com.dsc.spock.extractor.test.OnlyExpectWithSubject
import com.dsc.spock.extractor.test.OnlyExpectWithTitle
import com.dsc.spock.extractor.test.OnlyExpectWithoutDescriptionSpec
import com.dsc.spock.extractor.test.WhenThenWithDescriptionSpec
import com.dsc.spock.extractor.test.WithIgnoredSpec
import com.dsc.spock.extractor.test.WithIssueSpec
import com.dsc.spock.extractor.test.WithNoBlocksSpec
import com.dsc.spock.extractor.test.WithSeeSpec
import com.dsc.spock.extractor.test.WithWhereSpec

import spock.lang.Specification

class ExtractTest extends Specification {

	def "should generate spec for only expect with description spec"() {
		when:
		List<Spec> specs = Extract.specsOf(OnlyExpectWithDescriptionSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(name: "com.dsc.spock.extractor.test.OnlyExpectWithDescriptionSpec", scenarios: [
			new Scenario(name: "adding test", statements: [new Statement(block: Block.EXPECT, description: "adding works")])
		])
	}

	def "should generate spec for only expect without description spec"() {
		when:
		List<Spec> specs = Extract.specsOf(OnlyExpectWithoutDescriptionSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(name: "com.dsc.spock.extractor.test.OnlyExpectWithoutDescriptionSpec", scenarios: [
			new Scenario(name: "adding test", statements: [new Statement(block: Block.EXPECT)])
		])
	}

	def "should generate spec with when then with description spec"() {
		when:
		List<Spec> specs = Extract.specsOf(WhenThenWithDescriptionSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(name: "com.dsc.spock.extractor.test.WhenThenWithDescriptionSpec", scenarios: [
			new Scenario(name: "length should be positive", statements: [
				new Statement(block: Block.WHEN, description: "get length of string"),
				new Statement(block: Block.THEN, description: "length is positive")
			])
		])
	}

	def "should generate spec with where spec"() {
		when:
		List<Spec> specs = Extract.specsOf(WithWhereSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(name: "com.dsc.spock.extractor.test.WithWhereSpec", scenarios: [
			new Scenario(name: "length should be positive", statements: [
				new Statement(block: Block.WHEN),
				new Statement(block: Block.THEN),
				new Statement(block: Block.WHERE)
			])
		])
	}

	def "should generate spec with given expect and setup expect cleanup"() {
		when:
		List<Spec> specs = Extract.specsOf(GivenExpectAndSetupExpectCleanupSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(name: "com.dsc.spock.extractor.test.GivenExpectAndSetupExpectCleanupSpec",
		scenarios: [
			new Scenario(name: "length should be positive", statements: [
				new Statement(block: Block.GIVEN, description: "A string"),
				new Statement(block: Block.EXPECT, description: "check length")
			]),
			new Scenario(name: "length should be positive 2", statements: [
				new Statement(block: Block.SETUP, description: "A string"),
				new Statement(block: Block.EXPECT, description: "check length"),
				new Statement(block: Block.CLEANUP, description: "Clean after test")
			])
		])
	}

	def "should generate spec without scenario when there is no blocks in method"() {
		when:
		List<Spec> specs = Extract.specsOf(WithNoBlocksSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(name: "com.dsc.spock.extractor.test.WithNoBlocksSpec", scenarios: [
			new Scenario(name: "adding test", statements: [new Statement(block: Block.EXPECT, description: "adding works")])
		])
	}

	def "should generate empty spec when there are no scenarios"() {
		when:
		List<Spec> specs = Extract.specsOf(NoScenariosSpec.class)
		then:
		specs.isEmpty()
	}

	def "should generate spec for only expect with Title annotation"() {
		when:
		List<Spec> specs = Extract.specsOf(OnlyExpectWithTitle.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(
				name: "com.dsc.spock.extractor.test.OnlyExpectWithTitle",
				title: "Tests of adding",
				scenarios: [
					new Scenario(
					name: "adding test",
					statements: [
						new Statement(
						block: Block.EXPECT)])
				])
	}

	def "should generate spec for only expect with and"() {
		when:
		List<Spec> specs = Extract.specsOf(OnlyExpectWithAnd.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(
				name: "com.dsc.spock.extractor.test.OnlyExpectWithAnd",
				scenarios: [
					new Scenario(
					name: "adding test",
					statements: [
						new Statement(block: Block.EXPECT),
						new Statement(block: Block.AND)
					])
				])
	}

	def "should generate spec for only expect with Narrative annotation"() {
		when:
		List<Spec> specs = Extract.specsOf(OnlyExpectWithNarrative.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(
				name: "com.dsc.spock.extractor.test.OnlyExpectWithNarrative",
				description: "Long description of tests",
				scenarios: [
					new Scenario(
					name: "adding test",
					statements: [
						new Statement(
						block: Block.EXPECT)])
				])
	}

	def "should generate spec Subject annotation on class and field"() {
		when:
		List<Spec> specs = Extract.specsOf(OnlyExpectWithSubject.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(
				name: "com.dsc.spock.extractor.test.OnlyExpectWithSubject",
				subjects: [String, Long, int, Byte] as Set,
				scenarios: [
					new Scenario(
					name: "adding test",
					statements: [
						new Statement(
						block: Block.EXPECT)])
				])
	}

	def "should generate spec with See annotation on class and methods"() {
		when:
		List<Spec> specs = Extract.specsOf(WithSeeSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(
				name: "com.dsc.spock.extractor.test.WithSeeSpec",
				links: ['http://google.com', 'http://oracle.com'] as Set,
				scenarios: [
					new Scenario(
					name: "length of empty string is zero",
					links: ['http://test.com'] as Set,
					statements: [
						new Statement(
						block: Block.EXPECT)]),
					new Scenario(
					name: "length should be positive",
					links: ['http://length.com', 'http://hi.com'] as Set,
					statements: [
						new Statement(
						block: Block.EXPECT),
						new Statement(
						block: Block.WHERE)])
				])
	}

	def "should generate spec with Issue annotation on class and methods"() {
		when:
		List<Spec> specs = Extract.specsOf(WithIssueSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(
				name: "com.dsc.spock.extractor.test.WithIssueSpec",
				issues: ['http://mantis.test/1', 'http://bugzilla.test/145'] as Set,
				scenarios: [
					new Scenario(
					name: "length of empty string is zero",
					issues: ['http://mantis.test/5'] as Set,
					statements: [
						new Statement(
						block: Block.EXPECT)]),
					new Scenario(
					name: "length should be positive",
					issues: ['http://mantis.test/132', 'http://bugzilla.test/1212'] as Set,
					statements: [
						new Statement(
						block: Block.EXPECT)])
				])
	}

	def "should generate spec with ignore annotation on class and methods"() {
		when:
		List<Spec> specs = Extract.specsOf(WithIgnoredSpec.class)
		then:
		specs.size() == 1
		specs[0] == new Spec(
				name: "com.dsc.spock.extractor.test.WithIgnoredSpec",
				ignored: new Ignored("Ignore spec description"),
				scenarios: [
					new Scenario(
					name: "length of empty string is zero",
					ignored: new Ignored("ignored scenario"),
					statements: [
						new Statement(
						block: Block.EXPECT)]),
					new Scenario(
					name: "length should be positive",
					ignored: new Ignored([:]),
					statements: [
						new Statement(
						block: Block.EXPECT)])
				])
	}
}