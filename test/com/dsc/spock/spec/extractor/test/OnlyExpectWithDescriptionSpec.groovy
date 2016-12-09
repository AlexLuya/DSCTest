package com.dsc.spock.spec.extractor.test

import spock.lang.Specification

class OnlyExpectWithDescriptionSpec extends Specification {
    def "adding test"() {
        expect: "adding works"
            2 + 10 == 12
    }
}