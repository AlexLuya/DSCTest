package com.dsc.spock.spec.extractor.core.test

import spock.lang.Specification

class OnlyExpectWithAnd extends Specification {
    def "adding test"() {
        expect:
            2 + 10 == 12
        and:
            12 > 0
    }
}