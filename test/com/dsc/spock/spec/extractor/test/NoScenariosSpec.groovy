package com.dsc.spock.spec.extractor.test

import spock.lang.Specification

class NoScenariosSpec extends Specification {
    String test() {
        ""
    }

    int size(int t) {
        t + 1
    }
}