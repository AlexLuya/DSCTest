package com.dsc.spock.spec.extractor.core.test

import spock.lang.Specification

class OnlyExpectWithDescriptionSpec extends Specification {
    def "adding test"() {
        expect: "adding works"
            2 + 10 == 12
    }
}